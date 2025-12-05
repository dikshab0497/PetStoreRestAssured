package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.dataproviders.UserDataProvider;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserDataProviderTest {
	
	UserEndPoints up = new UserEndPoints();
	public Logger logger; // for logs
	User userPayload;
	Faker faker;
	
	@BeforeClass
    public void setup() {
        faker = new Faker();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("debugging.....");
    }
	
	
	@Test(priority=1,dataProvider = "userData",dataProviderClass = UserDataProvider.class )
	public void testPostUser(String username, String firstName, String lastName, String email) {
		
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(username);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(email);
		
		Response response = up.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		logger.info("✅ User created: " + username);
		
		logger.info("..........................User is created..............................");
	}
	
}
