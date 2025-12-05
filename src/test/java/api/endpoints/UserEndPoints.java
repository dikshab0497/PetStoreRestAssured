package api.endpoints;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;

import api.payload.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class UserEndPoints {
	

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
	
	public static Response readUser(String userName)
	{
		Response response=given()
			.pathParam("username", userName)
		.when()
			.get(Routes.get_url);
			
		return response;
	}
	
	public static Response updateUser(String userName,User payload)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
		.when()
			.put(Routes.update_url);
			
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response=given()
			.pathParam("username", userName)
		.when()
			.delete(Routes.delete_url);
			
		return response;
	}
	
	public static Response loginUser(String userName, String password) {
	    return given()
	            .queryParam("username", userName)
	            .queryParam("password", password)
	        .when()
	            .get(Routes.getLogin_url);
	}
	
	public static Response logoutUser() {
	    return given()
	        .when()
	            .get(Routes.getLogout_url);
	}

	public static Response createUserWithArray(User[] usersArray)
	{
		Response response=given() 
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(usersArray)
		.when()
			.post(Routes.postWithArray_url);
			
		return response;
	}
	
	
	
	
	   
	
}
