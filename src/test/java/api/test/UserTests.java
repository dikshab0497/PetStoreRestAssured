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

public class UserTests {
	
	Faker faker;
	User userPayload;
	UserEndPoints up = new UserEndPoints();
	public Logger logger; // for logs
	
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		userPayload=new User();
		
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
	public void testPostUser() {
		
		Response response = up.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		logger.info("..........................User is created..............................");
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		
		Response response = up.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		logger.info("..........................User Data fetched..............................");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
			
			userPayload.setFirstName(faker.name().firstName());
			userPayload.setLastName(faker.name().lastName());
			userPayload.setEmail(faker.internet().safeEmailAddress());
			
			Response response = up.updateUser(this.userPayload.getUsername(), userPayload);
			response.then().log().all();
			
			Assert.assertEquals(response.statusCode(), 200);
			
			logger.info("..........................User Data Updated..............................");
		}
	

	@Test(priority=4)
	public void testDeleteUserByName() {
			
			Response response = up.deleteUser(this.userPayload.getUsername());
			response.then().log().all();
			
			Assert.assertEquals(response.statusCode(), 200);
			
			logger.info("..........................User Data Deleted..............................");
		}
}
