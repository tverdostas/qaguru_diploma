package ru.bitrix24.tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.bitrix24.BaseTest;
import ru.bitrix24.api.deals.DealListResponseDto;
import ru.bitrix24.api.deals.DealsApi;
import ru.bitrix24.api.deals.GetDealRequestDto;
import ru.bitrix24.components.DealsCreateIframe;
import ru.bitrix24.pageobject.DealsPage;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;
import ru.bitrix24.api.deals.Deal;

import java.util.Map;

import static com.codeborne.selenide.Condition.visible;

public class DealsCreateTests extends BaseTest {

    DealsPage dealsPage = new DealsPage();
    DealsApi dealsApi = new DealsApi();

    @Test
    public void dealIsSuccessfullyCreatedByButton() {

        String createdDealId = null; // будем хранить ID созданной сделки

        // 1. Формируем запрос для получения списка открытых сделок
        GetDealRequestDto dealsRequest = GetDealRequestDto.builder()
                .filter(Map.of("CLOSED", "N"))
                .order(Map.of("DATE_CREATE", "DESC"))
                .build();

        // Получаем начальное количество сделок
        DealListResponseDto initialResponse = dealsApi.getListOfDeals(dealsRequest);
        int initialDealCount = initialResponse.getResult().size();
        System.out.println("Initial deal count: " + initialDealCount);

        // 2. Открываем страницу и нажимаем "Создать"
        // open("https://b24-ql072f.bitrix24.ru/crm/deal/list/");
        dealsPage.clickCreateButton();

        // 3. Переключаемся во фрейм (предположим, что метод switchToFrame() реализован в BaseTest или здесь)
        switchToFrame(); // Убедитесь, что этот метод существует и корректно переключает в iframe

        // 4. Заполняем поле названия сделки
        DealsCreateIframe dealForm = new DealsCreateIframe();
        dealForm.inputNameOfDeal().shouldBe(visible).setValue("создано автотестом");

        // 5. Сохраняем через Ctrl+Enter
        dealForm.inputNameOfDeal().sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));

        // 6. Возвращаемся в основной контекст
        Selenide.switchTo().defaultContent();

        // 7. Ждём завершения создания (лучше заменить на Awaitility в продакшене)
        Selenide.sleep(3000);

        // 8. Запрашиваем список снова
        DealListResponseDto finalResponse = dealsApi.getListOfDeals(dealsRequest);
        int finalDealCount = finalResponse.getResult().size();
        System.out.println("Final deal count: " + finalDealCount);

        assertThat(finalDealCount).isEqualTo(initialDealCount + 1);

        // 9. Находим ID только что созданной сделки по названию
        createdDealId = finalResponse.getResult().stream()
                .filter(deal -> "создано автотестом".equals(deal.getTitle()))
                .findFirst()
                .map(Deal::getId) // предполагается, что у DealDto есть getId()
                .orElseThrow(() -> new IllegalStateException("Созданная сделка не найдена в списке"));

        System.out.println("Created deal ID: " + createdDealId);

        // 10. Удаляем сделку через API
        boolean deleted = dealsApi.deleteDeal(createdDealId);
        assertThat(deleted).isTrue();

        // 11. (Опционально) Проверяем, что сделка удалена
        DealListResponseDto afterDeleteResponse = dealsApi.getListOfDeals(dealsRequest);
        int afterDeleteCount = afterDeleteResponse.getResult().size();
        assertThat(afterDeleteCount).isEqualTo(initialDealCount); // вернулись к исходному количеству
    }
}
