package com.testautomation.helpers;

import com.testautomation.config.Config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

public final class RequestSpecFactory {
    private RequestSpecFactory() {}

    public static RequestSpecification jsonSpec() {
        // Configure Apache HttpClient 4.x timeouts via RestAssured
        RestAssuredConfig cfg = RestAssuredConfig.config()
                .httpClient(
                        httpClientConfig()
                                .setParam("http.connection.timeout", Config.connectTimeoutMs())
                                .setParam("http.socket.timeout", Config.readTimeoutMs())
                );

        return new RequestSpecBuilder()
                .setBaseUri(Config.baseUri())
                .setConfig(cfg)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addFilter(new AllureRestAssured())
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .log(LogDetail.BODY)
                .build();
    }
}
