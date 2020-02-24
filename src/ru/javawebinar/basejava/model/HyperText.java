package ru.javawebinar.basejava.model;

public class HyperText {
    private String text;
    private String link;

    public HyperText(String text, String link) {
        this.text = text;
        this.link = link;
    }

    public String getHyperText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HyperText that = (HyperText) o;

        if (!text.equals(that.text)) return false;
        return link.equals(that.link);
    }

    @Override
    public int hashCode() {
        int result = text.hashCode();
        result = 31 * result + link.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "HyperText{" +
                "text='" + text + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
