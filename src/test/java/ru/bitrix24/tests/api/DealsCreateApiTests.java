package ru.bitrix24.tests.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import ru.bitrix24.BaseTest;
import ru.bitrix24.api.deals.*;
import ru.bitrix24.config.AppConfig;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DealsCreateApiTests {

    DealsApi dealsApi = new DealsApi();

    @Test
    public void dealIsSuccessfullyCreatedByApi() {

        RestAssured.baseURI = AppConfig.apiUrl + AppConfig.apiUrl + AppConfig.apiWebhook;

        String createdDealId = ""; // будем хранить ID созданной сделки

        // 1. Формируем запрос для получения списка открытых сделок
        GetDealRequestDto dealsRequest = GetDealRequestDto.builder()
                .filter(Map.of("CLOSED", "N"))
                .order(Map.of("DATE_CREATE", "DESC"))
                .build();

        // Получаем начальное количество сделок
        DealListResponseDto initialResponse = dealsApi.getListOfDeals(dealsRequest);
        int initialDealCount = initialResponse.getResult().size();
        System.out.println("Initial deal count: " + initialDealCount);

        // Создаем сделку через апи
        DealCreateResponseDto createResponse = dealsApi.createDeal(
                DealCreateRequestDto.builder()
                        .fields(Map.of("TITLE", "Сделка создано автотестом"))
                        .build()
        );

        createdDealId = String.valueOf(createResponse.getResult());

        // 8. Запрашиваем список снова
        DealListResponseDto finalResponse = dealsApi.getListOfDeals(dealsRequest);
        int finalDealCount = finalResponse.getResult().size();
        System.out.println("Final deal count: " + finalDealCount);

        assertThat(finalDealCount).isEqualTo(initialDealCount + 1);

        // 9. Находим ID только что созданной сделки по названию
        createdDealId = finalResponse.getResult().stream()
                .filter(deal -> "создано автотестом".equals(deal.getTitle()))
                .findFirst()
                .map(Deal::getId) // предполагается, что у DealDto есть getId()
                .orElseThrow(() -> new IllegalStateException("Созданная сделка не найдена в списке"));

        System.out.println("Created deal ID: " + createdDealId);

        // 10. Удаляем сделку через API
        boolean deleted = dealsApi.deleteDeal(createdDealId);
        assertThat(deleted).isTrue();

        // 11. Проверяем, что сделка удалена
        DealListResponseDto afterDeleteResponse = dealsApi.getListOfDeals(dealsRequest);
        int afterDeleteCount = afterDeleteResponse.getResult().size();
        assertThat(afterDeleteCount).isEqualTo(initialDealCount); // вернулись к исходному количеству
    }
}
