package api.test;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

	public class InternetChcekTest {

	    @Test
	    public void checkInternetConnection() {
	        given()
	            .when()
	            .get("https://reqres.in/api/users?page=2")
	            .then()
	            .statusCode(200)
	            .log().body();
	    }
	}

