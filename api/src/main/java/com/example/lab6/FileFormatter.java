package com.example.lab6;

public class FileFormatter {

    // storing the students in an array
    private Student[] students;

    // TO DO ...

    /**
     * stores a list of Student objects in an instance
     * @param students the list of student objects
     */
    public FileFormatter(Student[] students) {}

    /**
     * turns `this.students` into CSV format
     * @param   delimiter   the character that separates each field
     * @return a formatted CSV file
     */
    public String toCSV(String delimiter) {}

    /**
     * turns `this.students` into XML format
     * @return a formatted XML file
     */
    public String toXML() {}

    /**
     * turns `this.students` into JSON format
     * @return a formatted JSON file
     */
    public String toJSON() {}
}
