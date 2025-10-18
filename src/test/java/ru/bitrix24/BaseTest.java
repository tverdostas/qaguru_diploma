package ru.bitrix24;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import ru.bitrix24.config.AppConfig;
import ru.bitrix24.pageobject.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class BaseTest {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = AppConfig.apiUrl + AppConfig.apiWebhook;
        Selenide.open(AppConfig.url);

        LoginPage loginPage = new LoginPage();
        loginPage.successfulLogin();
    }

    @Step("Переключиться на фрейм")
    public static void switchToFrame(){
        switchTo().frame($(By.xpath("//div[@class='side-panel-content-container']//iframe[@class='side-panel-iframe']")));
    }
}
