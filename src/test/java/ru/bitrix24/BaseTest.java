package ru.bitrix24;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.LoggerFactory;
import ru.bitrix24.config.AppConfig;
import ru.bitrix24.helpers.Attach;
import ru.bitrix24.pageobject.LoginPage;

import java.time.Duration;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;

public class BaseTest {

    protected static AppConfig config;
    public static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeAll
    static void setUp() {

        config = ConfigFactory.create(AppConfig.class, System.getProperties());

        String browser = config.browser();
        String browserVersion = config.browserVersion();
        String browserSize = config.browserSize();
        Configuration.pageLoadStrategy = "normal";
        Configuration.baseUrl = config.baseUrl();

        String selenoidPassword = config.selenoidPassword();
        String selenoidUsername = config.selenoidUsername();
        if (selenoidPassword != null) {
            Configuration.remote = "https://" + selenoidUsername + ":" + selenoidPassword + "@" + System.getProperty("selenoid_url", "selenoid.autotests.cloud/wd/hub");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true,
                    "videoCodec", "libx264",
                    "videoFrameRate", 24
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }

    @BeforeEach
    public void browserConfigurations(){
        open("");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    public void closeWebDriverAndAddAttach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }

    @Step("Переключиться на фрейм")
    public static void switchToFrame(){

        // Дождаться iframe внутри неё
        SelenideElement iframe = $x("//div[@class='side-panel-content-container']//iframe[@class='side-panel-iframe']")
                .shouldBe(visible, Duration.ofSeconds(20));

        // Переключиться в iframe
        Selenide.switchTo().frame(iframe.toWebElement());
    }

    @Step("Успешный логин")
    public void successfulLogin(){
        LoginPage loginPage = new LoginPage();
        loginPage.successfulLogin(config.userLogin(), config.userPassword());
    }
}
