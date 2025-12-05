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

public class CreateUserWithArrayDataTest {
    
    public Logger logger; 
    UserEndPoints up = new UserEndPoints();
    User[] usersArray = new User[10];

    @BeforeClass
    public void setup() {
        logger = LogManager.getLogger(this.getClass());
    }

    
    public User generateRandomUser() {

        Faker faker = new Faker();
        User user = new User();

        user.setId(faker.idNumber().hashCode());
        user.setUsername(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().safeEmailAddress());
        user.setPassword(faker.internet().password(5, 10));
        user.setPhone(faker.phoneNumber().cellPhone());

        return user;
    }

    @Test(priority = 1)
    public void testPostUserWithArray() {

        

        for (int i = 0; i < usersArray.length; i++) {
            usersArray[i] = generateRandomUser();
        }

        Response response = up.createUserWithArray(usersArray);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
        System.out.println("Username1: " + usersArray[0].getUsername());
        System.out.println("Username2: " + usersArray[1].getUsername());

        logger.info("....................... Users created successfully .......................");
    }
    
    @Test(priority=2)
	public void testGetUserByName() {
		
    	 for (int i = 0; i < usersArray.length; i++) {
    		 Response response = up.readUser(this.usersArray[i].getUsername());
    			response.then().log().all();
    			
    			Assert.assertEquals(response.statusCode(), 200);
    			  			
    			logger.info("..........................User Data fetched..............................");
         }
	}
}
