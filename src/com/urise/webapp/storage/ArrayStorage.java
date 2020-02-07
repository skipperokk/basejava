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
        Arrays.fill(storage, 0, countOfResumes-1, null);
        countOfResumes = 0;
    }

    public int isPresent(String uuid) {
        for (int i = 0; i < countOfResumes; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        System.out.println(uuid + " ЕЩЕ НЕ СУЩЕСТВУЕТ");
        return -1;
    }

    public void update(Resume resume) {
        int index = isPresent(resume.getUuid());
        storage[index] = resume;
    }

    public void save(Resume resume) {
        if (countOfResumes != storage.length) {
            if (isPresent(resume.getUuid()) == -1) {
                System.out.println("Однако сейчас сохраним " + resume.getUuid());
                storage[countOfResumes++] = resume;
            }
        } else
            System.out.println("Хранилище полностью заполнено");
    }

    public Resume get(String uuid) {
        int index = isPresent(uuid);
        return storage[index];
    }

    public void delete(String uuid) {
        int index = isPresent(uuid);
        storage[index] = storage[countOfResumes - 1];
        storage[countOfResumes - 1] = null;
        countOfResumes--;
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