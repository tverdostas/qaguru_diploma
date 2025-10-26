package ru.bitrix24.api.tasks;

import io.qameta.allure.Step;

import java.util.List;
import java.util.Map;

public class TaskSteps {
    TaskApi taskApi = new TaskApi();

    @Step("Получить общее количество задач")
    private int getTaskCount() {
        TaskListRequestDto request = TaskListRequestDto.builder()
                .select(List.of("id"))
                .build();

        TaskListResponseDto response = taskApi.getTaskList(request);
        return response.getTotal();
    }

    @Step("Получить задачу по ID")
    private TaskListResponseDto getTaskById(String taskId) {
        TaskListRequestDto request = TaskListRequestDto.builder()
                .select(List.of("TITLE", "description", "DEADLINE", "CREATED_BY", "RESPONSIBLE_ID"))
                .build();

        return taskApi.getTaskListWithFilter(request, "ID", taskId);
    }

    @Step("Добавить пункт чек-листа к задаче")
    private void addChecklistItem(String taskId, String title) {
        // Пример тела для task.checklistitem.add
        Map<String, Object> params = Map.of(
                "taskId", taskId,
                "title", title
        );
    }
}
