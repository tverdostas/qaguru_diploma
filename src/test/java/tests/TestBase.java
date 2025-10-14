package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void browserConfigurations(){
        Configuration.baseUrl = "https://b24-ql072f.bitrix24.ru";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    public void closeWebDriver() {
        Selenide.closeWebDriver();
    }
}
