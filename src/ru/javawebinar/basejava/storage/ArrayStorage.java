package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume resume, int index) {
        storage[countOfResumes] = resume;
    }

    @Override
    protected void deleteElement(int index) {
        storage[index] = storage[countOfResumes - 1];
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < countOfResumes; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }


}