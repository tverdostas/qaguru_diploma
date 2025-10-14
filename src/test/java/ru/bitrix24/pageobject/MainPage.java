package ru.bitrix24.pageobject;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.codeborne.selenide.Selenide.$;

@Getter
@Setter
@NoArgsConstructor
public class MainPage {
    private SelenideElement userName = $("#userName-value"),
            deleteButtons = $("#delete-record-undefined"),
            deleteModal = $("#closeSmallModal-ok"),
            noRowsFound = $(".rt-noData");
}
