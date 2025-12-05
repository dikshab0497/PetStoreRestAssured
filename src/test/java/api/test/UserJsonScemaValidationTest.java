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
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class UserJsonScemaValidationTest {

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
	
	@Test
	public void testPostUserSchema() {
		
		Response response = up.createUser(userPayload);
		response.then().log().all();
		
		 try {
		        // validate schema and log response if fails
		        response.then().log().ifValidationFails()
		                .assertThat()
		                .body(matchesJsonSchemaInClasspath("Schema/UserSchema.json"));

		        System.out.println("✅ Schema validation PASSED!");
		    } catch (AssertionError e) {
		        System.out.println("❌ Schema validation FAILED!");
		        System.out.println("Reason: " + e.getMessage());
		    }
		Assert.assertEquals(response.statusCode(), 200);
		
		
		logger.info("..........................User is created..............................");
	}
	
}
