package ru.bitrix24.api.deals;

import com.google.gson.Gson;
import lombok.Getter;
import ru.bitrix24.api.base.ResponseDto;
@Getter
public class DealCreateResponseDto implements ResponseDto {
    private Integer result;
    private TimeInfo time;

    private static final Gson gson = new Gson();

    public static DealCreateResponseDto fromJson(String json) {
        return gson.fromJson(json, DealCreateResponseDto.class);
    }
}
