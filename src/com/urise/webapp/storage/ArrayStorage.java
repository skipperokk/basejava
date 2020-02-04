package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int countOfResumes = 0;

    public void clear() {
        for (int i = 0; i < countOfResumes; i++) {
            storage[i] = null;
        }
        countOfResumes = 0;
    }

    public void save(Resume r) {
        storage[countOfResumes++] = r;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < countOfResumes; i++) {
            if (uuid.equals(storage[i].getUuid()))
                return storage[i];
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < countOfResumes; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                for (int j = i; j < countOfResumes - 1; j++) {
                    storage[j] = storage[j + 1];
                }
                countOfResumes--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[countOfResumes];
        for (int i = 0; i < countOfResumes; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    public int size() {
        return countOfResumes;
    }
}