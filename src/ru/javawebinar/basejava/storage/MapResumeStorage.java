package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected void doUpdate(Resume resume, Object key) {
        mapStorage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, Object key) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object key) {
        mapStorage.remove(((Resume) (key)).getUuid());
    }

    @Override
    protected Resume doGet(Object key) {
        return (Resume) key;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return mapStorage.get(uuid);
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

    @Override
    public List<Resume> doCopyArray() {
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