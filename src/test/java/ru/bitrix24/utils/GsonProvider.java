package ru.bitrix24.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GsonProvider {
    public static final Gson GSON = new Gson(); // ← без excludeFieldsWithoutExposeAnnotation
    private GsonProvider() {}
}
