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
import ru.bitrix24.config.AppConfig;
import ru.bitrix24.helpers.Attach;
import ru.bitrix24.pageobject.LoginPage;

import java.time.Duration;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class BaseTest {


    protected String baseUrl = System.getProperty("baseUrl");
    protected String userLogin = System.getProperty("userLogin");
    protected String userPassword = System.getProperty("userPassword");
    protected String dealsUrl = System.getProperty("userPassword");

    @BeforeAll
    static void setUp() {
        Configuration.browser = System.getProperty("browser", "chrome");
        // Configuration.browserVersion = System.getProperty("browserVersion", "128.0");
        Configuration.browserSize = System.getProperty("windowSize", "1920x1080");
        Configuration.pageLoadStrategy = "normal";
        String selenoidPassword = System.getProperty("selenoidPassword");
        String selenoidUsername = System.getProperty("selenoidUsername");
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
        open(baseUrl);
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
        loginPage.successfulLogin();
    }
}
