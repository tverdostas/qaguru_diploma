package tests;

import org.junit.jupiter.api.Test;
import ru.bitrix24.pageobject.DealsPage;
import ru.bitrix24.pageobject.DealsTable;
import ru.bitrix24.pageobject.LoginPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class DealsTests extends TestBase {
    
    DealsPage dealsPage = new DealsPage();
    LoginPage loginPage = new LoginPage();
    
    @Test
    public void checkColumnTotalPrice(){

        open("https://b24-ql072f.bitrix24.ru");

        loginPage.fillLogin("woltia94@gmail.com")
                .fillUserPass("#4H=Mgchst7t_zE");

        dealsPage.checkPageTitle("Сделки");

        int sum = dealsPage.getDealsTable()
                .getDealsFromColumn("NEW")
                .getTotalSum();

        assertThat(sum).isGreaterThan(0);
    }
}
