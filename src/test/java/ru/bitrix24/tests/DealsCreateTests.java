package ru.bitrix24.tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.bitrix24.BaseTest;
import ru.bitrix24.api.deals.*;
import ru.bitrix24.components.DealsCreateIframe;
import ru.bitrix24.pageobject.DealsPage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import static com.codeborne.selenide.Condition.visible;

@Epic("Создание элементов CRM")
@Feature("Создание сделки")
public class DealsCreateTests extends BaseTest {

    DealsPage dealsPage = new DealsPage();;
    DealsApi dealsApi = new DealsApi();

    @DisplayName("Сделка успешно создается через UI")
    @Test
        public void dealIsSuccessfullyCreatedByButton() {


            String createdDealId = null; // будем хранить ID созданной сделки

            // 1. Формируем запрос для получения списка открытых сделок
            GetDealRequestDto dealsRequest = GetDealRequestDto.builder()
                    .filter(Map.of("CLOSED", "N"))
                    .order(Map.of("DATE_CREATE", "DESC"))
                    .build();

            // Получаем начальное количество сделок
            int initialDealCount = Allure.step("Получить начальное количество сделок через API", () -> {
                DealListResponseDto initialResponse = dealsApi.getListOfDeals(dealsRequest);
                int count = initialResponse.getResult().size();
                log.info("Initial deal count: " + count);
                return count;
            });

            // 2. Открываем страницу и нажимаем "Создать"
            Allure.step("Выполнить успешный логин в UI", () -> successfulLogin());

            Allure.step("Нажать кнопку 'Создать' на странице сделок", () -> dealsPage.clickCreateButton());

            // 3. Переключаемся во фрейм (предположим, что метод switchToFrame() реализован в BaseTest или здесь)
            Allure.step("Переключиться во фрейм создания сделки", () -> switchToFrame()); // Убедитесь, что этот метод существует и корректно переключает в iframe

            // 4. Заполняем поле названия сделки
            Allure.step("Заполнить поле названия сделки", () -> {
                DealsCreateIframe dealForm = new DealsCreateIframe();
                dealForm.inputNameOfDeal().shouldBe(visible).setValue("создано автотестом");
            });

            // 5. Сохраняем через Ctrl+Enter
            Allure.step("Сохранить сделку через Ctrl+Enter", () -> {
                DealsCreateIframe dealForm = new DealsCreateIframe(); // Возможно, нужно получить из switchToFrame
                dealForm.inputNameOfDeal().sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
            });

            // 6. Возвращаемся в основной контекст
            Allure.step("Вернуться в основной контекст браузера", () -> Selenide.switchTo().defaultContent());

            // 7. Ждём завершения создания (лучше заменить на Awaitility в продакшене)
            Selenide.sleep(3000); // Лучше избегать sleep, использовать явные ожидания

            // 8. Запрашиваем список снова
            int finalDealCount = Allure.step("Получить количество сделок после создания через UI", () -> {
                DealListResponseDto finalResponse = dealsApi.getListOfDeals(dealsRequest);
                int count = finalResponse.getResult().size();
                log.info("Final deal count: " + count);
                return count;
            });

            Allure.step("Проверить, что количество сделок увеличилось на 1", () -> {
                assertThat(finalDealCount).isEqualTo(initialDealCount + 1);
            });

            // 9. Находим ID только что созданной сделки по названию
            createdDealId = Allure.step("Найти ID созданной сделки по названию через API", () -> {
                DealListResponseDto finalResponse = dealsApi.getListOfDeals(dealsRequest); // Повторный вызов, чтобы получить обновлённый список
                String id = finalResponse.getResult().stream()
                        .filter(deal -> "создано автотестом".equals(deal.getTitle()))
                        .findFirst()
                        .map(Deal::getId) // предполагается, что у DealDto есть getId()
                        .orElseThrow(() -> new IllegalStateException("Созданная сделка не найдена в списке"));
                log.info("Created deal ID: " + id);
                return id;
            });

            // 10. Удаляем сделку через API
            String finalCreatedDealId = createdDealId;
            boolean deleted = Allure.step("Удалить созданную сделку через API", () -> dealsApi.deleteDeal(finalCreatedDealId));
            Allure.step("Проверить, что удаление прошло успешно", () -> assertThat(deleted).isTrue());

            // 11. (Опционально) Проверяем, что сделка удалена
            int afterDeleteCount = Allure.step("Получить количество сделок после удаления через API", () -> {
                DealListResponseDto afterDeleteResponse = dealsApi.getListOfDeals(dealsRequest);
                int count = afterDeleteResponse.getResult().size();
                return count;
            });

            Allure.step("Проверить, что количество сделок вернулось к исходному", () -> {
                assertThat(afterDeleteCount).isEqualTo(initialDealCount); // вернулись к исходному количеству
            });
        }
    }
