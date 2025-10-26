package ru.bitrix24.api.tasks;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.bitrix24.api.base.ResponseDto;
import ru.bitrix24.utils.GsonProvider;

@Getter
@Builder
@AllArgsConstructor
public class TaskDeleteResponseDto implements ResponseDto {

    private Result result;
    private Time time;

    @Getter
    public static class Time {
        private long start;
        private double finish;
        private double duration;
        private double processing;
        private String date_start;
        private String date_finish;
        private long operating_reset_at;
        private double operating;
    }
    @Getter
    public static class Result {
        private boolean task; // ← "task": true
    }
    private static final Gson GSON = new Gson();

    public static TaskDeleteResponseDto fromJson(String json) {
        return GSON.fromJson(json, TaskDeleteResponseDto.class);
    }

    // Удобный метод для проверки успеха
    public boolean isSuccess() {
        return result != null && result.task;
    }
}
