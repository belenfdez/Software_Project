package com.testautomation.services;

import com.testautomation.helpers.RequestSpecFactory;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductService {

    public Response getAllProducts() {
        return given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/products")
                .then().extract().response();
    }

    public Response getProduct(String productId) {
        return given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/products/" + productId)
                .then().extract().response();
    }

    public Response getInventory(String productId) {
        return given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/products/" + productId + "/inventory")
                .then().extract().response();
    }
}
