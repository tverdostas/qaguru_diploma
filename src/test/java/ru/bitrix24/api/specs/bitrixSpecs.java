package ru.bitrix24.api.specs;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class bitrixSpecs {
    public class BaseHttpClient {

        private static final String AUTHORIZATION = "Authorization";
        private static final String CONTENT_TYPE = "Content-Type";
        private static final String JSON = "application/json";

        public static Response doGetRequest(String token, String uri) {
            return given()
                    .header(AUTHORIZATION, token)
                    .get(uri);
        }
    }
}
