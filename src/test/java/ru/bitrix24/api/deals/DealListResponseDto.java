package ru.bitrix24.api.deals;

import com.google.gson.Gson;
import lombok.Getter;

import java.util.List;

@Getter
public class DealListResponseDto {
    private List<Deal> result;
    private Integer next;
    private Integer total;
    private TimeInfo time;

    private static final Gson gson = new Gson();

    public static DealListResponseDto fromJson(String json) {
        return gson.fromJson(json, DealListResponseDto.class);
    }
}
