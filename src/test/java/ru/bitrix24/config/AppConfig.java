package ru.bitrix24.config;

import lombok.Getter;

@Getter
public class AppConfig {

    private static String userLogin;
    private static String userPassword;

    public static void initCredentials() {
        String url = System.getProperty("BASE_URL");
        if (url == null || url.isBlank()) {
            throw new IllegalStateException("BASE_URL не задан!");
        }

        userLogin = System.getProperty("LOGIN");
        userPassword = System.getProperty("PASSWORD");

        if (userLogin == null || userLogin.isBlank()) {
            throw new IllegalStateException("LOGIN не задан!");
        }
        if (userPassword == null || userPassword.isBlank()) {
            throw new IllegalStateException("PASSWORD не задан!");
        }
    }
}
