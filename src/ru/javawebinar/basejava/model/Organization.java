package ru.javawebinar.basejava.model;

import java.time.YearMonth;
import java.util.Objects;

public class Organization {
    private final YearMonth startDate;
    private final YearMonth endDate;
    private final String title;
    private final String description;

    private final Link homePage;

    public Organization(YearMonth startDate, YearMonth endDate, String title, String description, String name, String url) {
        Objects.requireNonNull(startDate, "Start date must not be null");
        Objects.requireNonNull(endDate, "End date must not be null");
        Objects.requireNonNull(title, "Title must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
        this.homePage = new Link(name, url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!title.equals(that.title)) return false;
        if (!Objects.equals(description, that.description)) return false;
        return homePage.equals(that.homePage);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + homePage.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return startDate + " - "
                + endDate + ", " + title + ", " + description + ", " + homePage;
    }
}
