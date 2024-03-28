function getStudentDataFromServer(url) {
	fetch(url, {
		method: 'GET', 
		headers: {
			'Accept': 'application/json'
		}
	}).then(response => response.json())
		.then(response => loadDataAsTable(response))
		.catch((err) => {
			console.log("Error when getting the JSON student data: " + err);
		});
}

function loadDataAsTable(response) {
	/*
	Note to self: 
	How to get the student at index 0 from the response object: 
		console.log(response["students"][0]);
	*/
	let table = document.getElementById("chartBody");

	let students = response["students"];
	let studentCount = students.length;

	for (var i = 0; i < studentCount; i++) {
		//console.log(students[i]);
		let student = students[i];
		let studentRow = document.createElement("tr");
		table.appendChild(studentRow);

		let nameCell = document.createElement("td");
		nameCell.innerHTML = "<p>" + student["name"] + "</p>";
		studentRow.appendChild(nameCell);

		let idCell = document.createElement("td");
		idCell.innerHTML = "<p>" + student["id"] + "</p>";
		studentRow.appendChild(idCell);

		let gpaCell = document.createElement("td");
		gpaCell.innerHTML = "<p>" + student["gpa"] + "</p>";
		studentRow.appendChild(gpaCell);
	}
}

let protocol = "http://";
let domain = "localhost:8080/";
let contextRoot = "lab6/";
let jsonDataUrl = protocol + domain + contextRoot + "api/students/json";

function add_record() {
	// Get the values: 
	let name = document.getElementById("name").value;
	let id = document.getElementById("id").value;
	let gpa = document.getElementById("gpa").value;

	if (name == "" || id == "" || gpa == "") {
		console.log("Error: at least one of name, id, or gpa is empty. ");
		return;
	}

	//Add to table: 
	let table = document.getElementById("chartBody");
	let newRow = document.createElement("tr");
	table.appendChild(newRow);
	//Name: 
	let nameCell = document.createElement("td");
	nameCell.innerHTML = "<p>" + name + "</p>";
	newRow.appendChild(nameCell);
	//ID: 
	let idCell = document.createElement("td");
	idCell.innerHTML = "<p>" + id + "</p>";
	newRow.appendChild(idCell);
	//GPA: 
	let gpaCell = document.createElement("td");
	gpaCell.innerHTML = "<p>" + gpa + "</p>";
	newRow.appendChild(gpaCell);

	//Reset values: 
	document.getElementById("name").value = "";
	document.getElementById("id").value = "";
	document.getElementById("gpa").value = "";
}

let formatDataUrl = protocol + domain + contextRoot + "api/format/";

function getJsonFromTable() {
	let endpoint = "json";
	let contentType = "application/json";
	let url = formatDataUrl + endpoint;
	const payload = document.getElementById("chart").innerHTML;
	console.log(payload);

	//Create a request to server. 
	const request = new XMLHttpRequest();
	request.open("POST", url);
	request.setRequestHeader("Content-Type", "text/html"); // setting the sending content-type
	request.setRequestHeader("Accept", contentType); // setting the receiving content-type

	//On response handler: 
	request.onload = () => {
		if (request.status !== 200) {
			console.error("Something went wrong went contacting the server. Got response: ");
			console.error(request);
			return
		}
		console.log("Received from the server: ", request.responseText) // this contains the received payload
		/**
		* this is how to programmatically download something in javascript.
		* 1. create an invisible anchor tag
		* 2. set the href attribute (contains file data)
		* 3. set the download attribute (contains the file name)
		* 4. click it
		*/
		var element = document.createElement('a');
		element.setAttribute('href', `data:${contentType};charset=utf-8,` + encodeURIComponent(request.responseText));
		element.setAttribute('download', `students.${endpoint}`);
		element.click();
	}

	//Send request. 
	request.send(payload);
}

function getCSVFromTable() {
	let endpoint = "csv";
	let contentType = "text/csv";
	let url = formatDataUrl + endpoint;
	const payload = document.getElementById("chart").innerHTML;
	console.log(payload);

	//Create a request to server. 
	const request = new XMLHttpRequest();
	request.open("POST", url);
	request.setRequestHeader("Content-Type", "text/html"); // setting the sending content-type
	request.setRequestHeader("Accept", contentType); // setting the receiving content-type

	//On response handler: 
	request.onload = () => {
		if (request.status !== 200) {
			console.error("Something went wrong went contacting the server. Got response: ");
			console.error(request);
			return
		}
		console.log("Received from the server: ", request.responseText) // this contains the received payload
		/**
		* this is how to programmatically download something in javascript.
		* 1. create an invisible anchor tag
		* 2. set the href attribute (contains file data)
		* 3. set the download attribute (contains the file name)
		* 4. click it
		*/
		var element = document.createElement('a');
		element.setAttribute('href', `data:${contentType};charset=utf-8,` + encodeURIComponent(request.responseText));
		element.setAttribute('download', `students.${endpoint}`);
		element.click();
	}

	//Send request. 
	request.send(payload);
}

(function () {
	console.log(jsonDataUrl);
	fetch(protocol + domain + contextRoot + "api/format/test", {
		method: 'GET', 
		headers: {
			'Accept': 'text/plain'
		}
	}).then(response => console.log(response));
	getStudentDataFromServer(jsonDataUrl);
})();
