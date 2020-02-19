package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    /* Компаратор для сравнения двух объектов Resume, необходимо для бинарного поиска
   Статический потому, что нет ссылок на внешний класс (SortedArrayStorage)
   Это внутренний статический класс (static nested class)

    private static class ResumeComparator implements Comparator<Resume> {

        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }
    */

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

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
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, countOfResumes, searchKey, RESUME_COMPARATOR);
    }
}