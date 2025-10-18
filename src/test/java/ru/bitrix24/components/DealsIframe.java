package ru.bitrix24.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class DealsIframe {
    public SelenideElement getTimelineTab(String tabName) {
        return $x("//span[@class='main-buttons-item-text']//child::span[@class='main-buttons-item-text-box' and text() = '" + tabName + "']");
    }
}
