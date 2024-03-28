package com.example.lab6;

public class FileFormatter {

	// storing the students in an array
	private Student[] students;

	// TO DO ...

	/**
	 * stores a list of Student objects in an instance
	 * @param students the list of student objects
	 */
	public FileFormatter(Student[] students) {this.students = students;}

	/**
	 * turns `this.students` into CSV format
	 * @param   delimiter   the character that separates each field
	 * @return a formatted CSV file
	 */
	public String toCSV(String delimiter) {
		StringBuilder builder = new StringBuilder("name");
		builder.append(delimiter);
		builder.append("id");
		builder.append(delimiter);
		builder.append("gpa");
		builder.append(delimiter);
		builder.append("\n");
		for (int i = 0; i < students.length; i++) {
			Student student = students[i];
			builder.append(student.name);
			builder.append(delimiter);
			builder.append(student.id);
			builder.append(delimiter);
			builder.append(student.gpa);
			if (i != students.length - 1) {
				builder.append(delimiter);
				builder.append("\n");
			}
		}

		return builder.toString();
	}

	/**
	 * turns `this.students` into XML format
	 * @return a formatted XML file
	 */
	public String toXML() {
		StringBuilder builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<students>\n");
		for (int i = 0; i < students.length; i++) {
			Student student = students[i];
			builder.append("<student>\n");

			builder.append("<name>");
			builder.append(student.name);
			builder.append("</name>\n");

			builder.append("<id>");
			builder.append(student.id);
			builder.append("</id>\n");

			builder.append("<gpa>");
			builder.append(student.gpa);
			builder.append("</gpa>\n");

			builder.append("</student>\n");
		}

		builder.append("</sudents>\n");
		return builder.toString();
	}

	/**
	 * turns `this.students` into JSON format
	 * @return a formatted JSON file
	 */
	public String toJSON() {
		StringBuilder builder = new StringBuilder("{\n\"students\": [\n\t");
		for (int i = 0; i < students.length; i++) {
			if (i != 0) builder.append(", \n\t");

			Student student = students[i];
			builder.append("{\"name\": \"");
			builder.append(student.name);
			builder.append("\", ");

			builder.append("\"id\": ");
			builder.append(student.id);
			builder.append(", ");

			builder.append("\"gpa\": ");
			builder.append(student.gpa);

			builder.append("}");
		}

		builder.append("]\n}\n");
		return builder.toString();
	}
}
