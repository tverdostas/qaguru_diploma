package ru.bitrix24.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    public SelenideElement userLogin() {
        return $x("//*[@id=\"login\"]");
    }

    public SelenideElement userPass() {
        return $x("//input[@type=\"password\"]");
    }

    public SelenideElement inputLoginWarning() {
        return $x("//div[contains(@class, 'b24net-input-warning')]");
    }

    public SelenideElement inputPasswordWarning() {
        return $x("//div[contains(@class, 'b24net-input-warning')]");
    }

    @Step
    public LoginPage fillLogin(String login) {
        userLogin().setValue(login).pressEnter();

        return this;
    }

    @Step
    public LoginPage fillUserPass(String password) {
        userPass().setValue(password).pressEnter();

        return this;
    }

    @Step
    public LoginPage checkVisibilityOfWarning() {
        inputLoginWarning().isDisplayed();

        return this;
    }

    @Step
    public LoginPage checkWarningText(String warning) {
        inputLoginWarning().shouldHave(text(warning));

        return this;
    }

    @Step
    public LoginPage successfulLogin() {

        fillLogin("+7 909 338-12-97")
                .fillUserPass("Bitrix1910");

        return this;
    }
}
