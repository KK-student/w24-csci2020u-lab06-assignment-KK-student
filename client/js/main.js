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

let jsonDataUrl = "http://localhost:8080/lab5/api/students/json";

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
	nameCell.innerHTML = name;
	newRow.appendChild(nameCell);
	//ID: 
	let idCell = document.createElement("td");
	idCell.innerHTML = id;
	newRow.appendChild(idCell);
	//GPA: 
	let gpaCell = document.createElement("td");
	gpaCell.innerHTML = gpa;
	newRow.appendChild(gpaCell);

	//Reset values: 
	document.getElementById("name").value = "";
	document.getElementById("id").value = "";
	document.getElementById("gpa").value = "";
}

(function () {
	getStudentDataFromServer(jsonDataUrl);
})();
