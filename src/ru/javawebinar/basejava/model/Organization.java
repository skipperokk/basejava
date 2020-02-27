package ru.javawebinar.basejava.model;

import java.time.YearMonth;
import java.util.List;
import java.util.Objects;

public class Organization {

    private final Link homePage;
    private List<OrganizationDescribeBlock> odb;

    public Organization(String name, String url, List<OrganizationDescribeBlock> odb){
        this(new Link(name, url), odb);
    }

    public Organization(Link homePage, List<OrganizationDescribeBlock> odb) {
        this.homePage = homePage;
        this.odb = odb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!Objects.equals(homePage, that.homePage)) return false;
        return Objects.equals(odb, that.odb);
    }

    @Override
    public int hashCode() {
        int result = homePage != null ? homePage.hashCode() : 0;
        result = 31 * result + (odb != null ? odb.hashCode() : 0);
        return result;
    }

    private static class OrganizationDescribeBlock {
        private final YearMonth startDate;
        private final YearMonth endDate;
        private final String title;
        private final String description;

        public OrganizationDescribeBlock(YearMonth startDate, YearMonth endDate, String title, String description) {
            Objects.requireNonNull(startDate, "Start date must not be null");
            Objects.requireNonNull(endDate, "End date must not be null");
            Objects.requireNonNull(title, "Title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;

        }

        public YearMonth getStartDate() {
            return startDate;
        }

        public YearMonth getEndDate() {
            return endDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            OrganizationDescribeBlock that = (OrganizationDescribeBlock) o;

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
    }
}
