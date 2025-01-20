package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import api.payloads.User;

// UserEndPont.java
// Created for perform Create, read, Update , Delete requests the user API

public class PropertiesUserEndPoint2 {
	
//************** ----------- Properties Reader Method --------- ****************** //
	
	static ResourceBundle getPropertiesData() {
		
		ResourceBundle routesBundle= ResourceBundle.getBundle("routes"); // routes= reading data from .properties
		return routesBundle;
			
	}
	

//************** ----------- create user Method --------- ****************** //

	
	public static Response createUser(User payload) 
	{
		String post_url = getPropertiesData().getString("post_url");
		
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(post_url);
		
		return response;
			
		
		
	}
	

//************** ----------- Read user Method --------- ****************** //
	
	public static Response readUser(String userName) 
	{
		String get_url = getPropertiesData().getString("get_url");
		
		Response response=given()
				.pathParam("username", userName)
			
			
		.when()
			.get(get_url);
		
		return response;
			
		
	}


//************** ----------- Update user Method --------- ****************** //
	
	public static Response updateUser(String userName, User payload) 
	{
		String put_url = getPropertiesData().getString("update_url");
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
				
		.when()
			.put(put_url);
			
		return response;
				
			
	}
	
//************** ----------- Delete user Method --------- ****************** //

	public static Response deleteUser(String userName) 
	{
		String delete_url = getPropertiesData().getString("delete_url");
			
		Response response=given()
				
				.pathParam("username", userName)
		
				
		.when()
			.delete(delete_url);
			
		return response;
				
			
	}
		
		

		
	
	
}
