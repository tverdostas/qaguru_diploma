package ru.bitrix24.pageobject;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.text;

@Getter
@Setter
@NoArgsConstructor
public class MainPage {
    public SelenideElement logoText = $x("//*[@class=\"logo-text\"]");

    @Step
    public MainPage checkPageTitle(String title) {
        logoText.shouldHave(text(title));

        return this;
    }
}
