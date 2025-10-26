package ru.bitrix24.api.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface RequestDto {
    Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    default String toJson() {
        return GSON.toJson(this);
    }
}
