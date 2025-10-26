package ru.bitrix24.config;

import lombok.Getter;

@Getter
public class AppConfig {
/*    public static String url = System.getenv("BASE_URL");
    public static String dealsUrl = url + "crm/deal/details/";
    public static String apiUrl = url + "rest/";
    public static String apiWebhook = System.getenv("API_WEBHOOK");*/

    public static String getBaseUrl() {
        String url = System.getProperty("BASE_URL");
        if (url == null || url.isBlank()) {
            throw new IllegalStateException("BASE_URL не задан!");
        }
        return url;
    }

    public static String getApiWebhook() {
        String webhook = System.getProperty("API_WEBHOOK");
        if (webhook == null || webhook.isBlank()) {
            throw new IllegalStateException("API_WEBHOOK не задан!");
        }
        return webhook;
    }

    public static String getRestBaseUrl() {
        return getBaseUrl() + "/rest/" + getApiWebhook();
    }
}
