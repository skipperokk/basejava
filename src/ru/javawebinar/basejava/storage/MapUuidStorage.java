package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected void doUpdate(Resume resume, Object uuid) {
        mapStorage.replace(uuid.toString(), resume);
    }

    @Override
    protected void doSave(Resume resume, Object uuid) {
        mapStorage.put(uuid.toString(), resume);
    }

    @Override
    protected void doDelete(Object uuid) {
        mapStorage.remove(uuid.toString());
    }

    @Override
    protected Resume doGet(Object uuid) {
        return mapStorage.get(uuid.toString());
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object uuid) {
        return mapStorage.containsKey(uuid.toString());
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }
}