package ru.bitrix24.api.deals;

import com.google.gson.Gson;
import lombok.Getter;

@Getter
public class DealCreateResponseDto {
    private Integer result;
    private TimeInfo time;

    private static final Gson gson = new Gson();

    public static DealCreateResponseDto fromJson(String json) {
        return gson.fromJson(json, DealCreateResponseDto.class);
    }
}
