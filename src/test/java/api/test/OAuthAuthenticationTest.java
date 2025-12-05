package api.test;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OAuthAuthenticationTest {

    String baseUrl = "https://gorest.co.in/public/v2";
    String oauthToken;

    // Step 1: Simulate getting OAuth2 token
    @Test(priority = 1)
    public void getAccessToken() {
        // In real scenario, you'd send client_id & client_secret to token endpoint
        // Here we simulate by directly assigning a valid GoRest token
        oauthToken = "bdd7b2da39387e4d71c02560eb64c1944157bb47df1e8d09e534a1e871a2182a"; // replace with your token

        System.out.println("Simulated OAuth2 Token: " + oauthToken);
    }

    // Step 2: Use the token to access protected resource
    @Test(priority = 2, dependsOnMethods = "getAccessToken")
    public void accessProtectedAPIWithToken() {
        Response response = given()
                .header("Authorization", "Bearer " + oauthToken)
                .when()
                .get(baseUrl + "/users");

        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0)) // validate response has users
                .log().all();

        System.out.println("Protected API accessed successfully with OAuth2 token!");
    }
}
