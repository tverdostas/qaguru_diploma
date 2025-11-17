package ru.bitrix24.api.base;

import com.codeborne.selenide.Configuration;
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

        // Теперь можно безопасно использовать config для инициализации baseApiUrl
        // ВАЖНО: Убедитесь, что в вашем вызове gradle есть -DapiWebhook=ваш_вебхук
        // Если вы хотите использовать URL из AppConfig (например, apiWebhook или baseUrl)
        // вместо Configuration.baseUrl (который используется Selenide для UI), используйте:
        baseApiUrl = config.baseUrl() + "/rest/" + config.apiWebhook() + "/"; // Используем apiWebhook из AppConfig

        // Если же apiWebhook в AppConfig включает полный путь (например, .../rest/1/webhook/),
        // и вы хотите добавить к нему что-то, используйте:
        // baseApiUrl = config.apiWebhook() + "дополнительная_часть_пути/";

        // Если вы всё-таки хотите использовать baseUrl из AppConfig и добавить к нему apiWebhook:
        // baseApiUrl = config.baseUrl() + config.apiWebhook(); // Убедитесь, что формат URL корректен

        // Инициализируем RequestSpecification с вычисленным baseApiUrl
        defaultRequestSpec = new RequestSpecBuilder()
                .setBaseUri(baseApiUrl)
                .setContentType(JSON)
                .build()
                .config(newConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")));
    }
}
