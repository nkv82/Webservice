package com.nv.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.nv.Track;
@Path("/json/metallica")
public class JSONService {
	Gson gson = new Gson();
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Track getTrackInJSON() {

		Track track = new Track();
		track.setTitle("Enter Sandman");
		track.setSinger("Metallica");

		return track;

	}

	@GET
	@Path("/jsonGetQueryParms")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers(
			@QueryParam("from") int from,
			@QueryParam("to") int to,
			@QueryParam("orderBy") List<String>  orderBy) {

		return Response
				.status(200)
				.entity(gson.toJson("getUsers is called, from : " + from + ", to : " + to
						+ ", orderBy " + orderBy)).build();

	}

	//Programmatic Query Parameter http://localhost:8080/Webservice/rest/hello/query2?from=1&to=10&orderBy=age&orderBy=name
	@GET
	@Path("/jsonGetContextURIInfo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers(@Context UriInfo info) {

		String from = info.getQueryParameters().getFirst("from");
		String to = info.getQueryParameters().getFirst("to");
		List<String> orderBy = info.getQueryParameters().get("orderBy");

		return Response
				.status(200)
				.entity(gson.toJson("getUsers is called, from : " + from + ", to : " + to
						+ ", orderBy " + orderBy.toString())).build();

	}

	//Multiple path params
		@GET
		@Path("{year}/{month}/{day}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getUserHistory(
				@PathParam("year") int year,
				@PathParam("month") int month, 
				@PathParam("day") int day) {

			String date = year + "/" + month + "/" + day;

			return Response.status(200)
					.entity(gson.toJson("getUserHistory is called, year/month/day : " + date))
					.build();

		}

		
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Track track) {
		String result = gson.toJson(track);
		//	String result =  track.toString();
		return Response.status(200).entity(result).build();

	}


}