package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;


public class UserNegativeTests {
	Faker faker;
	User userPayload;
	UserEndPoints up = new UserEndPoints();
	public Logger logger; // for logs
	
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		userPayload=new User();
		//random data
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger= LogManager.getLogger(this.getClass());
		
		logger.debug("debugging.....");
		
	}
	
	@Test(priority=1)
	public void testPostUserNegative() {
		
		Response response = up.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		logger.info("..........................User is created..............................");
	}
	
//	@Test(priority=2)
	public void testUser_NonExistingUserName() {
		
		Response response = up.readUser("NonExistentUser");
		response.then()
				.log()
				.all();
		
		int statusCode = response.getStatusCode();
		String message = response.jsonPath().getString("message");

			    // Assertions
		Assert.assertEquals(statusCode, 404, "Status code should be 404 for non-existing user");
		Assert.assertTrue(message.contains("User not found"), "Message should indicate user not found");
		
		
		logger.info("..........................User Data fetched..............................");
	}
	
//	@Test(priority=2)
	public void testUser_EmptyUserName() {
		
		Response response = up.readUser("");
		response.then()
				.log()
				.all();
		
		int statusCode = response.getStatusCode();

		// Assertions
		Assert.assertEquals(statusCode, 405, "Status code should be 405 for empty user");

		
		
		logger.info("..........................User Data fetched..............................");
	}


	@Test(priority=2)
	public void testUser_NumericUserName() {
		
		Response response = up.readUser("1234");
		response.then()
				.log()
				.all();
		
		int statusCode = response.getStatusCode();

		// Assertions
		Assert.assertEquals(statusCode, 404, "Status code should be 404 for Numeric user");

		
		
		logger.info("..........................User Data fetched..............................");
	}


	
}
