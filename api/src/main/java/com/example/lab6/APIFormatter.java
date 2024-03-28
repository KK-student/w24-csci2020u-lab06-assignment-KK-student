package com.example.lab6;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path("/format")
public class APIFormatter {

	@GET
	@Path("/test")
	public Response test() {
		return Response.status(200).entity("Hello World!\n").header("Access-Control-Allow-Origin", "*").header("Content-Type", "text/plain").build();
	}

	@POST
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_HTML)
	public Response json(String body) {
		/*ResponseBuilder baseResponse = Response.status(404).header("Access-Control-Allow-Origin", "*");
		ResponseBuilder errorResponse = baseResponse.status(400);
		ResponseBuilder okayResponse = baseResponse.status(200);*/
		ResponseBuilder errorResponse = Response.status(400);
		ResponseBuilder okayResponse = Response.status(200).header("Access-Control-Allow-Origin", "*");
		try {
			Student[] students;
	
			// rejecting if the data is empty
			if (body.isEmpty()) {
				return errorResponse
						.entity("No data passed in the body.")
						.build();
			}

			/*
			try {
				 students = Student.fromHTML(body);
			} catch (RuntimeException e) {
				System.out.println(e);
				return errorResponse.entity("Bad data passed to the API\n" + e).build();
			}
			*/
			students = Student.fromHTML(body);


			//Check if student objects are valid. 
			for(int i = 0; i < students.length; i++) {
				Student student = students[i];
				if (!student.isValid()) return errorResponse.entity("Invalid Student object: " + student.toString() + ". \n").build();
			}

			String response = new FileFormatter(students).toJSON();

			return okayResponse
					.header("Content-Disposition", "attachment;filename=\"students.json\"")
					.entity(response)
					.build();
		} catch (Exception e) {
			/*StringBuilder message = new StringBuilder("An exception has occured: ");
			message.append(e);
			message.append("\nStack trace: \n");
			StackTraceElement[] stackTrace = e.getStackTrace();
			for (int i = 0; i < stackTrace.length; i++) {
				message.append(stackTrace[i].toString());
				message.append("\n");
			}
			return errorResponse.entity(message.toString()).build();*/
			System.err.println("An error has occurred: ");
			e.printStackTrace();
			return errorResponse.entity("An error has occurred. See log file. ").build();
		}
	}

	@POST
	@Path("/csv")
	@Produces("text/csv")
	@Consumes(MediaType.TEXT_HTML)
	public Response csv(String body) {
		ResponseBuilder baseResponse = Response.status(404).header("Access-Control-Allow-Origin", "*");
		ResponseBuilder errorResponse = baseResponse.status(400);
		ResponseBuilder okayResponse = baseResponse.status(200);
		try {
			Student[] students;

			// rejecting if the data is empty
			if (body.isEmpty()) {
				return errorResponse
						.entity("No data passed in the body.")
						.build();
			}

			try {
				students = Student.fromHTML(body);
			} catch (RuntimeException e) {
				System.out.println(e);
				return errorResponse.entity("Bad data passed to the API\n" + e).build();
			}

			//Check if student objects are valid. 
			for(int i = 0; i < students.length; i++) {
				Student student = students[i];
				if (!student.isValid()) return errorResponse.entity("Invalid Student object: " + student.toString() + ". \n").build();
			}

			String response = new FileFormatter(students).toCSV(",");

			return okayResponse
					.header("Content-Disposition", "attachment;filename=\"students.csv\"")
					.entity(response)
					.build();
		} catch (Exception e) {
			return errorResponse.entity("An exception has occured: \n" + e).build();
		}
	}

	@POST
	@Path("/xml")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.TEXT_HTML)
	public Response xml(String body) {
		ResponseBuilder baseResponse = Response.status(404).header("Access-Control-Allow-Origin", "*");
		ResponseBuilder errorResponse = baseResponse.status(400);
		ResponseBuilder okayResponse = baseResponse.status(200);
		try {
			Student[] students;

			// rejecting if the data is empty
			if (body.isEmpty()) {
				return errorResponse
						.entity("No data passed in the body.")
						.build();
			}

			try {
				students = Student.fromHTML(body);
			} catch (RuntimeException e) {
				System.out.println(e);
				return errorResponse.entity("Bad data passed to the API\n" + e).build();
			}

			//Check if student objects are valid. 
			for(int i = 0; i < students.length; i++) {
				Student student = students[i];
				if (!student.isValid()) return errorResponse.entity("Invalid Student object: " + student.toString() + ". \n").build();
			}

			String response = new FileFormatter(students).toXML();

			return okayResponse
					.header("Content-Disposition", "attachment;filename=\"students.xml\"")
					.entity(response)
					.build();
		} catch (Exception e) {
			return errorResponse.entity("An exception has occured: \n" + e).build();
		}
	}
}
