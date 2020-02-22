package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> listStorage = new ArrayList<>();

    @Override
    protected void doUpdate(Resume resume, Integer index) {
        listStorage.set(index, resume);
    }

    @Override
    protected void doSave(Resume resume, Integer key) {
        listStorage.add(resume);
    }

    @Override
    protected void doDelete(Integer index) {
        listStorage.remove(index.intValue());
    }

    @Override
    protected Resume doGet(Integer index) {
        return listStorage.get(index);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < listStorage.size(); i++)
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        return null;
    }

    protected boolean isExist(Integer index) {
        return index != null;
    }

    @Override
    public List<Resume> doCopyAll() {
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
