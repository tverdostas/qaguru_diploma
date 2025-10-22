package ru.bitrix24.api.deals;

import com.google.gson.Gson;
import ru.bitrix24.api.base.RequestDto;
import com.google.gson.annotations.Expose;
import lombok.*;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class GetDealRequestDto implements RequestDto {

    @Expose
    private final Map<String, String> filter;

    @Expose
    private final Map<String, String> order;

    private static final Gson gson = new Gson();

    // В Bitrix24 сортировка — это просто мапа вида {"поле": "направление"}
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Order {
        // Пример: DATE_CREATE -> "DESC"
        @Expose
        private final String DATE_CREATE; // можно сделать Map<String, String>, но для простоты — фиксированное поле
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Filter {
        @Expose
        private final String CLOSED;

        // Добавьте другие поля по мере необходимости: STAGE_ID, ASSIGNED_BY_ID и т.д.
    }

    // Этот метод toJson() больше не подходит для прямой отправки в Bitrix!
    // Лучше не использовать его для запроса — см. пояснение ниже.
    @Override
    public String toJson() {
        return gson.toJson(this);
    }
}
