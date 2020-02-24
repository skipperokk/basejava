package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section {

    private final List<TextSection> listText;

    public ListSection(List<TextSection> listText) {
        this.listText = listText;
    }

    public List<TextSection> getListText() {
        return listText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return Objects.equals(listText, that.listText);
    }

    @Override
    public int hashCode() {
        return listText != null ? listText.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "List: " + listText.toString();
    }
}
