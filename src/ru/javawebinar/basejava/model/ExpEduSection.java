package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ExpEduSection extends Section {
    private final List<ExpEdu> expEduList;

    public ExpEduSection(List<ExpEdu> expEduList) {
        this.expEduList = expEduList;
    }

    public List<ExpEdu> getExpEduList() {
        return expEduList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpEduSection that = (ExpEduSection) o;

        return Objects.equals(expEduList, that.expEduList);
    }

    @Override
    public int hashCode() {
        return expEduList != null ? expEduList.hashCode() : 0;
    }

    @Override
    public String toString() {
        return expEduList.toString();
    }
}
