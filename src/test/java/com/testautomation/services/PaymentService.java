package com.testautomation.services;

import com.testautomation.helpers.RequestSpecFactory;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PaymentService {

    public Response payOrder(String orderId) {
        return given().spec(RequestSpecFactory.jsonSpec())
                .when().post("/payments/" + orderId)
                .then().extract().response();
    }
}
