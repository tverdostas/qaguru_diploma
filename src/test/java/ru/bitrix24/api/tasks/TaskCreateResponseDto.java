package ru.bitrix24.api.tasks;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import ru.bitrix24.api.base.ResponseDto;

import java.util.List;

@Getter
@Setter
public class TaskCreateResponseDto implements ResponseDto {

    public static final Gson GSON = new Gson();

        private Result result;
        private Time time;

        @Getter
        @Setter
        public static class Result {
            private Task task;
        }

        @Getter
        @Setter
        public static class Task {
            private String id;
            private String parentId;
            private String title;
            private String description;
            private String mark;
            private String priority;
            private String multitask;
            private String notViewed;
            private String replicate;
            private String stageId;
            private String sprintId;
            private String backlogId;
            private String createdBy;
            private String createdDate;
            private String responsibleId;
            private String changedBy;
            private String changedDate;
            private String statusChangedBy;
            private String closedBy;
            private String closedDate;
            private String activityDate;
            private String dateStart;
            private String deadline;
            private String startDatePlan;
            private String endDatePlan;
            private String guid;
            private String xmlId;
            private String commentsCount;
            private String serviceCommentsCount;
            private String allowChangeDeadline;
            private String allowTimeTracking;
            private String taskControl;
            private String addInReport;
            private String forkedByTemplateId;
            private String timeEstimate;
            private String timeSpentInLogs;
            private String matchWorkTime;
            private String forumTopicId;
            private String forumId;
            private String siteId;
            private String subordinate;
            private String exchangeModified;
            private String exchangeId;
            private String outlookVersion;
            private String viewedDate;
            private String sorting;
            private String durationFact;
            private String isMuted;
            private String isPinned;
            private String isPinnedInGroup;
            private String flowId;
            private String descriptionInBbcode;
            private String status;
            private String statusChangedDate;
            private String durationPlan;
            private String durationType;
            private String favorite;
            private String groupId;

            private List<String> auditors;
            private List<String> accomplices;
            private List<Object> checklist; // можно уточнить тип позже
            private List<Object> group;

            private User creator;
            private User responsible;
            private List<Object> accomplicesData;
            private List<Object> auditorsData;

            private Integer newCommentsCount;

            private Action action;
            private CheckListTree checkListTree;
            private Boolean checkListCanAdd;
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
        public static class Action {
            private Boolean accept;
            private Boolean decline;
            private Boolean complete;
            private Boolean approve;
            private Boolean disapprove;
            private Boolean start;
            private Boolean pause;
            private Boolean delegate;
            private Boolean remove;
            private Boolean edit;
            private Boolean defer;
            private Boolean renew;
            private Boolean create;
            private Boolean changeDeadline;
            private Boolean checklistAddItems;
            private Boolean addFavorite;
            private Boolean deleteFavorite;
            private Boolean rate;
            private Boolean take;
            private Boolean editOriginator;

            @SerializedName("checklist.reorder")
            private Boolean checklistReorder;

            @SerializedName("elapsedtime.add")
            private Boolean elapsedTimeAdd;

            @SerializedName("dayplan.timer.toggle")
            private Boolean dayplanTimerToggle;

            @SerializedName("edit.plan")
            private Boolean editPlan;

            @SerializedName("checklist.add")
            private Boolean checklistAdd;

            @SerializedName("favorite.add")
            private Boolean favoriteAdd;

            @SerializedName("favorite.delete")
            private Boolean favoriteDelete;
        }

        @Getter
        @Setter
        public static class CheckListTree {
            private Integer nodeId;
            private CheckListFields fields;
            private List<Object> action;
            private List<Object> descendants;
        }

        @Getter
        @Setter
        public static class CheckListFields {
            private String id;
            private String copiedId;
            private String entityId;
            private Integer userId;
            private String createdBy;
            private String parentId;
            private String title;
            private Integer sortIndex;
            private String displaySortIndex;
            private Boolean isComplete;
            private Boolean isImportant;
            private Integer completedCount;
            private List<Object> members;
            private List<Object> attachments;
            private Integer nodeId;
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

    public static TaskCreateResponseDto fromJson(String json) {
        return GSON.fromJson(json, TaskCreateResponseDto.class);
    }
    }