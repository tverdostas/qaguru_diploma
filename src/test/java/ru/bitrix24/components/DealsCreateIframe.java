package ru.bitrix24.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class DealsCreateIframe {
    public SelenideElement inputNameOfDeal() {
        return $x("//input[@name = 'TITLE']");
    }


    }

