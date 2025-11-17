package ru.bitrix24.api.tasks;

import io.restassured.specification.RequestSpecification;
import ru.bitrix24.api.base.BaseApi;

import ru.bitrix24.api.deals.DealDeleteResponseDto;
import ru.bitrix24.api.deals.DealListResponseDto;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TaskApi extends BaseApi {

    public TaskCreateResponseDto createTask(TaskCreateRequestDto request) {
        String json = given().log().all()
                .spec(defaultRequestSpec)
                .body(request.toJson())
                .when()
                .post("tasks.task.add")
                .then()
                .statusCode(200)
                .extract().asString();

        return TaskCreateResponseDto.fromJson(json);
    }

    public TaskListResponseDto getTaskList(TaskListRequestDto request) {
        return getTaskListWithFilter(request, null, null);
    }

    public TaskListResponseDto getTaskListWithFilter(TaskListRequestDto request, String filterField, String filterValue) {
        RequestSpecification spec = given().spec(defaultRequestSpec).body(request.toJson());
        if (filterField != null && filterValue != null) {
            spec = spec.queryParam("filter[" + filterField + "]", filterValue);
        }
        String json = spec
                .when()
                .post("tasks.task.list")
                .then()
                .statusCode(200)
                .extract().asString();
        return TaskListResponseDto.fromJson(json);
    }

/*    public void addChecklistItem(Map<String, Object> params) {
        given().log().all()
                .spec(defaultRequestSpec)
                .body(params) // Gson сам сериализует Map в JSON
                .when()
                .post("/task.checklistitem.add")
                .then()
                .statusCode(200);
    }*/

    public int getTaskCount() {
        // Запрашиваем только ID, чтобы уменьшить объём данных
        TaskListRequestDto request = TaskListRequestDto.builder()
                .select(List.of("id"))
                .build();

        String json = given()
                .log().all()
                .spec(defaultRequestSpec)
                .body(request.toJson())
                .when()
                .post("tasks.task.list")
                .then()
                .statusCode(200)
                .extract().asString();

        TaskListResponseDto response = TaskListResponseDto.fromJson(json);
        return response.getTotal(); // getTotal() → int
    }
    // 1. Получить задачу по ID
    public TaskListResponseDto getTaskById(String taskId) {
        TaskListRequestDto request = TaskListRequestDto.builder()
                .select(List.of("TITLE", "description", "DEADLINE", "CREATED_BY", "RESPONSIBLE_ID"))
                .build();

        return given()
                .spec(defaultRequestSpec)
                .body(request.toJson())
                .queryParam("filter[ID]", taskId) // Bitrix24: фильтр через query param
                .when()
                .post("tasks.task.list")
                .then()
                .statusCode(200)
                .extract()
                .as(TaskListResponseDto.class); // или через fromJson
    }

    // 2. Добавить пункт чек-листа
    public void addChecklistItem(String taskId, String title) {
        Map<String, Object> body = Map.of(
                "taskId", taskId,
                "title", title
        );
        given()
                .spec(defaultRequestSpec)
                .body(body)
                .when()
                .post("task.checklistitem.add")
                .then()
                .statusCode(200);
    }

    public boolean deleteTask(String taskId) {
        String json = given().log().all()
                .spec(defaultRequestSpec)
                .queryParam("id", taskId)
                .when()
                .post("tasks.task.delete") // или твой endpoint
                .then()
                .statusCode(200)
                .extract().asString();

        return TaskDeleteResponseDto.fromJson(json).isSuccess();
    }
}
