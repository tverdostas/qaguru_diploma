package ru.bitrix24.api.tasks;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import ru.bitrix24.api.base.ResponseDto;

import java.util.List;

@Getter
@Setter
public class TaskListResponseDto implements ResponseDto {

    private Result result;
    private Integer total;
    private Time time;

    @Getter
    @Setter
    public static class Result {
        private List<Task> tasks;
    }

    @Getter
    @Setter
    public static class Task {
        private String title;
        private String deadline;
        private String createdBy;
        private String responsibleId;
        private String id;

        private List<Object> group; // пустой массив в примере

        private User creator;
        private User responsible;
    }

    @Getter
    @Setter
    public static class User {
        private String id;
        private String name;
        private String link;
        private String icon;
        private String workPosition;
    }

    @Getter
    @Setter
    public static class Time {
        private Long start;
        private Double finish;
        private Double duration;
        private Double processing;
        private String date_start;
        private String date_finish;
        private Long operating_reset_at;
        private Double operating;
    }
    public static TaskListResponseDto fromJson(String json) {
        return new Gson().fromJson(json, TaskListResponseDto.class);
    }
}
