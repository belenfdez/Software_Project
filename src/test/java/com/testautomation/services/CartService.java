package com.testautomation.services;

import com.testautomation.helpers.RequestSpecFactory;
import com.testautomation.pojos.QuantityRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CartService {

    public Response addToCart(QuantityRequest body) {
        return given().spec(RequestSpecFactory.jsonSpec())
                .body(body)
                .when().post("/cart")
                .then().extract().response();
    }

    public Response getCart(String userId) {
        return given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/cart/" + userId)
                .then().extract().response();
    }
}
