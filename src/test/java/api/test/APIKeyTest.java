package api.test;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APIKeyTest {

    String baseUrl = "https://gorest.co.in/public/v2";
    String apiToken = "bdd7b2da39387e4d71c02560eb64c1944157bb47df1e8d09e534a1e871a2182a"; // your GoRest token

    @Test
    public void getUsersWithApiToken() {
        Response response = given()
                .header("Authorization", "Bearer " + apiToken)
                .when()
                .get(baseUrl + "/users");

        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0))  // At least 1 user should exist
                .log().all();

        System.out.println("Response status code: " + response.statusCode());
    }
}
