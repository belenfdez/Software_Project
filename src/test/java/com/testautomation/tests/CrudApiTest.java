package com.testautomation.tests;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonObject;
import com.testautomation.helpers.RequestSpecFactory;

import io.qameta.allure.Description;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

@Tag("api")
@DisplayName("CRUD API Tests")
public class CrudApiTest {

    @Test
    @DisplayName("Create - POST a new resource")
    @Description("Verify POST endpoint creates new resource with correct status and ID")
    void test_create_post() {
        JsonObject body = new JsonObject();
        body.addProperty("title", "Test Post");
        body.addProperty("body", "This is a test post");
        body.addProperty("userId", 1);

        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .body(body.toString())
                .when().post("/posts")
                .then().extract().response();

        assertThat(res.statusCode()).isEqualTo(201);
        assertThat(res.jsonPath().getString("title")).isEqualTo("Test Post");
        assertThat(res.jsonPath().getInt("id")).isGreaterThan(0);
    }

    @Test
    @DisplayName("Read - GET existing resource")
    @Description("Verify GET endpoint retrieves resource with correct data")
    void test_read_post() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/posts/1")
                .then().extract().response();

        assertThat(res.statusCode()).isEqualTo(200);
        assertThat(res.jsonPath().getString("id")).isEqualTo("1");
        assertThat(res.jsonPath().getString("title")).isNotEmpty();
    }

    @Test
    @DisplayName("Update - PUT existing resource")
    void test_update_post() {
        JsonObject body = new JsonObject();
        body.addProperty("id", 1);
        body.addProperty("title", "Updated Title");
        body.addProperty("body", "Updated body");
        body.addProperty("userId", 1);

        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .body(body.toString())
                .when().put("/posts/1")
                .then().extract().response();

        assertThat(res.statusCode()).isEqualTo(200);
        assertThat(res.jsonPath().getString("title")).isEqualTo("Updated Title");
    }

    @Test
    @DisplayName("Delete - DELETE existing resource")
    void test_delete_post() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .when().delete("/posts/1")
                .then().extract().response();

        assertThat(res.statusCode()).isIn(200, 204);
    }

    @Test
    @DisplayName("Read All - GET list of resources")
    void test_read_all_posts() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/posts")
                .then().extract().response();

        assertThat(res.statusCode()).isEqualTo(200);
        assertThat(res.jsonPath().getList("$")).isNotEmpty();
        assertThat(res.jsonPath().getList("$").size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Filter - GET with query parameters")
    void test_get_posts_by_user() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .queryParam("userId", 1)
                .when().get("/posts")
                .then().extract().response();

        assertThat(res.statusCode()).isEqualTo(200);
        assertThat(res.jsonPath().getList("$")).isNotEmpty();
    }
}
