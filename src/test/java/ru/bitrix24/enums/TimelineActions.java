package ru.bitrix24.enums;

import lombok.Getter;

@Getter
public enum TimelineActions {
    BUSINESS("Дело"),
    COMMENT("Комментарий"),
    MESSAGE("Сообщение"),
/*    ONLINE_REGISTRATION("Онлайн-запись"),
    TASK("Задача")*/;

    // Геттер для получения названия на русском
    private final String displayName;

    // Приватный конструктор
    TimelineActions(String displayName) {
        this.displayName = displayName;
    }
}
