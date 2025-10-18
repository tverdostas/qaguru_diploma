package ru.bitrix24.api.deals;

import ru.bitrix24.api.base.RequestDto;
import com.google.gson.annotations.Expose;
import lombok.*;

@Getter
@Builder
public class GetDealRequestDto implements RequestDto {

    @Expose
    private final Filter filter;

    @Getter
    @Builder
    public static class Filter {
        @Expose
        private final String CLOSED; // именно "CLOSED", как в API

        // если понадобятся другие фильтры — добавите поля
    }

    @Override
    public String toJson() {
        return gson.toJson(this);
    }
}
