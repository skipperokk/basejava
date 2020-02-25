package ru.javawebinar.basejava.model;

import java.util.Objects;

public class HyperText {
    private final String text;
    private final String link;

    public HyperText(String text, String link) {
        Objects.requireNonNull(text, "text must not be null");
        this.text = text;
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HyperText hyperText = (HyperText) o;

        if (!text.equals(hyperText.text)) return false;
        return Objects.equals(link, hyperText.link);
    }

    @Override
    public int hashCode() {
        int result = text.hashCode();
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HyperText(" +
                "text='" + text + '\'' +
                ", link='" + link + '\'' +
                ')';
    }
}
