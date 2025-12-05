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
@DisplayName("Negative API Tests")
public class NegativeApiTest {

    @Test
    @DisplayName("404 - Get non-existent order")
    void test_get_nonexistent_resource() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/api/orders/999999")
                .then().extract().response();

        assertThat(res.statusCode()).isIn(404, 400);
    }

    @Test
    @DisplayName("Invalid path - wrong endpoint")
    void test_invalid_endpoint() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/api/invalid/endpoint")
                .then().extract().response();

        assertThat(res.statusCode()).isIn(404, 400);
    }

    @Test
    @DisplayName("Create with invalid body")
    void test_create_with_empty_body() {
        JsonObject body = new JsonObject();
        // Missing required fields (productId, quantity, etc.)

        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .body(body.toString())
                .when().post("/api/orders")
                .then().extract().response();

        assertThat(res.statusCode()).isIn(400, 422);
    }

    @Test
    @DisplayName("Malformed JSON")
    void test_malformed_json() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .body("{invalid json")
                .when().post("/api/orders")
                .then().extract().response();

        assertThat(res.statusCode()).isIn(400, 500);
    }

    @Test
    @DisplayName("Delete non-existent order")
    void test_delete_nonexistent() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .when().delete("/api/orders/999999")
                .then().extract().response();

        assertThat(res.statusCode()).isIn(404, 400);
    }
}
