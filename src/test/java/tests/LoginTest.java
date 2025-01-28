package tests;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTest{
    public static String token; // Static token for reuse in other tests
         @Test(priority = 1)
        public void login() {
            // Login credentials
        Map<String, String> loginData = new HashMap<>();
        loginData.put("uname", "reka12"); // Replace with valid credentials
        loginData.put("psw", "reka123");
        loginData.put("remember", "on");
        // Sending a POST request to the login page
        token = given()
                .baseUri("https://a96f4c0b-5254-44e5-8fcd-b3b54b294da6.mock.pstmn.io")
                .header("Content-type", "application/json")
                //.body("{ \"uname\": \"reka12\", \"psw\": \"reka123\", \"remember\": \"on\" }")
                .body(loginData)
            .when()
                .log().all()
                .post("/logintoken")
            .then()
                .log().all()
                .statusCode(200)  // Assert that the status code is 200 OK
                .extract().response()  // Capture the response for later validation
                .path("token");

                // Validate token
            assert token != null && !token.isEmpty() : "Login failed: Token not retrieved!";
            System.out.println("Token retrieved: " + token);
            }
}