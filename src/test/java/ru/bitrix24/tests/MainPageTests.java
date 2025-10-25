package ru.bitrix24.tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.bitrix24.BaseTest;
import ru.bitrix24.enums.MainMenuItems;
import ru.bitrix24.pageobject.LoginPage;
import ru.bitrix24.pageobject.MainPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.baseURI;

public class MainPageTests extends BaseTest {
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();

    @ParameterizedTest
    @EnumSource(MainMenuItems.class)
    public void AllButtonsPresentInLeftMainMenu(MainMenuItems mainMenuItems){

        loginPage.successfulLogin();

        SelenideElement menuButton = mainPage.findMenuButton(mainMenuItems.getDisplayName());
        menuButton.shouldBe(visible);
    }
}
