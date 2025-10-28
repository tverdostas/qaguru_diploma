package ru.bitrix24.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bitrix24.BaseTest;
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
        open(baseUrl);

        loginPage.fillLogin(username)
                .checkVisibilityOfWarning()
                .checkWarningText(warningText);
    }

    @Test
    public void successfulLoginTest() {
        open(baseUrl);

        loginPage.fillLogin(userLogin)
                .fillUserPass(userPassword);

        dealsPage.checkPageTitleVisibility();
    }

    @Test
    public void wrongPasswordTest() {
        open(baseUrl);

        loginPage.fillLogin(userLogin)
                .fillUserPass("12345qwe")
                .checkVisibilityOfWarning()
                .checkWarningText("Неверный логин или пароль.");
    }
}
