package tests;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUsersTest {
        
        @Test(enabled = false)
        public void createusersMap() {

                // Define the user data
                List<Map<String, String>> users = new ArrayList<>();

                // User 1
                Map<String, String> user1 = new HashMap<>();
                
                user1.put("username", "reka");
                user1.put("email", "reka@gmail.com");
        
                // User 2
                Map<String, String> user2 = new HashMap<>();
                
                user2.put("username", "raja");
                user2.put("email", "raja@gmail.com");
        
                // User 3
                Map<String, String> user3 = new HashMap<>();
                
                user3.put("username", "rennu");
                user3.put("email", "rennu@gmail.com");
        
                // User 4
                Map<String, String> user4 = new HashMap<>();
                
                user4.put("username", "rani");
                user4.put("email", "rani@gmail.com");
        
                // User 5
                Map<String, String> user5 = new HashMap<>();
              
                user5.put("username", "raji");
                user5.put("email", "raji@gmail.com");
        
                // Add all users to the list
                users.add(user1);
                users.add(user2);
                users.add(user3);
                users.add(user4);
                users.add(user5);

            given()
                //.baseUri("http://localhost:3003")
                .baseUri("https://a96f4c0b-5254-44e5-8fcd-b3b54b294da6.mock.pstmn.io")
                .header("Content-type","application/json")
                .body(users)
            .when()
                .log().all()
                .post("/users")
            .then()
                .log().all()
                .statusCode(200)
                //.time(lessThan(3000L))
                .extract().response();               
        }


        @Test(priority = 2, dependsOnMethods = "tests.LoginTest.login")
        public void createUsers() {

        // Define the user data
        List<Map<String, String>> users = new ArrayList<>();

        // Adding multiple users to the list
        users.add(createUser("reka", "reka@gmail.com"));
        users.add(createUser("raja", "raja@gmail.com"));
        users.add(createUser("rennu", "rennu@gmail.com"));
        users.add(createUser("rani", "rani@gmail.com"));
        users.add(createUser("raji", "raji@gmail.com"));

        // Send POST request to create users
        List<Map<String, Object>> fetchUsers = given()
            //.baseUri("http://localhost:3003")
            .baseUri("https://a96f4c0b-5254-44e5-8fcd-b3b54b294da6.mock.pstmn.io")
            .header("Content-type", "application/json")
            .header("Authorization", "Bearer " + LoginTest.token) // Use token from LoginTest
            .body(users)
        .when()
            .log().all()
            .post("/users")
        .then()
            .log().all()
            .statusCode(201)
            .extract()
            .jsonPath()
            .getList(""); // Extract response as a list of users
    // Display users
    System.out.println("List of Users:");
    for (Map<String, Object> eachUser : fetchUsers) {
        System.out.println("ID: " + eachUser.get("id") + ", Username: " + eachUser.get("username") + ", Email: " + eachUser.get("email"));
    }

}

// Utility method to create a user map
private Map<String, String> createUser(String username, String email) {
    Map<String, String> user = new HashMap<>();
    user.put("username", username);
    user.put("email", email);
    return user;
}
}
//C:\Users\nreka\vscodedevops\API-Create-Users\apicreateusers> mvn dependency:copy-dependencies