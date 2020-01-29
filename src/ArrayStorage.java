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
        for (int i = 0; i < countOfResumes; i++) {
            if (uuid.equals(storage[i].uuid))
                return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < countOfResumes; i++) {
            if (storage[i].uuid.equals(uuid)) {
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
    Resume[] getAll() {
        Resume[] resumes = new Resume[countOfResumes];
        for (int i = 0; i < countOfResumes; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        return countOfResumes;
    }
}