package com.example.lab6;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.example.lab6.Student;

@Path("/format")
public class APIFormatter {

    @POST
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_HTML)
    public Response json(String body) {
        Student[] students;

        // rejecting if the data is empty
        if (body.isEmpty()) {
            return Response.status(400)
                    .entity("No data passed in the body.")
                    .build();
        }

        try {
             students = Student.fromHTML(body);
        } catch (RuntimeException e) {
            System.out.println(e);
            return Response.status(400).entity("Bad data passed to the API\n" + e).build();
        }

        String response = new FileFormatter(students).toJSON();

        return Response.status(200)
                .header("Content-Disposition", "attachment;filename=\"students.json\"")
                .entity(response)
                .build();
    }

    @POST
    @Path("/csv")
    @Produces("text/csv")
    @Consumes(MediaType.TEXT_HTML)
    public Response csv(String body) {
        Student[] students;

        // rejecting if the data is empty
        if (body.isEmpty()) {
            return Response.status(400)
                    .entity("No data passed in the body.")
                    .build();
        }

        try {
            students = Student.fromHTML(body);
        } catch (RuntimeException e) {
            System.out.println(e);
            return Response.status(400).entity("Bad data passed to the API\n" + e).build();
        }

        String response = new FileFormatter(students).toCSV(",");

        return Response.status(200)
                .header("Content-Disposition", "attachment;filename=\"students.csv\"")
                .entity(response)
                .build();
    }

    @POST
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.TEXT_HTML)
    public Response xml(String body) {
        Student[] students;

        // rejecting if the data is empty
        if (body.isEmpty()) {
            return Response.status(400)
                    .entity("No data passed in the body.")
                    .build();
        }

        try {
            students = Student.fromHTML(body);
        } catch (RuntimeException e) {
            System.out.println(e);
            return Response.status(400).entity("Bad data passed to the API\n" + e).build();
        }

        String response = new FileFormatter(students).toXML();

        return Response.status(200)
                .header("Content-Disposition", "attachment;filename=\"students.xml\"")
                .entity(response)
                .build();
    }
}
