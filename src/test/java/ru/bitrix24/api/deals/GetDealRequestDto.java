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

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Order {
        @Expose
        private final String DATE_CREATE;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Filter {
        @Expose
        private final String CLOSED;
    }

    @Override
    public String toJson() {
        return gson.toJson(this);
    }
}
