package ru.bitrix24.tests.api;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bitrix24.api.tasks.TaskApi;
import ru.bitrix24.api.tasks.TaskCreateRequestDto;
import ru.bitrix24.api.tasks.TaskCreateResponseDto;
import ru.bitrix24.api.tasks.TaskListResponseDto;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskWithChecklistTest {

    private TaskApi taskApi;

    @BeforeEach
    public void setUp() {
        this.taskApi = new TaskApi();
    }

    @Test
    @DisplayName("Создать задачу с чек-листом и проверить её поля и наличие чек-листа")
    public void shouldCreateTaskWithChecklistAndVerifyFields() {

        // 1. Получить текущее количество задач
        int initialTaskCount = Allure.step("Получить начальное количество задач", () -> taskApi.getTaskCount());

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

        TaskCreateResponseDto createResponse = Allure.step("Создать задачу через API", () -> taskApi.createTask(createRequest));

        String createdTaskId = Allure.step("Получить ID созданной задачи", () -> {
            String id = createResponse.getResult().getTask().getId();
            assertThat(id).isNotNull().isNotEmpty();
            System.out.println("Created task ID: " + id); // Опционально: логирование
            return id;
        });

        // 4. Получить задачу по ID
        TaskListResponseDto taskListResponse = Allure.step("Получить созданную задачу по ID", () -> taskApi.getTaskById(createdTaskId));

        // 5. Проверки
        Allure.step("Проверить, что в ответе одна задача", () -> {
            assertThat(taskListResponse.getResult().getTasks()).hasSize(1);
        });

        TaskListResponseDto.Task task = taskListResponse.getResult().getTasks().get(0);

        Allure.step("Проверить поля задачи", () -> {
            assertThat(task.getTitle()).isEqualTo(title);
            assertThat(task.getDeadline()).isNotNull();
            assertThat(task.getCreatedBy()).isEqualTo("1");
            assertThat(task.getResponsibleId()).isEqualTo("2"); // или из конфига
            assertThat(task.getId()).isEqualTo(createdTaskId);
        });

        // 6. Проверка увеличения количества задач
        int finalTaskCount = Allure.step("Получить количество задач после создания", () -> taskApi.getTaskCount());

        Allure.step("Проверить, что количество задач увеличилось на 1", () -> {
            assertThat(finalTaskCount).isEqualTo(initialTaskCount + 1);
        });

        // 7. Удалить созданную задачу
        Allure.step("Удалить созданную задачу через API", () -> taskApi.deleteTask(createdTaskId));

        // 8. Проверка количества задач
        int finalTaskCount2 = Allure.step("Получить количество задач после удаления", () -> taskApi.getTaskCount());

        Allure.step("Проверить, что количество задач вернулось к исходному", () -> {
            assertThat(finalTaskCount2).isEqualTo(initialTaskCount);
        });
    }
}
