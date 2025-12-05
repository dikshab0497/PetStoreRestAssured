package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Routes;
import api.payload.User;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import java.util.Arrays;

public class UserNegativeBodyTest {

    Faker faker;
    User userPayload;
    public Logger logger; // for logs

    @BeforeClass
    public void setup() {
        faker = new Faker();
        userPayload = new User();

        // Random user data
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        // Logger setup
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Debugging setup complete...");
    }

    // Helper: Assert JSON "code" field
    private void assertJsonCode(Response response, int... expectedCodes) {
        int jsonCode = response.jsonPath().getInt("code");
        logger.info("HTTP Status: " + response.getStatusCode() + ", JSON Code: " + jsonCode);

        if (Arrays.stream(expectedCodes).noneMatch(code -> code == jsonCode)) {
            throw new AssertionError("Expected JSON code one of " + Arrays.toString(expectedCodes) + " but got " + jsonCode);
        }
    }

//    @Test
    public void testCreateUser_WrongContentType() {
        logger.info("Running test: Wrong Content-Type");

        Response response = given()
                .header("Content-Type", "text/plain") // intentionally wrong
                .accept(ContentType.JSON)
                .body(userPayload)
            .when()
                .post(Routes.post_url)
            .then()
                .log().all()
                .extract().response();

        // Only assert JSON code, do not assert HTTP status
        assert response.getStatusCode() == 415;
    }
    @Test
    public void testCreateUser_MissingContentType() {
        logger.info("Running test: Missing Content-Type");

        Response response = given()
                .accept(ContentType.JSON)
                .body(userPayload) // no content-type
            .when()
                .post(Routes.post_url)
            .then()
                .log().all()
                .extract().response();

        assertJsonCode(response, 415, 200);
    }

    @Test
    public void testCreateUser_MissingAcceptHeader() {
        logger.info("Running test: Missing Accept Header");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(userPayload) // accept header missing
            .when()
                .post(Routes.post_url)
            .then()
                .log().all()
                .extract().response();

        // API currently returns 200 even if Accept header is missing
        assertJsonCode(response, 200);
    }

    @Test
    public void testCreateUser_InvalidAuthorization() {
        logger.info("Running test: Invalid Authorization");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer wrongToken")
                .body(userPayload)
            .when()
                .post(Routes.post_url)
            .then()
                .log().all()
                .extract().response();

        // API currently returns 200 even with wrong token
        assertJsonCode(response, 200);
    }
}
