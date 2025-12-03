package ru.bitrix24.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:application.properties", // Основной источник
        "system:properties"                 // Позволяет переопределять через -Dkey=value
})

public interface AppConfig extends Config {

    @Key("base.url")
    @DefaultValue("https://b24-ql072f.bitrix24.ru")
    String baseUrl();

    @Key("user.login")
    String userLogin();

    @Key("user.password")
    String userPassword();

    @Key("api.webhook")
    String apiWebhook();

    @Key("selenoid.username")
    String selenoidUsername();

    @Key("selenoid.password")
    String selenoidPassword();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browser.version")
    @DefaultValue("128.0")
    String browserVersion();

    @Key("browser.size")
    @DefaultValue("1920x1080")
    String browserSize();
}
