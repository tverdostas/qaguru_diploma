package ru.bitrix24.api.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import ru.bitrix24.config.AppConfig;

import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;
import static io.restassured.http.ContentType.JSON;

public class BaseApi {
    protected static AppConfig config;
    private static final String baseApiUrl; // Объявляем, но не инициализируем сразу
    protected final static RequestSpecification defaultRequestSpec; // Объявляем, но не инициализируем сразу

    // Статический блок инициализации - гарантирует порядок инициализации
    static {
        // Сначала создаем и инициализируем config из системных свойств
        config = ConfigFactory.create(AppConfig.class, System.getProperties());

        baseApiUrl = config.baseUrl() + "/rest/" + config.apiWebhook() + "/"; // Используем apiWebhook из AppConfig

        // Инициализируем RequestSpecification с вычисленным baseApiUrl
        defaultRequestSpec = new RequestSpecBuilder()
                .setBaseUri(baseApiUrl)
                .setContentType(JSON)
                .build()
                .config(newConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")));
    }
}
