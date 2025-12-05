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
    @DisplayName("Create - POST a new order")
    @Description("Verify POST endpoint creates new order with correct status and ID")
    void test_create_order() {
        JsonObject body = new JsonObject();
        body.addProperty("productId", 1);
        body.addProperty("quantity", 2);
        body.addProperty("customerName", "Test Customer");
        body.addProperty("customerEmail", "test@example.com");

        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .body(body.toString())
                .when().post("/api/orders")
                .then().extract().response();

        assertThat(res.statusCode()).isIn(200, 201);
        assertThat(res.jsonPath().getInt("id")).isGreaterThan(0);
    }

    @Test
    @DisplayName("Read - GET existing order")
    @Description("Verify GET endpoint retrieves order with correct data")
    void test_read_order() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/api/orders/1")
                .then().extract().response();

        assertThat(res.statusCode()).isEqualTo(200);
        assertThat(res.jsonPath().getString("id")).isNotEmpty();
    }

    @Test
    @DisplayName("Update - PUT existing order")
    void test_update_order() {
        JsonObject body = new JsonObject();
        body.addProperty("quantity", 5);
        body.addProperty("status", "shipped");

        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .body(body.toString())
                .when().put("/api/orders/1")
                .then().extract().response();

        assertThat(res.statusCode()).isIn(200, 201);
    }

    @Test
    @DisplayName("Delete - DELETE existing order")
    void test_delete_order() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .when().delete("/api/orders/1")
                .then().extract().response();

        assertThat(res.statusCode()).isIn(200, 204);
    }

    @Test
    @DisplayName("Read All - GET list of orders")
    void test_read_all_orders() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/api/orders")
                .then().extract().response();

        assertThat(res.statusCode()).isEqualTo(200);
        assertThat(res.jsonPath().getList("$")).isNotEmpty();
    }

    @Test
    @DisplayName("Filter - GET products with query parameters")
    void test_get_products_by_category() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .queryParam("category", "electronics")
                .when().get("/api/products")
                .then().extract().response();

        assertThat(res.statusCode()).isEqualTo(200);
        assertThat(res.jsonPath().getList("$")).isNotEmpty();
    }
}
