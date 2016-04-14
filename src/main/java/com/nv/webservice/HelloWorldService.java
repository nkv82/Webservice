package com.nv.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/hello")
public class HelloWorldService {

	@GET
	@Path("/hi/{param}")
	public Response getMsg(@PathParam("param") String msg) {

		String output = "Jersey say : " + msg;

		return Response.status(200).entity(output).build();
	//dummy commit
	}

	@POST
	@Path("/insert/{id}")
	public Response insertData(@PathParam("id") int empid){
		String output = "New Employee record created: NE"+empid;
		return Response.status(200).entity(output).build();
	}

	@Path("/FeetToInch/{f}")  
	@GET  
	@Produces(MediaType.TEXT_XML)  
	public String convertFeetToInch(@PathParam("f") int f) {  
		int inch=0;  
		int feet = f;  
		inch = 12*feet;  

		return "<FeetToInchService>"  
		+ "<Feet>" + feet + "</Feet>"  
		+ "<Inch>" + inch + "</Inch>"  
		+ "</FeetToInchService>";  
	}  

	//@QueryParam example http://localhost:8080/Webservice/rest/hello/query2?from=1&to=10&orderBy=age&orderBy=name
	@GET
	@Path("/query1")
	public Response getUsers(
			@QueryParam("from") int from,
			@QueryParam("to") int to,
			@QueryParam("orderBy") List<String> orderBy) {

		return Response
				.status(200)
				.entity("getUsers is called, from : " + from + ", to : " + to
						+ ", orderBy" + orderBy.toString()).build();

	}

	//Programmatic Query Parameter http://localhost:8080/Webservice/rest/hello/query2?from=1&to=10&orderBy=age&orderBy=name
	@GET
	@Path("/query2")
	public Response getUsers(@Context UriInfo info) {

		String from = info.getQueryParameters().getFirst("from");
		String to = info.getQueryParameters().getFirst("to");
		List<String> orderBy = info.getQueryParameters().get("orderBy");

		return Response
				.status(200)
				.entity("getUsers is called, from : " + from + ", to : " + to
						+ ", orderBy" + orderBy.toString()).build();

	}

	//Multiple path params
	@GET
	@Path("{year}/{month}/{day}")
	public Response getUserHistory(
			@PathParam("year") int year,
			@PathParam("month") int month, 
			@PathParam("day") int day) {

		String date = year + "/" + month + "/" + day;

		return Response.status(200)
				.entity("getUserHistory is called, year/month/day : " + date)
				.build();

	}

	//@HeaderParam Example , /hello/get1“
	@GET
	@Path("/get1")
	public Response addUser(@HeaderParam("user-agent") String userAgent) {

		return Response.status(200)
				.entity("addUser is called, userAgent : " + userAgent)
				.build();

	}

	//@Context Example /hello/get2
	@GET
	@Path("/get2")
	public Response addUser(@Context HttpHeaders headers) {

		String userAgent = headers.getRequestHeader("user-agent").get(0);

		return Response.status(200)
				.entity("addUser is called, userAgent : " + userAgent)
				.build();

	}
}
