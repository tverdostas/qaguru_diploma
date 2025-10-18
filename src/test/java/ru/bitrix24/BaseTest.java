package ru.bitrix24;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import ru.bitrix24.config.AppConfig;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class BaseTest {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = AppConfig.apiUrl + AppConfig.apiWebhook;
    }

    @Step("Переключиться на фрейм")
    public static void switchToFrame(){
        switchTo().frame($(By.xpath("//div[@class='side-panel-content-container']//iframe[@class='side-panel-iframe']")));
    }
}
