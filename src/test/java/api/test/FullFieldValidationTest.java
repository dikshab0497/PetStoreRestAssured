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

public class FullFieldValidationTest {

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
	public void testPostUserFullFieldF() {
		
		Response response = up.createUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.statusCode(), 200,"StatusCode Not Matching");
	
		logger.info("..........................User is created..............................");
	}
	@Test(priority=2)
	public void testGetUserByNameFullFieldVal() {
		
		Response response = up.readUser(this.userPayload.getUsername());
		response.then().log().all();
		

		String UserName = response.jsonPath().getString("username");
		String FirstName = response.jsonPath().getString("firstName");
		String LastName = response.jsonPath().getString("lastName");
		String Email = response.jsonPath().getString("email");
		String Password = response.jsonPath().getString("password");
		String Phone = response.jsonPath().getString("phone");
		
		

		Assert.assertEquals(response.statusCode(), 200,"StatusCode Not Matching");
		Assert.assertEquals(UserName, userPayload.getUsername(),"UserName Not Matching");
		Assert.assertEquals(FirstName, userPayload.getFirstName(),"FirstName Not Matching");
		Assert.assertEquals(LastName, userPayload.getLastName(),"LastName Not Matching");
		Assert.assertEquals(Email, userPayload.getEmail(),"Email Not Matching");
		Assert.assertEquals(Password, userPayload.getPassword(),"Password Not Matching");
		Assert.assertEquals(Phone, userPayload.getPhone(),"Phone Not Matching");
		Assert.assertEquals(0, userPayload.getUserStatus(),"Status Not Matching");
		
		logger.info("..........................User Data fetched..............................");
	}
}
