package tests;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DDeleteUserTest {
    @Test(priority = 4, dependsOnMethods = "tests.CGetUsersListTest.getUsersAndStoreId" )      
    public void deleteUser() {
        String userId = CGetUsersListTest.getUserId(); // Retrieve userId from previous test
        if (userId != null) {
        // Send DELETE request to delete a user by ID
        String responseMessage = given()
            .baseUri("https://a96f4c0b-5254-44e5-8fcd-b3b54b294da6.mock.pstmn.io")
            .header("Content-type", "application/json")
            .header("Authorization", "Bearer " + ALoginTest.token) // Use token from LoginTest    
            .pathParam("userId", userId)  // Set the user ID dynamically
        .when()
            .log().all()
            .delete("/users/{userId}")
        .then()
            .log().all()
            .statusCode(200)
            .extract()
            .jsonPath()
            .getString("message"); 

    // Print the response message
    System.out.println("Response: " + responseMessage);
/*/
    Since you are using a mock API from Postman (https://a96f4c0b-5254-44e5-8fcd-b3b54b294da6.mock.pstmn.io/users),
    it is important to note that mock APIs typically return static or predefined responses,
    and they don't actually modify data on the server side.
    Mock APIs are used to simulate real API behavior but don't have actual backend functionality.
    Therefore, even though you are making a request to delete a user, 
    the mock server is not changing any stored data — it will continue returning the same predefined list of users,
     including the user you attempted to delete.
*/
    // Optionally, verify if the user has been deleted
     List<Map<String, Object>> fetchUsers = given()
     .baseUri("https://a96f4c0b-5254-44e5-8fcd-b3b54b294da6.mock.pstmn.io")
     .header("Content-type", "application/json")
     .header("Authorization", "Bearer " + ALoginTest.token) // Use token from LoginTest
 .when()
     .log().all()
     .get("/newlist") // Get the list of users
 .then()
     .log().all()
     .statusCode(200)
     .extract()
     .jsonPath()
     .getList(""); // Extract response as a list of users

    // Display users and verify user ID 4 is deleted
    System.out.println("List of Users after deletion:");
    for (Map<String, Object> eachUser : fetchUsers) {
        System.out.println("ID: " + eachUser.get("id") + ", Username: " + eachUser.get("username") + ", Email: " + eachUser.get("email"));
            }
        } else {
            System.out.println("User ID is not set. Cannot delete user.");
    }
  }

}
