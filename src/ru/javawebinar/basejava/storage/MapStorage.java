package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected void updateElement(Resume resume, Object key) {
        mapStorage.replace(key.toString(), resume);
    }

    @Override
    protected void saveElement(Resume resume, Object key) {
        mapStorage.put(key.toString(), resume);
    }

    @Override
    protected Resume getElement(Object key) {
        return mapStorage.get(key.toString());
    }

    @Override
    protected void deleteElement(Object key) {
        mapStorage.remove(key.toString());
    }

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object key) {
        return mapStorage.containsKey(key.toString());
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] array = new Resume[mapStorage.size()];
        return mapStorage.values().toArray(array);
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}