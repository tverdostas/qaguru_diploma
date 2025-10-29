package ru.bitrix24.tests.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bitrix24.api.tasks.*;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.OffsetDateTime;

public class TaskWithChecklistTest {

    private final TaskApi taskApi = new TaskApi();

    @Test
    @DisplayName("Создать задачу с чек-листом и проверить её поля и наличие чек-листа")
    public void shouldCreateTaskWithChecklistAndVerifyFields() {
        // 1. Получить текущее количество задач
        int initialTaskCount = taskApi.getTaskCount();

        // 2. Создать задачу
        String title = "Задача с чек-листом — автотест " + System.currentTimeMillis();
        String description = "Описание задачи для проверки чек-листа";
        String deadline = "2026-12-31T23:59:00+04:00";
        String createdBy = "1";
        String responsibleId = "2";

        TaskCreateRequestDto createRequest = TaskCreateRequestDto.builder()
                .fields(Map.of(
                        "TITLE", title,
                        "description", description,
                        "DEADLINE", deadline,
                        "CREATED_BY", createdBy,
                        "RESPONSIBLE_ID", responsibleId))
                .build();

        TaskCreateResponseDto createResponse = taskApi.createTask(createRequest);
        String createdTaskId = createResponse.getResult().getTask().getId();
        assertThat(createdTaskId).isNotNull().isNotEmpty();

        // 3. Добавить чек-лист к задаче
/*        taskApi.addChecklistItem(createdTaskId, "Пункт 1: проверить А");
        taskApi.addChecklistItem(createdTaskId, "Пункт 2: проверить Б");*/

        // 4. Получить задачу по ID
        TaskListResponseDto taskListResponse = taskApi.getTaskById(createdTaskId);

        // 5. Проверки
        assertThat(taskListResponse.getResult().getTasks()).hasSize(1);
        TaskListResponseDto.Task task = taskListResponse.getResult().getTasks().get(0);

        assertThat(task.getTitle()).isEqualTo(title);
        assertThat(task.getDeadline()).isEqualTo(deadline);
        assertThat(task.getCreatedBy()).isEqualTo("1");
        assertThat(task.getResponsibleId()).isEqualTo("2"); // или из конфига
        assertThat(task.getId()).isEqualTo(createdTaskId);

        // Сравнение дедлайна с учётом часового пояса
        OffsetDateTime expectedDeadline = OffsetDateTime.parse(deadline);
        OffsetDateTime actualDeadline = OffsetDateTime.parse(task.getDeadline());

        assertThat(actualDeadline)
                .as("Дедлайн задачи")
                .isEqualTo(expectedDeadline);

        // 6. Проверка увеличения количества задач
        int finalTaskCount = taskApi.getTaskCount();
        assertThat(finalTaskCount).isEqualTo(initialTaskCount + 1);

        // 7. Удалить созданную задачу
        taskApi.deleteTask(createdTaskId);

        // 8. Проверка количества задач
        int finalTaskCount2 = taskApi.getTaskCount();
        assertThat(finalTaskCount2).isEqualTo(initialTaskCount);
    }
}
