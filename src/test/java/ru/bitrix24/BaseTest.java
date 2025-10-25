package ru.bitrix24;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import ru.bitrix24.config.AppConfig;
import ru.bitrix24.pageobject.LoginPage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
/*    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = AppConfig.apiUrl + AppConfig.apiWebhook;
        Selenide.open(AppConfig.url);

        LoginPage loginPage = new LoginPage();
        loginPage.successfulLogin();
    }*/

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://b24-ql072f.bitrix24.ru";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        RestAssured.baseURI = AppConfig.apiUrl + AppConfig.apiUrl + AppConfig.apiWebhook;
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
