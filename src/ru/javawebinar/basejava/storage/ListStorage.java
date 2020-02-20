package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> listStorage = new ArrayList<>();

    @Override
    protected void doUpdate(Resume resume, Object index) {
        listStorage.set((Integer) index, resume);
    }

    @Override
    protected void doSave(Resume resume, Object key) {
        listStorage.add(resume);
    }

    @Override
    protected void doDelete(Object index) {
        listStorage.remove(((Integer) index).intValue());
    }

    @Override
    protected Resume doGet(Object index) {
        return listStorage.get((Integer) index);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < listStorage.size(); i++)
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        return null;
    }

    protected boolean isExist(Object index) {
        return index != null;
    }

    @Override
    public List<Resume> doCopyArray() {
        return new ArrayList<>(listStorage);
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    public void clear() {
        listStorage.clear();
    }
}
