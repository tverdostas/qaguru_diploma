package ru.bitrix24.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

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
    public LoginPage successfulLogin(String login, String password) {

        fillLogin(login)
                .fillUserPass(password);

        return this;
    }
}
