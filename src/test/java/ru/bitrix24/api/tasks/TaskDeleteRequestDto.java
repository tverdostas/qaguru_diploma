package ru.bitrix24.api.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.bitrix24.api.base.RequestDto;

@Getter
@Builder
@AllArgsConstructor
public class TaskDeleteRequestDto implements RequestDto {
    @Expose
    private final Integer taskId;

    private static final Gson gson = new Gson();
    }
