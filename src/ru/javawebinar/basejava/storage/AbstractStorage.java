package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected List<Resume> listStorage;

    public AbstractStorage(List<Resume> listStorage) {
        this.listStorage = listStorage;
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public void update(Resume resume) {
        int index = getIndexList(resume.getUuid());
        if (index == -1) {
            throw new NotExistStorageException(resume.getUuid());
        }
        listStorage.set(index, resume);
    }

    @Override
    public void save(Resume resume) {
        int index = getIndexList(resume.getUuid());
        listStorage.add(resume);
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndexList(uuid);
        if (index == -1) {
            throw new NotExistStorageException(uuid);
        }
        return listStorage.get(index);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndexList(uuid);
        if (index == -1) {
            throw new NotExistStorageException(uuid);
        }
        listStorage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        Resume[] array = new Resume[listStorage.size()];
        listStorage.toArray(array);
        return array;
    }

    protected abstract int getIndexList(String uuid);
}
