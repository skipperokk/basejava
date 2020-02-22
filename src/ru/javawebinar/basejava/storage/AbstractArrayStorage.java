package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void deleteElement(int index);

    protected abstract Integer getSearchKey(String uuid);

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int countOfResumes = 0;

    @Override
    protected void doUpdate(Resume resume, Integer index) {
        storage[index] = resume;
    }

    @Override
    protected void doSave(Resume resume, Integer index) {
        if (countOfResumes == STORAGE_LIMIT) {
            throw new StorageException("Хранилище заполнено", resume.getUuid());
        } else {
            insertElement(resume, index);
            countOfResumes++;
        }
    }

    @Override
    protected void doDelete(Integer index) {
        deleteElement(index);
        storage[countOfResumes - 1] = null;
        countOfResumes--;
    }

    @Override
    protected Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    protected List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, countOfResumes));
    }

    public int size() {
        return countOfResumes;
    }

    public void clear() {
        Arrays.fill(storage, 0, countOfResumes, null);
        countOfResumes = 0;
    }
}
