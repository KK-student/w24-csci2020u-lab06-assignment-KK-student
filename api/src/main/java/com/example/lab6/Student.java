package com.example.lab6;

public class Student {

    public String name;    // the student's name
    public int id;         // the student's id
    public double gpa;     // the student's gpa

    // TO DO ...

    /**
     * the default constructor for the class
     * @param name  the student's name
     * @param id    the student's id
     * @param gpa   the student's gpa
     */
    public Student(String name, int id, double gpa) {}

    /**
     * takes some HTML and parses a `Student` from each `<tr>`
     * @param payload           the html payload
     * @return a `Student[]`
     * @throws RuntimeException throws if a student object returns true from `isEmpty()`
     */
    public static Student[] fromHTML(String payload) throws RuntimeException {}

    /**
     * this turns the object into string format: [Student name:name, id:id, gpa:gpa]
     * @return the object casted into string format
     */
    public String toString() {}

    /**
     * returns true if:
     * - the name is empty &
     * - isValidId() is false &
     * - isValidGPA() is false
     * @see #isValidId()
     * @see #isValidGPA()
     * @return true if all fields are empty
     */
    public boolean isEmpty() {}

    /**
     * tests if the student's id is valid or not
     * @return true if the id is greater than 100,000,000
     */
    public boolean isValidId() {}

    /**
     * tests if the student's gpa is valid or not
     * @return true if the gpa is between 0 and 4.3
     */
    public boolean isValidGPA() {}
}
