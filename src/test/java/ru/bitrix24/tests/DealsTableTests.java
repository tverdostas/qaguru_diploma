package ru.bitrix24.tests;

import org.junit.jupiter.api.Test;
import ru.bitrix24.BaseTest;
import ru.bitrix24.pageobject.DealsPage;
import ru.bitrix24.pageobject.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class DealsTableTests extends BaseTest {
    
    DealsPage dealsPage = new DealsPage();
    
    @Test
    public void checkColumnTotalPrice(){

        successfulLogin();

        dealsPage.checkPageTitle("Сделки");

        int sum = dealsPage.getDealsTable()
                .getDealsFromColumn("NEW")
                .getTotalSum();

        assertThat(sum).isGreaterThan(0);
    }
}
