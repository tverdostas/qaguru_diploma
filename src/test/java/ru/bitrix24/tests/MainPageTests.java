package ru.bitrix24.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import ru.bitrix24.BaseTest;
import ru.bitrix24.enums.MainMenuItems;
import ru.bitrix24.pageobject.MainPage;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;

@Epic("Проверка наличия элементов главной страницы")
@Feature("Проверка меню")
public class MainPageTests extends BaseTest {
    MainPage mainPage = new MainPage();

    @DisplayName("В главном меню отображены все кнопки")
    @TestFactory
    Stream<DynamicTest> allButtonsPresentInLeftMainMenu() {
        // Выполняем логин один раз перед генерацией тестов
        Allure.step("Выполнить успешный логин в UI", () -> successfulLogin());

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
