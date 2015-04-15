package com.nv.client;
import static com.jayway.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.jayway.restassured.path.json.JsonPath.from;
import com.google.gson.Gson;
import com.nv.Track;
public class RestAssuredClient {
	static Gson gson = new Gson();
	static Track track;
	public static void main(String [] args){
	System.out.println("-----jsonGetQueryParms");		jsonGetQueryParms();
	System.out.println("-----jsonGetContextURIInfo");	jsonGetContextURIInfo();
	System.out.println("-----jsonGetPathParams");	jsonGetPathParams();
	System.out.println("-----jsonGetPathParams2");	jsonGetPathParams2();
	System.out.println("-----jsonPost");	jsonPost();
	System.out.println("-----jsonPostJsonPath"); jsonPostJsonPath();
	}
	
	public static void jsonGetQueryParms(){
		Map<String,Object> str= new HashMap<String, Object>();
		List<String> orderBy= new ArrayList<String>();
		orderBy.add("age");
		orderBy.add("name");
		str.put("from", 10);
		str.put("to",20);
		str.put("orderBy", orderBy);
	
		//RestAssured.defaultParser = Parser.JSON;
		String response = given().
				parameters(str).
				expect().statusCode(200).
				when().get("http://localhost:8080/Webservice/rest/json/metallica/jsonGetQueryParms").asString();
		//track=gson.fromJson( response, Track.class);
		System.out.println(response);
		

	}
	
	public static void jsonGetContextURIInfo(){
		Map<String,Object> str= new HashMap<String, Object>();
		str.put("from", 10);
		str.put("to",20);
		List<String> orderBy= new ArrayList<String>();
		orderBy.add("age");
		orderBy.add("name");
		//RestAssured.defaultParser = Parser.JSON;
		String response = given().
				queryParam("from",10).
				queryParam("to",30).
				queryParam("orderBy", "age").
				queryParam("orderBy","name").
				expect().statusCode(200).
				when().get("http://localhost:8080/Webservice/rest/json/metallica/jsonGetContextURIInfo").asString();
		//track=gson.fromJson( response, Track.class);
		System.out.println(response);
	}
	
	public static void jsonGetPathParams(){
		Map<String, Object> pathParam= new HashMap<String, Object>();
		pathParam.put("year",2015);
		pathParam.put("month",03);
		pathParam.put("day", 16);
		//RestAssured.defaultParser = Parser.JSON;
		String response = given().
				expect().statusCode(200).
				when().get("http://localhost:8080/Webservice/rest/json/metallica/{year}/{month}/{day}",pathParam).asString();
		//track=gson.fromJson( response, Track.class);
		System.out.println(response);
	}
	
	public static void jsonGetPathParams2(){
		
		//RestAssured.defaultParser = Parser.JSON;
		String response = given().
				pathParam("year",2015).
				pathParam("month",03).
				pathParam("day", 16).
				expect().statusCode(200).
				when().get("http://localhost:8080/Webservice/rest/json/metallica/{year}/{month}/{day}").asString();
		//track=gson.fromJson( response, Track.class);
		System.out.println(response);
	}
	public static void jsonPost(){
		String input = "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";
		//RestAssured.defaultParser = Parser.JSON;
		String response = given().
				body(input).
				contentType("application/json").
				expect().statusCode(200).
				when().post("http://localhost:8080/Webservice/rest/json/metallica/post").asString();


		track=gson.fromJson( response, Track.class);
		if(track.getSinger().equals("Metallica")){
			System.out.println(track);
		}
	}
	
	public static void jsonPostJsonPath(){
		String input = "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";
		//RestAssured.defaultParser = Parser.JSON;
		String response = given().
				body(input).
				contentType("application/json").
				expect().statusCode(200).
				when().post("http://localhost:8080/Webservice/rest/json/metallica/post").asString();


		String singer=from(response).getString("singer");
		System.out.println("Singer extracted is---> "+singer);
		if(singer.equals("Metallica")){
			System.out.println(track);
		}
	}
}
