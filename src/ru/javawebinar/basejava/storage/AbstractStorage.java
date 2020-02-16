package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void updateElement(Resume resume, Object key);

    protected abstract void saveElement(Resume resume, Object key);

    protected abstract Resume getElement(Object key);

    protected abstract void deleteElement(Object key);

    protected abstract Object getKey(String uuid);

    protected abstract boolean isExist(Object key);


    public void update(Resume resume) {
        Object key = getExistedKey(resume.getUuid());
        updateElement(resume, key);
    }

    public void save(Resume resume) {
        Object key = getNotExistedKey(resume.getUuid());
        saveElement(resume, key);
    }

    public void delete(String uuid) {
        Object key = getExistedKey(uuid);
        deleteElement(key);
    }

    public Resume get(String uuid) {
        Object key = getExistedKey(uuid);
        return getElement(key);
    }

    private Object getExistedKey(String uuid) {
        Object key = getKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private Object getNotExistedKey(String uuid) {
        Object key = getKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }
}
