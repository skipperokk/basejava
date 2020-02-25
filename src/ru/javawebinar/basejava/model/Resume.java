package ru.javawebinar.basejava.model;

import java.util.*;

public class Resume implements Comparable<Resume> {

    private final String uuid;

    private final String fullName;

    private final Map<SectionType, AbstractSection> sectionTypeMap = new EnumMap<>(SectionType.class);
    private final Map<ContactsType, String> contactsTypeMap = new EnumMap<>(ContactsType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        //предусловие (uuid и fullName не должны быть равны null)
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getContacts(ContactsType type) {
        return contactsTypeMap.get(type);
    }

    public void addSection(SectionType sectionType, AbstractSection section) {
        sectionTypeMap.put(sectionType, section);
    }

    public AbstractSection getSection(SectionType type) {
        return sectionTypeMap.get(type);
    }

    public void addContact(ContactsType contactsType, String text) {
        contactsTypeMap.put(contactsType, text);
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        if (!fullName.equals(resume.fullName)) return false;
        if (!sectionTypeMap.equals(resume.sectionTypeMap)) return false;
        return contactsTypeMap.equals(resume.contactsTypeMap);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + sectionTypeMap.hashCode();
        result = 31 * result + contactsTypeMap.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }

    @Override
    public int compareTo(Resume o) {
        int compare = fullName.compareTo(o.fullName);
        return compare != 0 ? compare : uuid.compareTo(o.uuid);
    }
}