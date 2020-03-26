package ru.javawebinar.basejava.util;

import org.junit.Test;
import ru.javawebinar.basejava.model.AbstractSection;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.TextSection;

import static org.junit.Assert.assertEquals;
import static ru.javawebinar.basejava.ResumeTestData.R1;

public class JsonParserTest {

    @Test
    public void read() {
        String json = JsonParser.write(R1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        assertEquals(R1, resume);
    }

    @Test
    public void write() {
        AbstractSection as = new TextSection("Objective1");
        String json = JsonParser.write(as, AbstractSection.class);
        System.out.println(json);
        AbstractSection asNew = JsonParser.read(json, AbstractSection.class);
        assertEquals(as, asNew);
    }
}