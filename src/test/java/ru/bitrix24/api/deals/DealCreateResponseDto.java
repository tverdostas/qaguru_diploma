package ru.bitrix24.api.deals;

import lombok.Getter;
import ru.bitrix24.api.base.ResponseDto;
@Getter
public class DealCreateResponseDto implements ResponseDto {
    private Integer result;
    private TimeInfo time;

    public static DealCreateResponseDto fromJson(String json) {
        return gson.fromJson(json, DealCreateResponseDto.class);
    }
}
