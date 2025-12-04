package ru.bitrix24.api.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.bitrix24.api.base.RequestDto;

import java.util.Map;
@Getter
@Builder
@AllArgsConstructor
public class TaskCreateRequestDto implements RequestDto {
    @Expose
    private final Map<String, String> fields;

    private static final Gson gson = new Gson();

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Fields {
        // Пример: DATE_CREATE -> "DESC"
        @Expose
        private final String TITLE;
    }

    // Единый Gson-инстанс для всех DTO
    private static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public String toJson() {
        return GSON.toJson(this);
    }
}
