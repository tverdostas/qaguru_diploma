package ru.bitrix24.api.clients;

import ru.bitrix24.api.deals.DealListResponseDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.bitrix24.config.AppConfig;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Bitrix24ApiClient {

    protected static AppConfig config;
    private String baseUrl;
    private String webhookToken;

    public Bitrix24ApiClient(String baseUrl, String webhookToken) {
        this.baseUrl = baseUrl;
        this.webhookToken = webhookToken;
        RestAssured.baseURI = baseUrl;
    }

    /**
     * Получить список сделок без фильтров
     */
    public DealListResponseDto getDealList() {
        return getDealList(null);
    }

    /**
     * Получить список сделок с параметрами (фильтр, сортировка, пагинация)
     *
     * @param queryParams параметры запроса: order, filter, start и т.д.
     */
    public DealListResponseDto getDealList(Map<String, Object> queryParams) {
        String endpoint = config.apiWebhook() + "/crm.deal.list";

        Response response = given()
                .queryParams(queryParams != null ? queryParams : Map.of())
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract().response();

        return response.as(DealListResponseDto.class);
    }

    public DealListResponseDto getDealsOrderedByDateDesc() {
        Map<String, Object> order = Map.of("DATE_CREATE", "DESC");
        return getDealList(Map.of("order", order));
    }
}