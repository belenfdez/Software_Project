package com.testautomation.services;

import com.testautomation.helpers.RequestSpecFactory;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class PaymentService {

    public Response payOrder(String orderId) {
        return given().spec(RequestSpecFactory.jsonSpec())
                .when().post("/payments/" + orderId)
                .then().extract().response();
    }
}
