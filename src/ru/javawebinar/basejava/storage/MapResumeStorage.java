package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected void doUpdate(Resume resume, Resume key) {
        mapStorage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, Resume key) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Resume key) {
        mapStorage.remove(key.getUuid());
    }

    @Override
    protected Resume doGet(Resume key) {
        return key;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return mapStorage.get(uuid);
    }

    @Override
    protected boolean isExist(Resume key) {
        return key != null;
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