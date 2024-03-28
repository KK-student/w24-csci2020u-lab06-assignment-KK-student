package com.example.lab6;

import java.util.ArrayList;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class Student {

	public String name;	// the student's name
	public int id;		 // the student's id
	public double gpa;	 // the student's gpa

	// TO DO ...

	/**
	 * the default constructor for the class
	 * @param name  the student's name
	 * @param id	the student's id
	 * @param gpa   the student's gpa
	 */
	public Student(String name, int id, double gpa) {
		this.name = name;
		this.id = id;
		this.gpa = gpa;
	}

	boolean isValid() {
		return this.name != null && !this.name.isEmpty() && this.isValidGPA() && this.isValidId();
	}

	/**
	 * takes some HTML and parses a `Student` from each `<tr>`
	 * @param payload		   the html payload
	 * @return a `Student[]`
	 * @throws RuntimeException throws if a student object returns true from `isEmpty()`
	 */
	public static Student[] fromHTML(String payload) throws RuntimeException {
		/*
		Format: 
		<head>...</thead>
		<tbody>
			<tr>
				<td><p>Name</p></td>
				<td><p>ID</p></td>
				<td><p>gpa</p></td>
			</tr>
		</tbody>
		*/
		ArrayList<Student> studentsArrayList = new ArrayList<Student>();
		Element base = Jsoup.parse(payload);
		Element body = base.select("html").get(0).select("body").get(0);
		Elements pElements = body.select("p");
		if (pElements.size() % 3 != 0) throw new IllegalArgumentException(String.format("Invalid payload format: pElements.size() = %d. ", pElements.size()));
		for (int i = 0; i < pElements.size(); i += 3) {
			String nameString = pElements.get(i).text();
			String idString = pElements.get(i + 1).text();
			String gpaString = pElements.get(i + 2).text();
			studentsArrayList.add(new Student(nameString, Integer.parseInt(idString), Double.parseDouble(gpaString)));
		}

		Student[] result = null;
		if (studentsArrayList.size() > 0) {
			result = new Student[studentsArrayList.size()];
			System.out.println("Students: ");
			for (int i = 0; i < result.length; i++) {
				result[i] = studentsArrayList.get(i);
				System.out.printf("%d: %s. \n", i, studentsArrayList.get(i).toString());
			}
		}
		return result;

	}

	/**
	 * this turns the object into string format: [Student name:name, id:id, gpa:gpa]
	 * @return the object casted into string format
	 */
	public String toString() {
		return "[Student name:" + (this.name != null ? this.name : "<null>") + ", id:" + Integer.toString(this.id) + ", gpa:" + Double.toString(this.gpa) + "]";
	}

	/**
	 * returns true if:
	 * - the name is empty &
	 * - isValidId() is false &
	 * - isValidGPA() is false
	 * @see #isValidId()
	 * @see #isValidGPA()
	 * @return true if all fields are empty
	 */
	public boolean isEmpty() {
		return this.name.isEmpty() && !this.isValidId() && !this.isValidGPA();
	}

	/**
	 * tests if the student's id is valid or not
	 * @return true if the id is greater than 100,000,000
	 */
	public boolean isValidId() {
		return this.id > 100000000;
	}

	/**
	 * tests if the student's gpa is valid or not
	 * @return true if the gpa is between 0 and 4.3
	 */
	public boolean isValidGPA() {
		return this.gpa >= 0.0 && this.gpa <= 4.3;
	}
}
