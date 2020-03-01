package ru.javawebinar.basejava.model;

import java.time.YearMonth;
import java.util.Objects;

public class Position {
    private final YearMonth startDate;
    private final YearMonth endDate;
    private final String title;
    private final String description;

    public Position(YearMonth startDate, YearMonth endDate, String title, String description) {
        Objects.requireNonNull(startDate, "Start date must not be null");
        Objects.requireNonNull(endDate, "End date must not be null");
        Objects.requireNonNull(title, "Title must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position that = (Position) o;

        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!title.equals(that.title)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return startDate + " - "
                + endDate + ", " + title + ", " + description;
    }
}
