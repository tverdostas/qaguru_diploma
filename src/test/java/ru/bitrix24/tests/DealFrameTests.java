package ru.bitrix24.tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import ru.bitrix24.BaseTest;
import ru.bitrix24.api.deals.Deal;
import ru.bitrix24.api.deals.DealListResponseDto;
import ru.bitrix24.api.deals.DealsApi;
import ru.bitrix24.api.deals.GetDealRequestDto;
import ru.bitrix24.components.DealsIframe;
import ru.bitrix24.enums.TimelineActions;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.bitrix24.config.AppConfig.dealsUrl;

public class DealFrameTests extends BaseTest {

    private final DealsApi dealsApi = new DealsApi();

    private final DealsIframe dealsIframe = new DealsIframe();

    @TestFactory
    Stream<DynamicTest> allActionsInTimelineIsDisplayedInDealIframe() {
        // Получаем одну сделку
        GetDealRequestDto dealsList = GetDealRequestDto.builder()
                .filter(GetDealRequestDto.Filter.builder().CLOSED("N").build())
                .build();

        DealListResponseDto response = dealsApi.getListOfDeals(dealsList);
        assertThat(response).isNotNull();
        assertThat(response.getResult()).isNotNull();

        List<Deal> deals = response.getResult();
        assertThat(deals).isNotEmpty();

        // Выбираем случайную сделку
        Random random = new Random();
        Deal randomDeal = deals.get(random.nextInt(deals.size()));
        assertThat(randomDeal.getId()).isNotBlank();
        assertThat(randomDeal.getTitle()).isNotBlank();

        System.out.println("Используемая сделка: ID=" + randomDeal.getId() + ", TITLE=" + randomDeal.getTitle());

        // Открываем страницу сделки один раз
        open(dealsUrl + randomDeal.getId() + "/");
        switchToFrame();

        // Генерируем тест для каждого элемента enum
        return Arrays.stream(TimelineActions.values())
                .map(action -> DynamicTest.dynamicTest(
                        "Проверка таба: " + action.name(),
                        () -> {
                            SelenideElement timelineTab = dealsIframe.getTimelineTab(action.getDisplayName());
                            timelineTab.shouldBe(visible);
                        }
                ));
    }
    }

