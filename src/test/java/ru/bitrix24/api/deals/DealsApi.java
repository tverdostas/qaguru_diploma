package ru.bitrix24.api.deals;

import ru.bitrix24.api.base.BaseApi;
import ru.bitrix24.api.endpoint.DealsEndPoint;

import static io.restassured.RestAssured.given;

public class DealsApi extends BaseApi {
    public DealListResponseDto getListOfDeals(GetDealRequestDto dealsList){
        String json = given().log().all()
                .spec(defaultRequestSpec)
                .body(dealsList.toJson())
                .when()
                .post(DealsEndPoint.GET_LIST)
                .then()
                .statusCode(200)
                .extract().response().asString();

        return DealListResponseDto.fromJson(json);
    }

    public boolean deleteDeal(String dealId){
        String json = given().log().all()
                .spec(defaultRequestSpec)
                .queryParam("id", dealId)
                .when()
                .post(DealsEndPoint.DELETE_DEAL)
                .then()
                .statusCode(200)
                .extract().response().asString();

        DealDeleteResponseDto response = DealDeleteResponseDto.fromJson(json);
        return response.isResult();
    }
}
