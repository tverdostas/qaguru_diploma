package ru.bitrix24.tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DynamicTest;
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

    @TestFactory
    Stream<DynamicTest> allActionsInTimelineIsDisplayedInDealIframe() {
        // Получаем одну сделку
        DealsSteps dealsSteps = new DealsSteps();
        Deal deal = dealsSteps.getRandomActiveDeal();

        successfulLogin();

        sleep(30000);

        open(dealsUrl + deal.getId() + "/");
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

        open(dealsUrl + deal.getId() + "/");
        switchToFrame();

        return Arrays.stream(DealsStatus.values())
                .map(stage -> DynamicTest.dynamicTest(
                        "Стадия: " + stage.name(),
                        () -> {
                            SelenideElement dealStage = dealsIframe.getDealStage(stage.getDisplayName());
                            dealStage.shouldBe(visible);
                        }
                ));
    }
    }

