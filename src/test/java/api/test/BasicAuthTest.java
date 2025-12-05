package api.test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class BasicAuthTest {
	
	
    @Test
    public void basicAuth_Success() {
        given()
            .auth()
            .preemptive()    
            .basic("postman", "password")
        .when()
            .get("https://postman-echo.com/basic-auth")
        .then()
            .statusCode(200)
            .body("authenticated", equalTo(true))
            .log().headers();
    }

		
		

}
