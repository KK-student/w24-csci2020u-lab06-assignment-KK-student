# API Modifications

This is the main portion of the lab, here you are going to parse the raw data (e.g., the `<table>`) and turn it into objects.

## Modifications

All of the endpoints are given to you in the `APIFormatter.java` file.  
This is how the endpoints should flow:

1. if the `body` is empty, reply with error code `400`.
2. transform the `body` into `Student[]` using `Student.fromHTML(String)`
   1. if any of the `Student` objects are invalid, throw an error
   2. catch the error in the handler function and reply with an error code of `400`
3. transform the `Student[]` into whatever file format required
4. reply to the request with the file contents and a status code of `200`

Hint: You can use the [Jsoup](https://jsoup.org/cookbook/input/parse-document-from-string) library to parse the HTML table by adding a [dependency](https://jsoup.org/download) to it in your `pom.xml`. 

>Read more about HTTP response codes [here](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status).

You'll need to change the package from `com.example.lab5` to `com.example.lab6`.

You'll also need to remove any `Access-Control-Allow-Origin` headers set due to the given class `CORSEnabler.java`.
This will remove some potential bugs within the code.

### Prepping

First off, implement the `Student` class in `student.java`, the instructions and what the functions
should do are in the file.

```java
public class Student {
   // properties
   public String name;    // the student's name
   public int id;         // the student's id
   public double gpa;     // the student's gpa

   // constructor
   public Student(String name, int id, double gpa) {}

   // static methods
   public static Student[] fromHTML(String payload) throws RuntimeException {}

   // methods
   public String toString() {}
   public boolean isEmpty() {}
   public boolean isValidId() {}
   public boolean isValidGPA() {}
}
```

The purpose of this is to nicely encapsulate the data into an interface.

The main function of note is `fromHTML(String)`, this function should:

- take some HTML output (it must contain a set of `<tr>` elements)
- parse `Student` objects from the HTML
- validate each newly created object
  - if any objects fail the `isEmpty()` check, throw a `RuntimeException`
- return the `Student[]` array

### File Encoding

Then, to encode the `Student[]` into files you'll implement the class in
`FileFormatter.java`, see instructions in that file.

Hint: You will use String operations to convert `Student[]` array to CSV, JSON, XML format.

```java
public class FileFormatter {
   // properties
   private Student[] students;

   // constructor
   public FileFormatter(Student[] students) {}

   // methods
   public String toCSV(String delimiter) {}
   public String toXML() {}
   public String toJSON() {}
}
```

>Note: the format used is the same in lab 3.

With the return type, it's recommended to use a `String` object. You can use another datatype at
the discretion of your TA.

### The Server Response

Finally, after the data processing, the server should reply to the original request with:

- a status code of `200`
- the proper content-type header
- the file you've created as the body
