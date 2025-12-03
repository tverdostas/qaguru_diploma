package ru.bitrix24.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
@NoArgsConstructor
public class DealsPage {
    DealsTable dealsTable = new DealsTable();

    public SelenideElement getPageTitle() {
        return $x("//*[@id=\"pagetitle\"]");
    }

    public SelenideElement dealCreateButton() {
        return $x("//*[contains(@class, 'ui-btn-main')]/span[@class='ui-btn-text' and .='Создать']");
    }

    @Step
    public DealsPage checkPageTitle(String title) {
        getPageTitle().shouldHave(text(title));

        return this;
    }

    @Step
    public DealsPage checkPageTitleVisibility() {
        getPageTitle().shouldBe(visible);

        return this;
    }

    @Step
    public DealsPage clickCreateButton() {
        dealCreateButton().click();

        return this;
    }
}
