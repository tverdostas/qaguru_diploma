package ru.bitrix24.tests;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import ru.bitrix24.BaseTest;
import ru.bitrix24.enums.MainMenuItems;
import ru.bitrix24.pageobject.LoginPage;
import ru.bitrix24.pageobject.MainPage;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;

public class MainPageTests extends BaseTest {
    MainPage mainPage = new MainPage();

    @TestFactory
    Stream<DynamicTest> allButtonsPresentInLeftMainMenu() {
        // Выполняем логин один раз перед генерацией тестов
        successfulLogin();

        return Stream.of(MainMenuItems.values())
                .map(item -> DynamicTest.dynamicTest(
                        "Menu item '" + item.getDisplayName() + "' is visible",
                        () -> {
                            var menuButton = mainPage.findMenuButton(item.getDisplayName());
                            menuButton.shouldBe(visible);
                        }
                ));
    }
}
