package ru.bitrix24.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bitrix24.BaseTest;
import ru.bitrix24.config.AppConfig;
import ru.bitrix24.pageobject.DealsPage;
import ru.bitrix24.pageobject.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginTests extends BaseTest {

    LoginPage loginPage = new LoginPage();
    DealsPage dealsPage = new DealsPage();

    @ParameterizedTest
    @CsvSource({
            "qwe123@mail.com, Такого пользователя нет",
            "123, Используйте e-mail или телефон"
    })

    public void wrongLoginTests(String username, String warningText) {
        open(AppConfig.getBaseUrl());

        loginPage.fillLogin(username)
                .checkVisibilityOfWarning()
                .checkWarningText(warningText);
    }

    @Test
    public void successfulLoginTest() {
        open(AppConfig.getBaseUrl());

        loginPage.fillLogin("+7 909 338-12-97")
                .fillUserPass("Bitrix1910");

        dealsPage.checkPageTitleVisibility();
    }

    @Test
    public void wrongPasswordTest() {
        open(AppConfig.getBaseUrl());

        loginPage.fillLogin("+7 909 338-12-97")
                .fillUserPass("12345qwe")
                .checkVisibilityOfWarning()
                .checkWarningText("Неверный логин или пароль.");
    }
}
