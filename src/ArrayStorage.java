import sun.security.util.ArrayUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    int countOfResumes = 0;
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < countOfResumes; i++) {
            storage[i] = null;
        }
        countOfResumes = 0;
    }

    void save(Resume r) {
        storage[countOfResumes++] = r;
    }

    Resume get(String uuid) {
        Resume resume = new Resume();
        for (int i = 0; i < storage.length; i++) {
            resume.uuid = uuid;
        }
        return resume;
    }

    void delete(String uuid) {
        for (int i = 0; i < countOfResumes; i++) {
            if (storage[i].uuid.equals(uuid)) {
                for (int j = i; j < countOfResumes; j++) {
                    storage[j] = storage[j + 1];
                }
                countOfResumes--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] tmpStorage = new Resume[countOfResumes];
        for (int i = 0; i < countOfResumes; i++) {
            tmpStorage[i] = storage[i];
        }
        return tmpStorage;
    }

    int size() {
        return countOfResumes;
    }
}
