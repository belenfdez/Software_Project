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
        // Use public API for testing
    }

    @Test
    void test_get_posts_returns_ok() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/posts")
                .then().extract().response();

        assertThat(res.statusCode()).isEqualTo(200);
        assertThat(res.jsonPath().getList("$")).isNotEmpty();
    }

    @Test
    void test_get_post_by_id() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/posts/1")
                .then().extract().response();

        assertThat(res.statusCode()).isEqualTo(200);
        assertThat(res.jsonPath().getString("id")).isEqualTo("1");
    }

    @Test
    void test_get_non_existent_post() {
        Response res = given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/posts/99999")
                .then().extract().response();

        // JSONPlaceholder returns 200 but empty object
        assertThat(res.statusCode()).isIn(200, 404);
    }
}
