package ru.bitrix24.enums;

import lombok.Getter;

@Getter
public enum DealsStatus {
    NEW("Новая"),
    DOCUMENTS_PREPARATION("Подготовка документов"),
    PREPAYMENT_INVOICE("Счёт на предоплату"),
    IN_WORK("В работе"),
    FINAL_INVOICE("Финальный счет"),
    DEAL_COMPLETED("Завершить сделку");

    // Геттер для получения названия на русском
    private final String displayName;

    // Приватный конструктор
    DealsStatus(String displayName) {
        this.displayName = displayName;
    }

}
