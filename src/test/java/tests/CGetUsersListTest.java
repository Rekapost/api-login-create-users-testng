package tests;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CGetUsersListTest {

    // This will store the userId to be used in other test cases
    private static String userId;

    @Test(priority = 3, dependsOnMethods = "tests.ALoginTest.login")
    public void getUsersAndStoreId() {
        // Get the list of users from the API
        List<Map<String, Object>> usersList = given()
            .baseUri("https://a96f4c0b-5254-44e5-8fcd-b3b54b294da6.mock.pstmn.io")
            .header("Content-type", "application/json")
            .header("Authorization", "Bearer " + ALoginTest.token) // Use token from ALoginTest
        .when()
            .log().all()
            .get("/users")
        .then()
            .log().all()
            .statusCode(200)
            .extract()
            .jsonPath()
            .getList(""); // Extract list of users

        // Find the user with the specific email
        boolean userFound = false;
        for (Map<String, Object> user : usersList) {
            if ("rani@gmail.com".equals(user.get("email"))) {
                // Store the user ID for later use in other tests
                userId = user.get("id").toString();
                System.out.println("Stored User ID: " + userId);
                userFound = true;
                break;
            }
        }

        // If the user was not found, print a message
        if (!userFound) {
            System.out.println("User with email 'rani@gmail.com' not found.");
        }
    }

    // Getter method to retrieve the stored user ID in other classes/tests
    public static String getUserId() {
        return userId;
    }

    // Optional: If you need to manually set the userId (in case of mocking or manually injecting values)
    public static void setUserId(String id) {
        userId = id;
    }
}
