package com.testautomation.tests;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.testautomation.helpers.RequestSpecFactory;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

@Tag("api")
public class BasicApiTest {

    @BeforeAll
    static void setup() {
        // Electronics store backend must be running on http://localhost:8080
    }

    @Test
    void test_get_products_returns_ok() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/api/products")
                .then().extract().response();

        assertThat(res.statusCode()).isEqualTo(200);
        assertThat(res.jsonPath().getList("$")).isNotEmpty();
    }

    @Test
    void test_get_product_by_id() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/api/products/1")
                .then().extract().response();

        assertThat(res.statusCode()).isEqualTo(200);
        assertThat(res.jsonPath().getString("id")).isNotEmpty();
    }

    @Test
    void test_get_non_existent_product() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/api/products/99999")
                .then().extract().response();

        // Verify 404 or similar error response
        assertThat(res.statusCode()).isGreaterThanOrEqualTo(400);
    }
}
