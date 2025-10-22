package ru.bitrix24.api.deals;

import com.google.gson.Gson;

public class DealDeleteResponseDto {
    private boolean result;

    public boolean isResult() {
        return result;
    }

    public static DealDeleteResponseDto fromJson(String json) {
        return new Gson().fromJson(json, DealDeleteResponseDto.class);
    }
}
