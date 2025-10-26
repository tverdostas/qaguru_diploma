package ru.bitrix24.api.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import ru.bitrix24.config.AppConfig;

import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;
import static io.restassured.http.ContentType.JSON;

public class BaseApi {

    protected final static RequestSpecification defaultRequestSpec = new RequestSpecBuilder()
            .setBaseUri(AppConfig.getRestBaseUrl())
            .setContentType(JSON)
            .build()
            .config(newConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")));

}
