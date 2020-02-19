package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void deleteElement(int index);

    protected abstract Integer getSearchKey(String uuid);

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int countOfResumes = 0;

    @Override
    protected void doUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    protected void doSave(Resume resume, Object index) {
        if (countOfResumes == STORAGE_LIMIT) {
            throw new StorageException("Хранилище заполнено", resume.getUuid());
        } else {
            insertElement(resume, (Integer) index);
            countOfResumes++;
        }
    }

    @Override
    protected void doDelete(Object index) {
        deleteElement((Integer) index);
        storage[countOfResumes - 1] = null;
        countOfResumes--;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    public List<Resume> getAllSorted() {
        return Arrays.asList(Arrays.copyOf(storage, countOfResumes));
    }

    public int size() {
        return countOfResumes;
    }

    public void clear() {
        Arrays.fill(storage, 0, countOfResumes, null);
        countOfResumes = 0;
    }
}
