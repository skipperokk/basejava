package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume resume, OutputStream os) {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            collectionWriter(dos, contacts.entrySet(), writer -> {
                dos.writeUTF(writer.getKey().name());
                dos.writeUTF(writer.getValue());
            });

            Map<SectionType, AbstractSection> sections = resume.getSections();
            collectionWriter(dos, sections.entrySet(), writer -> {
                SectionType sectionType = writer.getKey();
                AbstractSection abstractSection = writer.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) abstractSection).getContent());
                        break;

                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        collectionWriter(dos, ((ListSection) abstractSection).getItems(), dos::writeUTF);
                        break;

                    case EXPERIENCE:
                    case EDUCATION:
                        collectionWriter(dos, ((OrganizationSection) abstractSection).getOrganizations(), orgList -> {
                            dos.writeUTF(orgList.getHomePage().getName());
                            dos.writeUTF(orgList.getHomePage().getUrl());
                            collectionWriter(dos, orgList.getPositions(), posList -> {
                                localDateWriter(dos, posList.getStartDate());
                                localDateWriter(dos, posList.getEndDate());
                                dos.writeUTF(posList.getTitle());
                                dos.writeUTF(posList.getDescription());
                            });
                        });
                        break;
                }
            });
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @FunctionalInterface
    private interface ConsumerModified<T> {
        void accept(T result) throws IOException;
    }

    private <T> void collectionWriter(DataOutputStream dos, Collection<T> collection, ConsumerModified<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T result : collection) {
            writer.accept(result);
        }
    }

    private void localDateWriter(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonth().getValue());
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readItems(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readItems(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, sectionReader(dis, sectionType));
            });
            return resume;
        }
    }

    private AbstractSection sectionReader(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());

            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ListSection(collectionReader(dis, dis::readUTF));

            case EXPERIENCE:
            case EDUCATION:
                return new OrganizationSection(collectionReader(dis, () ->
                        new Organization(new Link(dis.readUTF(), dis.readUTF()),
                                collectionReader(dis, () ->
                                        new Organization.Position(localDateReader(dis), localDateReader(dis),
                                                dis.readUTF(), dis.readUTF()))
                        )));

            default:
                throw new IllegalStateException();
        }
    }

    private LocalDate localDateReader(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    @FunctionalInterface
    private interface SupplierModified<T> {
        T get() throws IOException;
    }

    private <T> List<T> collectionReader(DataInputStream dis, SupplierModified<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.get());
        }
        return list;
    }

    @FunctionalInterface
    private interface CheckInterface {
        void checked() throws IOException;
    }

    private void readItems(DataInputStream dis, CheckInterface checkInterface) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            checkInterface.checked();
        }
    }
}