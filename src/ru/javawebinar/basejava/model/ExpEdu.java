package ru.javawebinar.basejava.model;

import java.time.YearMonth;
import java.util.Objects;

public class ExpEdu {
    private YearMonth startDate;
    private YearMonth finishDate;
    private String title;
    private String description;
    private HyperText home;

    public ExpEdu(YearMonth startDate, YearMonth finishDate, String title, String description, String text, String link) {
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

        ExpEdu expEdu = (ExpEdu) o;

        if (!startDate.equals(expEdu.startDate)) return false;
        if (!finishDate.equals(expEdu.finishDate)) return false;
        if (!title.equals(expEdu.title)) return false;
        if (!Objects.equals(description, expEdu.description)) return false;
        return Objects.equals(home, expEdu.home);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + finishDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (home != null ? home.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return startDate + " - "
                + finishDate + ", " + title + ", " + description + ", " + home;
    }
}
