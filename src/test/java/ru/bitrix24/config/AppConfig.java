package ru.bitrix24.config;

import org.aeonbits.owner.Config;

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
}
