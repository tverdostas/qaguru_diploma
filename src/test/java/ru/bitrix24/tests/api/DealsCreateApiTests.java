package ru.bitrix24.tests.api;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bitrix24.BaseTest;
import ru.bitrix24.api.deals.*;
import ru.bitrix24.config.AppConfig;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Создание элементов CRM")
@Feature("Создание сделки")
public class DealsCreateApiTests extends BaseTest {

    DealsApi dealsApi = new DealsApi();

    @DisplayName("Сделка успешно создается через API")
    @Test
    public void dealIsSuccessfullyCreatedByApi() {

        String createdDealId = ""; // будем хранить ID созданной сделки

        // 1. Формируем запрос для получения списка открытых сделок
        GetDealRequestDto dealsRequest = GetDealRequestDto.builder()
                .filter(Map.of("CLOSED", "N"))
                .order(Map.of("DATE_CREATE", "DESC"))
                .build();

        int initialDealCount = Allure.step("Получить начальное количество сделок", () -> {
            DealListResponseDto initialResponse = dealsApi.getListOfDeals(dealsRequest);
            int count = initialResponse.getResult().size();
            log.info("Initial deal count: " + count);
            return count;
        });

        createdDealId = Allure.step("Создать новую сделку через API", () -> {
            DealCreateResponseDto createResponse = dealsApi.createDeal(
                    DealCreateRequestDto.builder()
                            .fields(Map.of("TITLE", "Сделка создано автотестом"))
                            .build()
            );
            String id = String.valueOf(createResponse.getResult());
            log.info("Created deal ID: " + id);
            return id;
        });

        int finalDealCount = Allure.step("Получить количество сделок после создания", () -> {
            DealListResponseDto finalResponse = dealsApi.getListOfDeals(dealsRequest);
            int count = finalResponse.getResult().size();
            log.info("Final deal count: " + count);
            return count;
        });

        Allure.step("Проверить, что количество сделок увеличилось на 1", () -> {
            assertThat(finalDealCount).isEqualTo(initialDealCount + 1);
        });

        String finalCreatedDealId = createdDealId;
        Allure.step("Удалить созданную сделку через API", () -> {
            boolean deleted = dealsApi.deleteDeal(finalCreatedDealId);
            assertThat(deleted).isTrue();
        });

        int afterDeleteCount = Allure.step("Получить количество сделок после удаления", () -> {
            DealListResponseDto afterDeleteResponse = dealsApi.getListOfDeals(dealsRequest);
            int count = afterDeleteResponse.getResult().size();
            return count;
        });

        Allure.step("Проверить, что количество сделок вернулось к исходному", () -> {
            assertThat(afterDeleteCount).isEqualTo(initialDealCount); // вернулись к исходному количеству
        });
}
}
