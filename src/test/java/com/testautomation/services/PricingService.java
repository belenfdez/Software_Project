package com.testautomation.services;

import com.testautomation.helpers.RequestSpecFactory;
import com.testautomation.pojos.DiscountRequest;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class PricingService {

    public Response applyDiscount(DiscountRequest body) {
        return given().spec(RequestSpecFactory.jsonSpec())
                .body(body)
                .when().post("/pricing/discount")
                .then().extract().response();
    }
}
