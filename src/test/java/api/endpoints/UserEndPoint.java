package api.endpoints;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import api.payloads.User;

// UserEndPont.java
// Created for perform Create, read, Update , Delete requests the user API

public class UserEndPoint {
	


//************** ----------- create user Method --------- ****************** //

	public static Response createUser(User payload) 
	{
		
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(Routes.post_url);
		
		return response;
			
		
		
	}
	

//************** ----------- Read user Method --------- ****************** //
	
	public static Response readUser(String userName) 
	{
		
		Response response=given()
				.pathParam("username", userName)
			
			
		.when()
			.get(Routes.get_url);
		
		return response;
			
		
	}


//************** ----------- Update user Method --------- ****************** //
	
	public static Response updateUser(String userName, User payload) 
	{
			
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
				
		.when()
			.put(Routes.put_url);
			
		return response;
				
			
	}
	
//************** ----------- Delete user Method --------- ****************** //

	public static Response deleteUser(String userName) 
	{
			
		Response response=given()
				
				.pathParam("username", userName)
		
				
		.when()
			.delete(Routes.delete_url);
			
		return response;
				
			
	}
		
		

		
	
	
}
