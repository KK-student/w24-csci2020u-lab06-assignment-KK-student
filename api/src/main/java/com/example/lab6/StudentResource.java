package com.example.lab6;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.nio.file.Files;

import java.io.*;
import java.util.Scanner;

@Path("/students")
public class StudentResource {

    /**
     * This function retrieves a file using Java's built-in reflection functions.
     * This is because Java doesn't look in the directory you think it does on start up, this
     * is a way of guaranteeing it will return the absolute path of the file you're trying to read from.
     * @param filename the name of the file
     * @return the file's contents
     */
    private String readFileContents(String filename) {
        /**
         * if there is no '/' at the beginning, the following function call will return `null`
         */
        String fileContents = "";
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                fileContents += data + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            return e.toString();
        }
        return fileContents;
    }

    // TO DO ...

    @GET
    @Produces("text/plain")
    public String main() {
        return "Hello World!\n";
    }

    @GET
    @Path("/json")
    //@Produces("application/json")
    public Response json() {
        final String FILE_PATH = "/home/kseniya/Documents/LabServerData/Lab3/resources/students.json";
        String fileContents = readFileContents(FILE_PATH);
				Response response;
        //return fileContents == null ? "NULL" : fileContents;
				if (fileContents != null) {
					response = Response.status(200)
								//.header("Access-Control-Allow-Origin", "*")
								.header("Content-Type", "application/json")
								.entity(fileContents)
								.build();
				} else {
					response = Response.status(404).build();
				}
				return response;
    }

    @GET
    @Path("/csv")
    //@Produces("text/csv")
    public Response csv() {
        final String FILE_PATH = "/home/kseniya/Documents/LabServerData/Lab3/resources/students.csv";
        String fileContents = readFileContents(FILE_PATH);
        //return fileContents == null ? "NULL" : fileContents;
				Response response; 
				if (fileContents != null) {
					response = Response.status(200)
								//.header("Access-Control-Allow-Origin", "*")
								.header("Content-Type", "text/csv")
								.entity(fileContents)
								.build();
				} else {
					response = Response.status(404).build();
				}
				return response;
    }

    @GET
    @Path("/xml")
    //@Produces("application/xml")
    public Response xml() {
        final String FILE_PATH = "/home/kseniya/Documents/LabServerData/Lab3/resources/students.xml";
        String fileContents = readFileContents(FILE_PATH);
        //return fileContents == null ? "NULL" : fileContents;
				Response response; 
				if (fileContents != null) {
					response = Response.status(200)
								//.header("Access-Control-Allow-Origin", "*")
								.header("Content-Type", "application/xml")
								.entity(fileContents)
								.build();
				} else {
					response = Response.status(404).build();
				}
				return response;
    };

}
