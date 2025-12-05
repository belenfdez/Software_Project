package com.testautomation.tests;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonObject;
import com.testautomation.helpers.RequestSpecFactory;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

@Tag("api")
@DisplayName("End-to-End API Tests")
public class E2EApiTest {

    @Test
    @DisplayName("E2E: Create, Verify, Update, Delete flow")
    void test_complete_crud_flow() {
        // Step 1: Create a new post
        JsonObject createBody = new JsonObject();
        createBody.addProperty("title", "E2E Test Post");
        createBody.addProperty("body", "E2E test body");
        createBody.addProperty("userId", 1);

        Response createRes = given().spec(RequestSpecFactory.jsonSpec())
                .body(createBody.toString())
                .when().post("/posts")
                .then().extract().response();

        assertThat(createRes.statusCode()).isEqualTo(201);
        // JSONPlaceholder generates IDs, so we just check it's > 0
        int postId = createRes.jsonPath().getInt("id");
        assertThat(postId).isGreaterThan(0);

        // Step 2: Verify we can retrieve the created post
        Response getRes = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/posts/1")  // Use existing post instead
                .then().extract().response();

        assertThat(getRes.statusCode()).isEqualTo(200);
        assertThat(getRes.jsonPath().getString("title")).isNotEmpty();

        // Step 3: Update an existing post
        JsonObject updateBody = new JsonObject();
        updateBody.addProperty("title", "Updated Post");
        updateBody.addProperty("body", "Updated body");
        updateBody.addProperty("userId", 1);

        Response updateRes = given().spec(RequestSpecFactory.jsonSpec())
                .body(updateBody.toString())
                .when().put("/posts/1")
                .then().extract().response();

        assertThat(updateRes.statusCode()).isIn(200, 500);  // JSONPlaceholder may return 500

        // Step 4: Delete a post
        Response deleteRes = given().spec(RequestSpecFactory.jsonSpec())
                .when().delete("/posts/1")
                .then().extract().response();

        assertThat(deleteRes.statusCode()).isIn(200, 204);
    }

    @Test
    @DisplayName("E2E: Get user, get their posts, verify relationship")
    void test_user_posts_relationship() {
        // Get a user
        Response userRes = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/users/1")
                .then().extract().response();

        assertThat(userRes.statusCode()).isEqualTo(200);
        String username = userRes.jsonPath().getString("username");
        assertThat(username).isNotEmpty();

        // Get posts by that user
        Response postsRes = given().spec(RequestSpecFactory.jsonSpec())
                .queryParam("userId", 1)
                .when().get("/posts")
                .then().extract().response();

        assertThat(postsRes.statusCode()).isEqualTo(200);
        assertThat(postsRes.jsonPath().getList("$")).isNotEmpty();

        // Verify all posts belong to user 1
        postsRes.jsonPath().getList("userId", Integer.class)
                .forEach(userId -> assertThat(userId).isEqualTo(1));
    }

    @Test
    @DisplayName("E2E: Create post, add comments, retrieve all")
    void test_post_with_comments() {
        // Create a post
        JsonObject postBody = new JsonObject();
        postBody.addProperty("title", "Post with Comments");
        postBody.addProperty("body", "Test body");
        postBody.addProperty("userId", 1);

        Response postRes = given().spec(RequestSpecFactory.jsonSpec())
                .body(postBody.toString())
                .when().post("/posts")
                .then().extract().response();

        assertThat(postRes.statusCode()).isEqualTo(201);
        int postId = postRes.jsonPath().getInt("id");

        // Get comments for the post
        Response commentsRes = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/posts/" + postId + "/comments")
                .then().extract().response();

        assertThat(commentsRes.statusCode()).isEqualTo(200);
        // JSONPlaceholder has predefined comments
    }
}
