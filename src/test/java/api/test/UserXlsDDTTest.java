package api.test;

import org.testng.Assert;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import api.dataproviders.UserDataProvider;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserXlsDDTTest {
	
//	public Logger logger;
	private static final Logger logger = LogManager.getLogger(UserXlsDDTTest.class);
	
	
	@Test(priority=1, dataProvider="Data", dataProviderClass = UserDataProvider.class )
	public void testPostuser(String userID, String userName,String fname,String lname,String useremail,String pwd,String ph)
	{
		User userPayload=new User();
		
		logger.info("..........................User creation started..............................");
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		logger.info("..........................User is created..............................");
		
		Response response= UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
			
	}

}
