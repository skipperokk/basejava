package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int countOfResumes = 0;

    public int size() {
        return countOfResumes;
    }

    public void clear() {
        Arrays.fill(storage, 0, countOfResumes, null);
        countOfResumes = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            throw new NotExistStorageException(resume.getUuid());
        } else
            storage[index] = resume;
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else if (countOfResumes == STORAGE_LIMIT) {
            throw new StorageException("Хранилище заполнено", resume.getUuid());
        } else {
            insertElement(resume, index);
            countOfResumes++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteElement(index);
            storage[countOfResumes - 1] = null;
            countOfResumes--;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index <= -1) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, countOfResumes);
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void deleteElement(int index);

    protected abstract int getIndex(String uuid);
}
