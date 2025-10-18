package ru.bitrix24.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
@NoArgsConstructor
public class DealsTable {

    private int totalSum = 0;

    public SelenideElement getColumnTitle() {
        return $x("//*[@class=\"main-kanban-column-title-block-edit\"]");
    }

    public SelenideElement getColumnTotalPrice() {
        return $x("//*[@class=\"crm-kanban-total-price-total\"]");
    }

    @Step("Получить все элементы столбца {0}")
    public DealsTable getDealsFromColumn(String columnTitle) {
        String xpath = String.format("//div[@data-id=\"%s\"]//div[@class=\"crm-kanban-item-total-price\"]", columnTitle);
        List<String> priceTexts = $$x(xpath).texts();

        this.totalSum = priceTexts.stream()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .mapToInt(s -> {
                    String clean = s.replaceAll("[^\\d]", "");
                    return clean.isEmpty() ? 0 : Integer.parseInt(clean);
                })
                .sum();

        return this;
    }
}
