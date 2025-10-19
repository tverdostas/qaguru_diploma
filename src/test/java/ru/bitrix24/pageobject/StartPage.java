package ru.bitrix24.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class StartPage {
    public SelenideElement loginButton() {
        return $x("//span[contains(@class, 'portal-auth-bitrix24') and normalize-space(.)='Войти']");
    }

    @Step
    public StartPage clickLoginButton() {
        loginButton().click();

        return this;
    }
}
