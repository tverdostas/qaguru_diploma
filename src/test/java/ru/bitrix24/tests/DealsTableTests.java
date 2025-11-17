package ru.bitrix24.tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import ru.bitrix24.BaseTest;
import ru.bitrix24.pageobject.DealsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class DealsTableTests extends BaseTest {
    
    DealsPage dealsPage = new DealsPage();
    
    @Test
    public void checkColumnTotalPrice(){

        Allure.step("Выполнить успешный логин в UI", () -> successfulLogin());

        Allure.step("Проверить заголовок страницы 'Сделки'", () -> dealsPage.checkPageTitle("Сделки"));

        int sum = Allure.step("Получить общую сумму из колонки 'NEW' таблицы сделок", () -> {
            int totalSum = dealsPage.getDealsTable()
                    .getDealsFromColumn("NEW")
                    .getTotalSum();
            System.out.println("Total sum in 'NEW' column: " + totalSum);
            return totalSum;
        });

        Allure.step("Проверить, что общая сумма больше 0", () -> assertThat(sum).isGreaterThan(0));
    }
}
