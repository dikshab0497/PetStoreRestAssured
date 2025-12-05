package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.endpoints.UserEndPoints;
import api.payload.Store;
import api.payload.User;
import io.restassured.response.Response;

public class ComplexChainingTest {
	
	Faker faker;
	User userPayload;
	UserEndPoints up = new UserEndPoints();
	public Logger logger; // for logs
	Store storePayload;
    StoreEndPoints sp = new StoreEndPoints();
    long orderId;
	
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
		
		storePayload = new Store();

        storePayload.setOrderId(faker.idNumber().hashCode());
        storePayload.setPetId(101);
        storePayload.setQuantity(3);
        storePayload.setShipDate("2025-08-11T14:56:49.131Z");
        storePayload.setStatus("placed");
        storePayload.setCompleted(false);

		//logs
		logger= LogManager.getLogger(this.getClass());
		
		logger.debug("debugging.....");
		
	}
	
	@Test(priority=1)
	public void createUser() {
		
		Response response = up.createUser(userPayload);
		response.then().log().all();
		

		Assert.assertEquals(response.statusCode(), 200);
	
		logger.info("..........................User is created..............................");
	}
	
	@Test(priority=2)
	public void loginUser() {
		
		String username = userPayload.getUsername();
		String password = userPayload.getPassword();
		
		
		Response response = up.loginUser(username, password);
		response.then().log().all();
		

		Assert.assertEquals(response.statusCode(), 200);
	
		logger.info("..........................User able to login..............................");
	}

	@Test(priority = 3)
    public void createOrder() {
        Response response = sp.createPetOrder(storePayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        
      this.orderId = response.jsonPath().getLong("id"); // update the class-level variable
      storePayload.setOrderId(this.orderId);
      System.out.println("Generated Order ID = " + this.orderId);

      
      logger.info("..........................Order Created Succesfully..............................");
    }
	
	@Test(priority = 4)
    public void getOrderDeatils() {
//        Response response = sp.readPetOrder(this.storePayload.getOrderId());
        Response response = sp.readPetOrder(this.orderId);

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200, "Failed to retrieve order.");
        Assert.assertEquals(response.jsonPath().getInt("petId"), storePayload.getPetId());
        Assert.assertEquals(response.jsonPath().getInt("quantity"), storePayload.getQuantity());
        Assert.assertEquals(response.jsonPath().getString("status"), storePayload.getStatus());

        boolean actualComplete = response.jsonPath().getBoolean("complete");
        System.out.println("⚠️ Note: Expected complete: " + storePayload.isCompleted() + ", Actual: " + actualComplete);
        Assert.assertEquals(actualComplete, storePayload.isCompleted(), "Complete field mismatch");

        logger.info("..........................Order Details Verified Succesfully..............................");
    }

	@Test(priority=5)
	public void logoutUser() {
			
			Response response = up.logoutUser();
			response.then().log().all();
			
			Assert.assertEquals(response.statusCode(), 200);
			
			logger.info("..........................User Succesfully Logout..............................");
		}


}
