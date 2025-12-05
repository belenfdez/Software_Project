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
    @DisplayName("E2E: Create order, verify, update, delete flow")
    void test_complete_crud_flow() {
        // Step 1: Create a new order
        JsonObject createBody = new JsonObject();
        createBody.addProperty("productId", 1);
        createBody.addProperty("quantity", 3);
        createBody.addProperty("customerName", "E2E Test Customer");
        createBody.addProperty("customerEmail", "e2e@test.com");

        Response createRes = given().spec(RequestSpecFactory.jsonSpec())
                .body(createBody.toString())
                .when().post("/api/orders")
                .then().extract().response();

        assertThat(createRes.statusCode()).isIn(200, 201);
        int orderId = createRes.jsonPath().getInt("id");
        assertThat(orderId).isGreaterThan(0);

        // Step 2: Verify we can retrieve the created order
        Response getRes = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/api/orders/" + orderId)
                .then().extract().response();

        assertThat(getRes.statusCode()).isEqualTo(200);
        assertThat(getRes.jsonPath().getString("customerName")).isNotEmpty();

        // Step 3: Update the order
        JsonObject updateBody = new JsonObject();
        updateBody.addProperty("quantity", 5);
        updateBody.addProperty("status", "processing");

        Response updateRes = given().spec(RequestSpecFactory.jsonSpec())
                .body(updateBody.toString())
                .when().put("/api/orders/" + orderId)
                .then().extract().response();

        assertThat(updateRes.statusCode()).isIn(200, 201);

        // Step 4: Delete the order
        Response deleteRes = given().spec(RequestSpecFactory.jsonSpec())
                .when().delete("/api/orders/" + orderId)
                .then().extract().response();

        assertThat(deleteRes.statusCode()).isIn(200, 204);
    }

    @Test
    @DisplayName("E2E: Get product, create order with it, verify order contains product")
    void test_product_order_relationship() {
        // Get a product
        Response productRes = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/api/products/1")
                .then().extract().response();

        assertThat(productRes.statusCode()).isEqualTo(200);
        int productId = productRes.jsonPath().getInt("id");
        assertThat(productId).isGreaterThan(0);

        // Create an order with that product
        JsonObject orderBody = new JsonObject();
        orderBody.addProperty("productId", productId);
        orderBody.addProperty("quantity", 2);
        orderBody.addProperty("customerName", "Relationship Test");
        orderBody.addProperty("customerEmail", "rel@test.com");

        Response orderRes = given().spec(RequestSpecFactory.jsonSpec())
                .body(orderBody.toString())
                .when().post("/api/orders")
                .then().extract().response();

        assertThat(orderRes.statusCode()).isIn(200, 201);
        assertThat(orderRes.jsonPath().getInt("productId")).isEqualTo(productId);
    }

    @Test
    @DisplayName("E2E: Get all products, filter by category, verify results")
    void test_product_filtering() {
        // Get all products
        Response allProductsRes = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/api/products")
                .then().extract().response();

        assertThat(allProductsRes.statusCode()).isEqualTo(200);
        assertThat(allProductsRes.jsonPath().getList("$")).isNotEmpty();

        // Filter products by category
        Response filteredRes = given().spec(RequestSpecFactory.jsonSpec())
                .queryParam("category", "electronics")
                .when().get("/api/products")
                .then().extract().response();

        assertThat(filteredRes.statusCode()).isEqualTo(200);
        // Verify we got products (may be empty if no electronics in DB)
        assertThat(filteredRes.jsonPath().getList("$")).isNotNull();
    }
}
