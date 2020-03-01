package ru.javawebinar.basejava.model;

import java.util.List;

public class Organization {
    private final Link homePage;
    private List<Position> descriptionList;

    public Organization(String name, String url, List<Position> descriptionList) {
        this.homePage = new Link(name, url);
        this.descriptionList = descriptionList;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Position> getDescriptionList() {
        return descriptionList;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage + "Description=" + descriptionList + "}";
    }
}