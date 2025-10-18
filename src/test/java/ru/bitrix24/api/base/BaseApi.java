package ru.bitrix24.api.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public class BaseApi {

    protected final static RequestSpecification defaultRequestSpec = new RequestSpecBuilder()
            .setContentType(JSON)
            .build();

}
