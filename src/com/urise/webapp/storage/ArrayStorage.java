package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int countOfResumes = 0;

    public void clear() {
        for (int i = 0; i < countOfResumes; i++) {
            Arrays.fill(storage, null);
        }
        countOfResumes = 0;
    }

    public boolean isPresent(String uuid) {
        for (int i = 0; i < countOfResumes; i++) {
            if (uuid.equals(storage[i].getUuid()))
                return true;
        }
        System.out.println("uuid ЕЩЕ НЕ СУЩЕСТВУЕТ");
        return false;
    }

    public void update(Resume resume) {
        if (isPresent(resume.getUuid())) {
            for (int i = 0; i < countOfResumes; i++) {
                if (storage[i].getUuid().equals(resume.getUuid())) {
                    storage[i] = resume;
                }
            }
        }
    }

    public void save(Resume r) {
        if (countOfResumes != storage.length) {
            if (!isPresent(r.getUuid())) {
                System.out.println("Однако мы его сейчас сохраним");
                storage[countOfResumes++] = r;
            }
        } else
            System.out.println("Хранилище полностью заполнено");
    }

    public Resume get(String uuid) {
        if (isPresent(uuid)) {
            for (int i = 0; i < countOfResumes; i++) {
                if (uuid.equals(storage[i].getUuid()))
                    return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (isPresent(uuid)) {
            for (int i = 0; i < countOfResumes; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    storage[i] = storage[countOfResumes - 1];
                    storage[countOfResumes - 1] = null;
                    countOfResumes--;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    public Resume[] getAll() {
        return Arrays.copyOf(storage, countOfResumes);
    }

    public int size() {
        return countOfResumes;
    }
}