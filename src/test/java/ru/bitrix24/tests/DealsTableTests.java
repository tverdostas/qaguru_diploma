package ru.bitrix24.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bitrix24.BaseTest;
import ru.bitrix24.pageobject.DealsPage;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Проверка наличия элементов во фрейме")
@Feature("Проверка таймлайна сделки")
public class DealsTableTests extends BaseTest {
    
    DealsPage dealsPage = new DealsPage();

    @DisplayName("Все табы отображены во фрейме сделки")
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
