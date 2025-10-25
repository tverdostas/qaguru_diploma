package ru.bitrix24.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://b24-ql072f.bitrix24.ru";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        // Configuration.headless = true; // если нужно
    }

    @BeforeEach
    public void browserConfigurations(){
        open(baseUrl);
    }

    @AfterEach
    public void closeWebDriver() {
        Selenide.closeWebDriver();
    }

    @Step("Переключиться на фрейм")
    public static void switchToFrame(){
        switchTo().frame($(By.xpath("//div[@class='side-panel-content-container']//iframe[@class='side-panel-iframe']")));
    }
}
