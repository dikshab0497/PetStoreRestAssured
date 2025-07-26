//package api.test;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.github.javafaker.Faker;
//
//import api.endpoints.StoreEndPoints;
//import io.restassured.response.Response;
//import api.payload.Store;
//
//public class StoreTests {
//	
//	Faker faker;
//	Store storePayload;
//	StoreEndPoints sp = new StoreEndPoints();
//	public Logger logger; // for logs
//	
//	@BeforeClass
//	public void setup()
//	{
//		faker=new Faker();
//		storePayload=new Store();
//		
//	
//		storePayload.setOrderId(faker.idNumber().hashCode());
//		storePayload.setPetId(101);
//		storePayload.setQuantity(3);
//		storePayload.setShipDate("2025-08-11T14:56:49.131Z");
//		storePayload.setStatus("placed");
//		storePayload.setCompleted(true);
//		//logs
//		logger= LogManager.getLogger(this.getClass());
//		
//		logger.debug("debugging.....");
//		
//	}
//	
//	@Test(priority=1)
//	public void testPostOrder() {
//		
//		Response response = sp.createPetOrder(storePayload);
//		response.then().log().all();
//		
//		Assert.assertEquals(response.statusCode(), 200);
//		
//		logger.info("..........................Order is created..............................");
//		
//	}
//	
//	@Test(priority=2)
//	public void testGetOrder() {
//		Response response = sp.readPetOrder(this.storePayload.getOrderId());
//		response.then().log().all();
//		
//		Assert.assertEquals(response.statusCode(), 200);
//		
//		logger.info("..........................Order is display..............................");
//		
//	}
//	
//	@Test(priority=3)
//	public void testDeleteOrder() {
//		Response response = sp.deletePetOrder(this.storePayload.getOrderId());
//		response.then().log().all();
//		
//		Assert.assertEquals(response.statusCode(), 200);
//		
//		logger.info("..........................Order is deleted..............................");
//		
//	}
//	
//	@Test(priority = 4)
//	public void testGetAfterDelete() {
//	    Response response = sp.readPetOrder(this.storePayload.getOrderId());
//	    response.then().log().all();
//
//	    Assert.assertEquals(response.statusCode(), 404); // Not found
//	    logger.info("..........................Order is confirmed deleted..............................");
//	}
//
//	
//
//}

package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import io.restassured.response.Response;
import api.payload.Store;

public class StoreTests {

    Faker faker;
    Store storePayload;
    StoreEndPoints sp = new StoreEndPoints();
    public Logger logger;
    long orderId;

    @BeforeClass
    public void setup() {
        faker = new Faker();
        storePayload = new Store();

        storePayload.setOrderId(faker.idNumber().hashCode());
        storePayload.setPetId(101);
        storePayload.setQuantity(3);
        storePayload.setShipDate("2025-08-11T14:56:49.131Z");
        storePayload.setStatus("placed");
        storePayload.setCompleted(false);

        logger = LogManager.getLogger(this.getClass());
        logger.debug("Test data initialized.");
    }

    @Test(priority = 1)
    public void testPostOrder() {
        Response response = sp.createPetOrder(storePayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200, "Order creation failed.");
        
      this.orderId = response.jsonPath().getLong("id"); // update the class-level variable
      storePayload.setOrderId(this.orderId);

      
        logger.info("✅ Order created successfully.");
    }


    @Test(priority = 2)
    public void testGetOrder() {
        Response response = sp.readPetOrder(this.storePayload.getOrderId());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200, "Failed to retrieve order.");
        Assert.assertEquals(response.jsonPath().getInt("petId"), storePayload.getPetId());
        Assert.assertEquals(response.jsonPath().getInt("quantity"), storePayload.getQuantity());
        Assert.assertEquals(response.jsonPath().getString("status"), storePayload.getStatus());

        boolean actualComplete = response.jsonPath().getBoolean("complete");
        System.out.println("⚠️ Note: Expected complete: " + storePayload.isCompleted() + ", Actual: " + actualComplete);

        logger.info("📦 Order details verified.");
    }

    @Test(priority = 3)
    public void testDeleteOrder() {
        Response response = sp.deletePetOrder(this.storePayload.getOrderId());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200, "Order deletion failed.");
        logger.info("🗑️ Order deleted successfully.");
    }

    @Test(priority = 4)
    public void testGetAfterDelete() {
        Response response = sp.readPetOrder(this.storePayload.getOrderId());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 404, "Order should not exist after deletion.");
        logger.info("✅ Order deletion confirmed (not found).");
    }
}

