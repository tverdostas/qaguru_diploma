package ru.bitrix24.api.deals;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.bitrix24.api.base.RequestDto;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class DealCreateRequestDto implements RequestDto {

    @Expose
    private final Map<String, String> fields;

/*    @Expose
    private final Map<String, String> params;*/

    private static final Gson gson = new Gson();

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Fields {
        // Пример: DATE_CREATE -> "DESC"
        @Expose
        private final String TITLE;
    }

    @Override
    public String toJson() {
        return gson.toJson(this);
    }
}
