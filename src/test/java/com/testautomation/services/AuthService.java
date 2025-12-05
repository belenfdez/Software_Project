package com.testautomation.services;

import com.testautomation.helpers.RequestSpecFactory;
import com.testautomation.pojos.LoginRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthService {

    public Response login(LoginRequest body) {
        return given().spec(RequestSpecFactory.jsonSpec())
                .body(body)
                .when().post("/auth/login")
                .then().extract().response();
    }
}
