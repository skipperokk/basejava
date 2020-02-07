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
        Arrays.fill(storage, 0, countOfResumes, null);
        countOfResumes = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Резюме " + resume.getUuid() + " не существует!");
        } else
            storage[index] = resume;
    }

    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) != -1) {
            System.out.println("Резюме " + resume.getUuid() + " уже существует!");
        } else if (countOfResumes >= storage.length) {
            System.out.println("Хранилище заполнено");
        } else {
            storage[countOfResumes++] = resume;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме " + uuid + " не существует!");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме " + uuid + " не существует!");
        } else {
            storage[index] = storage[countOfResumes - 1];
            storage[countOfResumes - 1] = null;
            countOfResumes--;
        }
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < countOfResumes; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
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