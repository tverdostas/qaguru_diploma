package ru.bitrix24.tests;

import ru.bitrix24.BaseTest;
import ru.bitrix24.api.deals.Deal;
import ru.bitrix24.api.deals.DealListResponseDto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.bitrix24.api.deals.DealsApi;
import ru.bitrix24.api.deals.GetDealRequestDto;
import ru.bitrix24.enums.DealsStatus;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class DealFrameTests extends BaseTest {

/*    private Bitrix24ApiClient apiClient;
    private static final String BASE_URL = "https://portal-test.kuber.3l.ru/rest/2204";
    private static final String WEBHOOK_TOKEN = "password2Kub0057";*/

    private final DealsApi dealsApi = new DealsApi();



    @ParameterizedTest
    @EnumSource(DealsStatus.class)
    public void AllStagesOfDealDisplayedInDealIframe(){

        GetDealRequestDto dealsList = GetDealRequestDto.builder()
                .filter(GetDealRequestDto.Filter.builder().CLOSED("N").build())
                .build();

        // 2. Отправляем запрос и получаем ответ
        DealListResponseDto response = dealsApi.getListOfDeals(dealsList);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getResult()).isNotNull();

        List<Deal> deals = response.getResult();
        assertThat(deals).isNotEmpty(); // убедимся, что есть хотя бы одна

        // Выбираем случайную сделку
        Random random = new Random();
        Deal randomDeal = deals.get(random.nextInt(deals.size()));

        // Проверяем, что у неё есть ID и название
        assertThat(randomDeal.getId()).isNotBlank();
        assertThat(randomDeal.getTitle()).isNotBlank();

        System.out.println("Случайная сделка: ID=" + randomDeal.getId() + ", TITLE=" + randomDeal.getTitle());


    }
    }

