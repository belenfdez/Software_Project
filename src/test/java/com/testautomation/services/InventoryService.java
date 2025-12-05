package com.testautomation.services;

import com.testautomation.helpers.RequestSpecFactory;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class InventoryService {

    public Response getInventory(String productId) {
        return given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/products/" + productId + "/inventory")
                .then().extract().response();
    }
}
