package ru.bitrix24.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bitrix24.BaseUiTest;
import ru.bitrix24.api.deals.Deal;
import ru.bitrix24.api.steps.DealsSteps;
import ru.bitrix24.components.DealsIframe;
import ru.bitrix24.enums.DealsStatus;
import ru.bitrix24.enums.TimelineActions;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

@Epic("Проверка наличия элементов во фрейме")
@Feature("Проверка стадий сделки")
public class DealFrameTests extends BaseUiTest {

    private final DealsIframe dealsIframe = new DealsIframe();

    @DisplayName("Все табы отображены во фрейме сделки")
    @Test
    // Пришлось подогнать enum под размер экрана в тесте
    void allActionsInTimelineAreDisplayedInDealIframe() {
        // Подготовка: получаем сделку и открываем страницу
        DealsSteps dealsSteps = new DealsSteps();
        Deal deal = dealsSteps.getRandomActiveDeal();

        successfulLogin();
        sleep(30000);

        open(Configuration.baseUrl + "/crm/deal/details/" + deal.getId() + "/");
        switchToFrame();

        // Проверка всех табов через soft assertions
        SoftAssertions softly = new SoftAssertions();

        for (TimelineActions action : TimelineActions.values()) {
            String displayName = action.getDisplayName();
            SelenideElement tab = dealsIframe.getTimelineTab(displayName);
            softly.assertThat(tab.isDisplayed())
                    .as("Таб '%s' (enum: %s) должен быть видим", displayName, action.name())
                    .isTrue();
        }

        softly.assertAll();
    }

    @DisplayName("Все стадии отображены во фрейме сделки")
    @Test
    void allStagesInDealIframeAreVisible() {
        // Подготовка: получаем сделку и открываем страницу
        DealsSteps dealsSteps = new DealsSteps();
        Deal deal = dealsSteps.getRandomActiveDeal();

        successfulLogin();
        sleep(30000);

        open(Configuration.baseUrl + "/crm/deal/details/" + deal.getId() + "/");
        switchToFrame();

        // Проверка всех стадий через soft assertions
        SoftAssertions softly = new SoftAssertions();

        for (DealsStatus stage : DealsStatus.values()) {
            String displayName = stage.getDisplayName();
            SelenideElement dealStage = dealsIframe.getDealStage(displayName);
            softly.assertThat(dealStage.isDisplayed())
                    .as("Стадия '%s' (enum: %s) должна быть видима", displayName, stage.name())
                    .isTrue();
        }

        softly.assertAll();
    }
    }

