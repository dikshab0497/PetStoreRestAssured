package api.test;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class JWTAuthTest {

    String baseUrl = "https://dummyjson.com";
    static String token;
    static String StatusCode;

    @Test(priority = 1)
    public void loginAndGenerateToken() {
    	System.out.println("===== Starting Login Test =====");
        Response response = given()
                .contentType("application/json")
                .body("{\"username\": \"emilys\", \"password\": \"emilyspass\"}")

                .when()
                .post(baseUrl + "/auth/login");
        
                response.then().log().all();

                System.out.println("Response status code: " + response.statusCode());
                token = response.jsonPath().getString("accessToken");
                System.out.println("Response body: " + response.jsonPath().getString("accessToken"));
        
 }

    @Test(priority = 2, dependsOnMethods = "loginAndGenerateToken")
    public void getUserProfileWithJWT() {
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(baseUrl + "/auth/me")
                .then()
                .statusCode(200)
                .log().all();
    }
}
