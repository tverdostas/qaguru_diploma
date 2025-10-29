package ru.bitrix24.tests;

import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import ru.bitrix24.BaseTest;
import ru.bitrix24.api.deals.Deal;
import ru.bitrix24.api.steps.DealsSteps;
import ru.bitrix24.components.DealsIframe;
import ru.bitrix24.enums.DealsStatus;
import ru.bitrix24.enums.TimelineActions;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class DealFrameTests extends BaseTest {

    private final DealsIframe dealsIframe = new DealsIframe();

/*    @TestFactory
    Stream<DynamicTest> allActionsInTimelineIsDisplayedInDealIframe() {
        // Получаем одну сделку
        DealsSteps dealsSteps = new DealsSteps();
        Deal deal = dealsSteps.getRandomActiveDeal();

        successfulLogin();

        sleep(30000);

        open(baseUrl + "crm/deal/details/" + deal.getId() + "/");
        System.out.println(baseUrl + "crm/deal/details/" + deal.getId() + "/");

        switchToFrame();

        return Arrays.stream(TimelineActions.values())
                .map(action -> DynamicTest.dynamicTest(
                        "Таб: " + action.name(),
                        () -> {
                            SelenideElement tab = dealsIframe.getTimelineTab(action.getDisplayName());
                            tab.shouldBe(visible);
                        }
                ));
    }

    @TestFactory
    Stream<DynamicTest> allStagesInInDealIframeIsVisible() {
        // Получаем одну сделку
        DealsSteps dealsSteps = new DealsSteps();
        Deal deal = dealsSteps.getRandomActiveDeal();

        successfulLogin();

        sleep(30000);

        open(baseUrl + "crm/deal/details/" + deal.getId() + "/");
        System.out.println(baseUrl + "crm/deal/details/" + deal.getId() + "/");

        switchToFrame();

        return Arrays.stream(DealsStatus.values())
                .map(stage -> DynamicTest.dynamicTest(
                        "Стадия: " + stage.name(),
                        () -> {
                            SelenideElement dealStage = dealsIframe.getDealStage(stage.getDisplayName());
                            dealStage.shouldBe(visible);
                        }
                ));
    }*/

    @Test
    void allActionsInTimelineAreDisplayedInDealIframe() {
        // Подготовка: получаем сделку и открываем страницу
        DealsSteps dealsSteps = new DealsSteps();
        Deal deal = dealsSteps.getRandomActiveDeal();

        successfulLogin();
        sleep(30000); // ⚠️ лучше заменить на ожидания, но оставим как есть

        open(baseUrl + "crm/deal/details/" + deal.getId() + "/");
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

    @Test
    void allStagesInDealIframeAreVisible() {
        // Подготовка: получаем сделку и открываем страницу
        DealsSteps dealsSteps = new DealsSteps();
        Deal deal = dealsSteps.getRandomActiveDeal();

        successfulLogin();
        sleep(30000);

        open(baseUrl + "crm/deal/details/" + deal.getId() + "/");
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

