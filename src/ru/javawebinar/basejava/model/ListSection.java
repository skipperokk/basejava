package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {

    private final List<TextSection> items;

    public ListSection(List<TextSection> items) {
        Objects.requireNonNull(items, "Items must not be null");
        this.items = items;
    }

    public List<TextSection> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return items != null ? items.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "List: " + items.toString();
    }
}
