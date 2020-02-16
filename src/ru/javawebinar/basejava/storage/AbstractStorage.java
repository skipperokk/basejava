package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void saveElement(Resume resume, int key);

    protected abstract void updateElement(Resume resume, int key);

    protected abstract Resume getElement(int key);

    protected abstract void deleteElement(int key);

    protected abstract int getKey(String uuid);

    protected abstract boolean existKey(int key);


    public void update(Resume resume) {
        int key = getNotExistStorageException(resume.getUuid());
        updateElement(resume, key);
    }

    public void save(Resume resume) {
        int key = getExistStorageException(resume.getUuid());
        saveElement(resume, key);
    }

    public void delete(String uuid) {
        int key = getNotExistStorageException(uuid);
        deleteElement(key);
    }

    public Resume get(String uuid) {
        int key = getNotExistStorageException(uuid);
        return getElement(key);
    }

    private int getNotExistStorageException(String uuid){
        int key = getKey(uuid);
        if (!existKey(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private int getExistStorageException(String uuid){
        int key = getKey(uuid);
        if (existKey(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }
}
