package ru.javawebinar.basejava.model;

public enum ContactsType {
    TELEPHONE("Телефон: "),
    SKYPE("Skype: "),
    MAIL("Почта: "),
    LINKEDIN("Профиль LinkedIn: "),
    GITHUB("Профиль GitHub: "),
    STACKOVERFLOW("Профиль Stack Overflow: "),
    HOME("Домашняя страница: ");

    private String title;

    ContactsType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}