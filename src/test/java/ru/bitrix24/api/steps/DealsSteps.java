package ru.bitrix24.api.steps;

import ru.bitrix24.api.deals.Deal;
import ru.bitrix24.api.deals.DealListResponseDto;
import ru.bitrix24.api.deals.DealsApi;
import ru.bitrix24.api.deals.GetDealRequestDto;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class DealsSteps {

    private final DealsApi dealsApi = new DealsApi();

    public Deal getRandomActiveDeal() {
        GetDealRequestDto dealsList = GetDealRequestDto.builder()
                .filter(Map.of("CLOSED", "N"))
                .order(Map.of("DATE_CREATE", "DESC"))
                .build();

        DealListResponseDto response = dealsApi.getListOfDeals(dealsList);
        assertThat(response).isNotNull();
        assertThat(response.getResult()).isNotNull();

        List<Deal> deals = response.getResult();
        assertThat(deals).isNotEmpty();

        Random random = new Random();
        Deal deal = deals.get(random.nextInt(deals.size()));
        assertThat(deal.getId()).isNotBlank();
        assertThat(deal.getTitle()).isNotBlank();

        System.out.println("Выбрана случайная сделка: ID=" + deal.getId() + ", TITLE=" + deal.getTitle());
        return deal;
    }

}
