package ru.bitrix24.api.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.bitrix24.api.base.RequestDto;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class TaskListRequestDto implements RequestDto {

    @Expose
    private final List<String> select;

    public static TaskListResponseDto fromJson(String json) {
        return new Gson().fromJson(json, TaskListResponseDto.class);
    }
}
