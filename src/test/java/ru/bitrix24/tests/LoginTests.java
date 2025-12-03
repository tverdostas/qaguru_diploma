package ru.bitrix24.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bitrix24.BaseUiTest;
import ru.bitrix24.pageobject.DealsPage;
import ru.bitrix24.pageobject.LoginPage;

import static com.codeborne.selenide.Selenide.open;

@Epic("Логин и авторизация")
@Feature("Проверки логина")
public class LoginTests extends BaseUiTest {
    LoginPage loginPage = new LoginPage();
    DealsPage dealsPage = new DealsPage();

    @ParameterizedTest
    @CsvSource({
            "qwe123@mail.com, Такого пользователя нет",
            "123, Используйте e-mail или телефон"
    })

    @DisplayName("Получение ошибок при неверной комбинации логина и пароля")
    public void wrongLoginTests(String username, String warningText) {
        // open(Configuration.baseUrl);

        loginPage.fillLogin(username)
                .checkVisibilityOfWarning()
                .checkWarningText(warningText);
    }

    @Test
    @DisplayName("Успешный логин в систему")
    public void successfulLoginTest() {
        // Allure.step("Открыть главную страницу", () -> open(Configuration.baseUrl));

        Allure.step("Заполнить логин и пароль", () -> {
            loginPage.fillLogin(config.userLogin())
                    .fillUserPass(config.userPassword());
        });

        Allure.step("Проверить видимость заголовка страницы сделок", () -> dealsPage.checkPageTitleVisibility());
    }

    @Test
    @DisplayName("Получение ошибки при вводе неправильного пароля")
    public void wrongPasswordTest() {
       // Allure.step("Открыть главную страницу", () -> open(Configuration.baseUrl));

        Allure.step("Заполнить логин и неправильный пароль, проверить предупреждение", () -> {
            loginPage.fillLogin(config.userLogin())
                    .fillUserPass("12345qwe")
                    .checkVisibilityOfWarning()
                    .checkWarningText("Неверный логин или пароль.");
        });
    }
}
