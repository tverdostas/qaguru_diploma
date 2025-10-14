package ru.bitrix24.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    public SelenideElement userLogin() {
        return $x("//*[@id=\"login\"]");
    }

    public SelenideElement userPass() {
        return $x("//input[@type=\"password\"]");
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
}
