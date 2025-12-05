package com.testautomation.services;

import com.testautomation.helpers.RequestSpecFactory;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class InventoryService {

    public Response getInventory(String productId) {
        return given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/products/" + productId + "/inventory")
                .then().extract().response();
    }
}
