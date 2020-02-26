package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Телефон: "),
    MOBILE("Мобильный телефон: "),
    HOME_PHONE("Домашний телефон: "),
    SKYPE("Skype: "),
    MAIL("Почта: "),
    LINKEDIN("Профиль LinkedIn: "),
    GITHUB("Профиль GitHub: "),
    STACKOVERFLOW("Профиль Stack Overflow: "),
    HOME_PAGE("Домашняя страница: ");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}