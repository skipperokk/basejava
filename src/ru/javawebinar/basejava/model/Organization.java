package ru.javawebinar.basejava.model;

import java.time.YearMonth;
import java.util.Objects;

public class Organization {
    private final YearMonth startDate;
    private final YearMonth finishDate;
    private final String title;
    private final String description;
    private final HyperText home;

    public Organization(YearMonth startDate, YearMonth finishDate, String title, String description, String text, String link) {
        Objects.requireNonNull(startDate, "start date must not be null");
        Objects.requireNonNull(finishDate, "finish date must not be null");
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(text, "text must not be null");
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.title = title;
        this.description = description;
        this.home = new HyperText(text, link);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!startDate.equals(that.startDate)) return false;
        if (!finishDate.equals(that.finishDate)) return false;
        if (!title.equals(that.title)) return false;
        if (!Objects.equals(description, that.description)) return false;
        return home.equals(that.home);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + finishDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + home.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return startDate + " - "
                + finishDate + ", " + title + ", " + description + ", " + home;
    }
}
