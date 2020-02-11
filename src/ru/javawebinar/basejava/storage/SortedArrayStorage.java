package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume resume, int index) {
        int newIndex = -index - 1;
        System.arraycopy(storage, newIndex, storage, newIndex + 1, countOfResumes - newIndex);
        storage[newIndex] = resume;
    }

    @Override
    protected void deleteElement(int index) {
        int indexMove = countOfResumes - index - 1;
        if (indexMove > 0) {
            System.arraycopy(storage, index + 1, storage, index, indexMove);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, countOfResumes, searchKey);
    }
}