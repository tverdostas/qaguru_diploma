package ru.bitrix24.enums;

public enum MainMenuItems {
    CRM("CRM"),
    ONLINE_REGISTRATION("Онлайн-запись"),
    WAREHOUSE_ACCOUNTING("Складской учёт"),
    MARKETING("Маркетинг"),
    WEBSITES_AND_STORES("Сайты и Магазины"),
    TASKS_AND_PROJECTS("Задачи и Проекты"),
    MESSENGER("Мессенджер"),
    RIBBON("Лента"),
    COLLABS("Коллабы"),
    CALENDAR("Календарь"),
    DOCUMENTS_ONLINE("Документы Онлайн"),
    BOARDS("Доски"),
    DISK("Диск"),
    MAIL("Почта"),
    GROUPS("Группы"),
    BI_CONSTRUCTOR("BI Конструктор"),
    EMPLOYEES("Сотрудники"),
    AUTOMATION("Автоматизация"),
    APPLICATIONS("Приложения"),
    MARKETPLACE("Маркетплейс"),
    FOR_DEVELOPERS("Разработчикам"),
    KEDO_GOSKLYUCH("КЭДО + Госключ"),
    SIGNATURE("Подпись"),
    MY_TARIFF("Мой тариф");

    // Геттер для получения названия на русском
    private final String displayName;

    // Приватный конструктор
    MainMenuItems(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }
}
