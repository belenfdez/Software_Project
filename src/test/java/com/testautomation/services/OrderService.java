package com.testautomation.services;

import com.testautomation.helpers.RequestSpecFactory;
import com.testautomation.pojos.OrderRequest;
import com.testautomation.pojos.OrderStatusRequest;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class OrderService {

    public Response createOrder(OrderRequest body) {
        return given().spec(RequestSpecFactory.jsonSpec())
                .body(body)
                .when().post("/orders")
                .then().extract().response();
    }

    public Response getOrder(String orderId) {
        return given().spec(RequestSpecFactory.jsonSpec())
                .when().get("/orders/" + orderId)
                .then().extract().response();
    }

    public Response updateStatus(OrderStatusRequest body) {
        return given().spec(RequestSpecFactory.jsonSpec())
                .body(body)
                .when().put("/orders/status")
                .then().extract().response();
    }

    public Response deleteOrder(String orderId) {
        return given().spec(RequestSpecFactory.jsonSpec())
                .when().delete("/orders/" + orderId)
                .then().extract().response();
    }
}
