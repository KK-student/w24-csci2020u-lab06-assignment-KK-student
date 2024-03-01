/**
 * this is the testing file for Cypress, DO NOT TOUCH
 */

// Length is 7 
// 1 for the table headers
// 6 for the entries from the json file 
const DEFAULTLEN = 7;

describe("Testing the API portion of the website", () => {
  /**
   * need to test how the student's website handles API information
   * - stubbing any request to http://localhost with students.json
   *
   * tests
   * - if there is no error, then there should be 7 <tr>s in the table(1 for headers and 6 for the contents from the json file)
   * - existing functionality should still work too
   *   - adding a <tr> should still work (there will be 6)
   */

  beforeEach("setting HTTP request failure exception", () => {
    // setting a fetch error handler
    cy.on("uncaught:exception", (e) => {
      if (e.message.includes("NetworkError")) {
        // page made call to API but failed, ignoring
        return false;
      }
      if (e.message.includes("fetch")) {
        // page made call to API but failed, ignoring
        return false;
      }
    });
  })

  /

  context("Testing Lab 5 Components ", () => {
    // stubbing the response
  beforeEach(() => {
    // stubbing the response before each test
    cy.intercept("GET", "*/api/students/*", {
      fixture: "students.json",
    }).as("payload");

    // loading the page before each test
    cy.visit("../../index.html");

    // awaiting HTTP request before each test
    cy.wait("@payload");
    cy.wait(250);
  });
    it("Lab 5: the table should render the data from the API", () => {

      const expectedData = ["Ryan Gosling", '100000000', '4.3'];

      /// HTML page requested data, processing it

      // examing the table to test for records
      cy.get("table ")
        .find("tr") 
        .should("have.length", DEFAULTLEN)
        .eq(1) // second row
        .find("td")
        .should("have.length", expectedData.length)
        .each(($td, index) => {
        const actualValue = $td.text().trim();
        const expectedValue = expectedData[index];
        cy.wrap(actualValue).should("be.eq", expectedValue);
        });



      
    });
      context("Testing the page's JS", () => {
        const mapping = [
          "input[name='name'], input[placeholder='name']",
          "input[name='id'], input[placeholder='id']",
          "input[name='gpa'], input[placeholder='gpa']"
        ];
        const data = ["fort nite", "100000000", "4.3"];
  
        it("Should append to table if all input fields are Non-Empty", () => {
          // setting the values
          for (const index in [0, 1, 2]) {
            cy.get(mapping[index]).type(data[index]);
          }
  
         
          // clicking the button
          cy.get("button").filter((index, button) => Cypress.$(button).text().toLowerCase() === "submit").click();
         
  
          
          
  
          cy.get("table")
          .find("tr") // Select all table rows
          .last() // Get the last row
          .find("td") // Find all table cells within the last row
          .should("have.length.gte", 3)
          .then(($tds) => {
            for (const index in [0, 1, 2])
              cy.wrap($tds[index].innerText).should("be.eq", data[index]);
          });
        });
  
        context("Should not append to table if any input field is empty", () => {
          mapping.forEach((el, index) => {
              it(`should do nothing if ${el} is empty`, () => {
                  // skipping the current index
                  cy.get(el).clear(); // clear the current input field
                  for (let i = 0; i < mapping.length; i++) {
                      // skipping the current index
                      if (i === index) continue;
                      // typing into the inputs
                      cy.get(mapping[i]).type(data[i]);
                  }
                  
  
                  const expectedData = ["Helena Bonham Carter", '100000005', '2'];
  
                  // the button shouldn't do anything
                  // finds the table with header
                  // it checks for the tr in both the thead and tbody and counts for both
                  // since we expect table headers, the count should be 1(from thead)
                  cy.get("button").filter((index, button) => Cypress.$(button).text().toLowerCase() === "submit").click()
                  
                  cy.get("table")
                  .find("tr")
                  .last() 
                  .find("td")
                  .should("have.length", expectedData.length)
                  .each(($td, index) => {
                  const actualValue = $td.text().trim();
                  const expectedValue = expectedData[index];
  
                  console.log(`Actual Value for index ${index}: ${actualValue}`);
                  cy.wrap(actualValue).should("be.eq", expectedValue);
                  });
      
                  // clearing inputs
                  for (const el of mapping) {
                      cy.get(el).clear();
                  }
              });
          });
      });
  
      
  
  
    });



      

      
    
    


  });

  context("Lab 6: testing each button that POSTs data to the API", () => {
    beforeEach("prepping the page", () => {
      // stubbing the response
      cy.intercept("GET", "*/api/students/*", {
        fixture: "students.json",
      }).as("payload");

      // stubbing the response
      cy.intercept("POST", "*/api/format/*").as("request");

      // loading the page
      cy.visit("../../index.html");

      // awaiting HTTP request
      cy.wait("@payload");
      cy.wait(250);
    });

    // Define the mapping between endpoint types and MIME types
  const type = {
    "csv": "text/csv",
    "xml": "application/xml",
    "json": "application/json"
  };

  // Define the mapping between button indices and endpoint types
  const buttonEndpointMapping = {
    0: "csv",
    1: "xml",
    2: "json"
  };

  // Iterate over the buttons and click them in the specified order
  [0, 1, 2].forEach(index => {
    const endpoint = buttonEndpointMapping[index];

    it(`Button ${index + 1} should make a POST to /api/format/${endpoint}`, () => {
      cy.intercept("POST", `/api/format/${endpoint}`).as("request");
      cy.get("div")
        .eq(1) // Select the second div
        .find("button:eq(" + index + ")") 
        .click()
        .wait("@request")
        .then(req => {
          const request = req.request;
          const headers = request.headers;

          expect(request.body.length).not.eq(0, "the body shouldn't be empty");
          expect(request.method).to.eq("POST", "the method should be POST");
          expect(headers["accept"]).to.contain(type[endpoint], "the request should accept the proper MIME type");
          expect(headers["content-type"]).to.contain("text/html", "the request should contain the proper content-type");
        });
    });
  });



  });
});
