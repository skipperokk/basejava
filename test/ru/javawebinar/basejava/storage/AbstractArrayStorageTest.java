package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private final String UUID_1 = "uuid1";
    private final String UUID_2 = "uuid2";
    private final String UUID_3 = "uuid3";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume resume = new Resume("uuid1");
        storage.update(resume);
        assertSame(storage.get(UUID_1), resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void save() {
        Resume resume = new Resume("uuid4");
        storage.save(resume);
        assertEquals(resume, storage.get(resume.getUuid()));
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void storageOverflow() {
        try {
            for (int i = 4; i < AbstractArrayStorage.STORAGE_LIMIT+1; i++) {
                storage.save(new Resume("uuid_"+i));
            }
        } catch (StorageException e) {
            fail("fail");
        }
        storage.save(new Resume("uuid_10000"));
    }

    /*заполняем массив, но не вызываем у него переполнение
    если исключение вылетит раньше, чем массив будет заполнен, то тест должен провалиться (см. Assert.fail())
    если исключение вылетает, когда пытаемся добавить в полностью заполненный массив еще одно резюме - тест пройден */

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void get() {
        Resume resume = new Resume("uuid4");
        storage.save(resume);
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        assertEquals(3, resumes.length);
        assertEquals(UUID_1, resumes[0].getUuid());
        assertEquals(UUID_2, resumes[1].getUuid());
        assertEquals(UUID_3, resumes[2].getUuid());
    }
}
