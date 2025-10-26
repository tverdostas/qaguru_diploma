package ru.bitrix24;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import ru.bitrix24.config.AppConfig;
import ru.bitrix24.pageobject.LoginPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static ru.bitrix24.config.AppConfig.getBaseUrl;

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
        Configuration.baseUrl = AppConfig.getBaseUrl();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "normal";
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
/*        // Дождаться появления контейнера боковой панели
        $(".side-panel-content-container").shouldBe(visible, Duration.ofSeconds(10));*/

        // Дождаться iframe внутри неё
        SelenideElement iframe = $x("//div[@class='side-panel-content-container']//iframe[@class='side-panel-iframe']")
                .shouldBe(visible, Duration.ofSeconds(20));

        // Переключиться в iframe
        Selenide.switchTo().frame(iframe.toWebElement());
    }

    @Step("Успешный логин")
    public void successfulLogin(){
        LoginPage loginPage = new LoginPage();
        loginPage.successfulLogin();
    }
}
