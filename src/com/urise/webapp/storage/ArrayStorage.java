package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public boolean isPresent(String uuid) {
        for (int i = 0; i < countOfResumes; i++) {
            if (uuid.equals(storage[i].getUuid()))
                return true;
        }
        System.out.println("RESUME NOT EXIST");
        return false;
    }

    public void update(Resume r) throws IOException {
        // Так как мы предполагаем, что у нас уникальные uuid в базе, то имеет смысл после того, как
        // uuid в базе найден, выйти из цикла с помощью оператора break;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        if (isPresent(r.getUuid())) {
            for (int i = 0; i < countOfResumes; i++) {
                if (storage[i].getUuid().equals(r.getUuid())) {
                    System.out.print("Введи изменения: ");
                    storage[i].setUuid(reader.readLine());
                    break;
                }
            }
        }
    }

    public void save(Resume r) {
          if (countOfResumes != storage.length) {
            storage[countOfResumes++] = r;
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