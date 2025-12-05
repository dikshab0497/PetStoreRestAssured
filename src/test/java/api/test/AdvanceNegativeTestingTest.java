package api.test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class AdvanceNegativeTestingTest {

    String baseUrl = "https://petstore.swagger.io/v2";

    // -----------------------------
    // 1️⃣ RATE LIMIT TESTING
    // -----------------------------
    @Test
    public void testRateLimit() {
        for (int i = 1; i <= 12; i++) {
            Response res = 
                given()
                    .baseUri(baseUrl)
                .when()
                    .get("/user/someUser");

            System.out.println("Attempt " + i + " -> Status: " + res.getStatusCode());
        }

        // NOTE: PetStore doesn't implement rate limiting
        // But showing how it's tested everywhere
    }

    // -------------------------------------
    // 2️⃣ BOUNDARY VALUE TESTING (USERNAME)
    // -------------------------------------
    @Test
    public void testUsernameTooShort() {

        String shortUsernameJson = """
        {
            "id": 123,
            "username": "ab",  
            "firstName": "test",
            "lastName": "user",
            "email": "abc@test.com",
            "password": "12345",
            "phone": "12345"
        }
        """;

        Response res = 
            given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(shortUsernameJson)
            .when()
                .post("/user");

        assertTrue(res.statusCode() == 400 || res.statusCode() == 500);
    }

    @Test
    public void testUsernameTooLong() {

        String longUsername = "a".repeat(30);

        String longUsernameJson = """
        {
            "id": 456,
            "username": "%s",
            "firstName": "test",
            "lastName": "user",
            "email": "abc@test.com",
            "password": "12345",
            "phone": "12345"
        }
        """.formatted(longUsername);

        Response res = 
            given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(longUsernameJson)
            .when()
                .post("/user");

        assertTrue(res.statusCode() == 400 || res.statusCode() == 500);
    }

    // ----------------------------------
    // 3️⃣ INVALID TOKEN / UNAUTHORIZED
    // ----------------------------------
    @Test
    public void testInvalidToken() {
        Response res = 
            given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer WRONG_TOKEN_123")
            .when()
                .get("/user/someUser");

        // Expected for secured APIs
        assertTrue(res.statusCode() == 401 || res.statusCode() == 403);
    }

    // ---------------------------
    // 4️⃣ INVALID JSON FORMAT
    // ---------------------------
    @Test
    public void testInvalidJson() {

        String badJson = "{ \"username\": \"abc\", "; // broken JSON

        Response res = 
            given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(badJson)
            .when()
                .post("/user");

        assertTrue(res.statusCode() == 400 || res.statusCode() == 500);
    }

    // ----------------------------------------------
    // 5️⃣ WRONG HTTP METHOD (Testing 405)
    // ----------------------------------------------
    @Test
    public void testWrongHttpMethod() {

        Response res = 
            given()
                .baseUri(baseUrl)
            .when()
                .post("/user/login"); // login should be GET

        assertEquals(res.statusCode(), 405);
    }

    // -------------------------------------------
    // 6️⃣ SQL INJECTION / XSS SECURITY TEST
    // -------------------------------------------
    @Test
    public void testSqlInjection() {

        String maliciousJson = """
        {
            "id": 789,
            "username": "abc'; DROP TABLE Users; --",
            "firstName": "test",
            "lastName": "user",
            "email": "abc@test.com",
            "password": "12345",
            "phone": "12345"
        }
        """;

        Response res = 
            given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(maliciousJson)
            .when()
                .post("/user");

        assertTrue(res.statusCode() == 400 || res.statusCode() == 500);
    }

    // --------------------------------------------------
    // 7️⃣ OVERSIZED PAYLOAD (Payload too large)
    // --------------------------------------------------
    @Test
    public void testOversizedPayload() {

        String longString = "a".repeat(10000); // 10k chars

        String hugeJson = """
        {
            "id": 100,
            "username": "%s",
            "firstName": "big",
            "lastName": "payload",
            "email": "big@test.com",
            "password": "%s",
            "phone": "9999999999"
        }
        """.formatted(longString, longString);

        Response res = 
            given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(hugeJson)
            .when()
                .post("/user");

        assertTrue(res.statusCode() == 413 || res.statusCode() == 500 || res.statusCode() == 400);
    }
}

