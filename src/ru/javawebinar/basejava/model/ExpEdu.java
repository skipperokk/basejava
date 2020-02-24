package ru.javawebinar.basejava.model;

import java.util.Objects;

public class ExpEdu {
    private String startDate;
    private String finishDate;
    private String title;
    private String description;
    private HyperText home;

    public ExpEdu(String startDate, String finishDate, String title, String description, String text, String link) {
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

        ExpEdu that = (ExpEdu) o;

        if (!startDate.equals(that.startDate)) return false;
        if (!finishDate.equals(that.finishDate)) return false;
        if (!title.equals(that.title)) return false;
        if (!description.equals(that.description)) return false;
        return Objects.equals(home, that.home);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + finishDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (home != null ? home.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ExpEduBlock{" +
                "startDate='" + startDate + '\'' +
                ", finishDate='" + finishDate + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", home=" + home +
                '}';
    }
}
