package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoint;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {

	
	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setupData() {
		
		
		faker=new Faker();
		userPayload= new User();
		
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	// *********** -------- Create User 1st Test case -------- ************* /
	
	
	@Test(priority = 1, enabled = true)
	public void testPostUser() {
			
		Response response=UserEndPoint.createUser(userPayload);
		
		response.then().log().body();
		response.then().statusCode(200);
		
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	
	
	// *********** -------- Read User 2nd Test case -------- ************* /
	
	
	@Test(priority = 2, enabled = true)
	public void testGetUserByName() {
		
		Response response=UserEndPoint.readUser(this.userPayload.getUsername());
		
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(),200);
		
		
	}
	
	
	// *********** -------- Update user 3rd Test case -------- ************* /
	
	@Test(priority = 3, enabled = true)
	public void testUpdateUser() {
		
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response response=UserEndPoint.updateUser(this.userPayload.getUsername(), userPayload);
		
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(),200);
		
		
		Response afterupdateresponse=UserEndPoint.readUser(this.userPayload.getUsername());
		
		afterupdateresponse.then().log().body();
		Assert.assertEquals(afterupdateresponse.getStatusCode(),200);
		
	}
	
	
	// *********** -------- Delete User 4th Test case -------- ************* /
	
	
		@Test(priority = 4,enabled = true)
		public void testDeleteUser() {
			
			Response response=UserEndPoint.deleteUser(this.userPayload.getUsername());
			
			response.then().log().body();
			Assert.assertEquals(response.getStatusCode(),200);
			
		}
	
	
}
