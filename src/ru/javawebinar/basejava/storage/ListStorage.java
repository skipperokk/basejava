package ru.javawebinar.basejava.storage;

/*Выделите общий класс AbstractStorage и реализуйте подкласс ListStorage.
Для этого вам необходимо вынести в AbstractStorage максимум кода, исключив тем самым его дублирование.

при поиске uuid не надо использовать методы, который сравнивают объекты Resume по equals,
 в следующих уроках добавим еще поля в Resume и в equals и данный вариант не подойдет
*/

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> listStorage = new ArrayList<>();

    @Override
    protected void saveElement(Resume resume, int key) {
        listStorage.add(resume);
    }

    @Override
    protected void updateElement(Resume resume, int key) {
        listStorage.set(key, resume);
    }

    @Override
    protected Resume getElement(int key) {
        return listStorage.get(key);
    }

    @Override
    protected void deleteElement(int key) {
        listStorage.remove(key);
    }

    @Override
    protected int getKey(String uuid) {
        for (Resume resume : listStorage) {
            if (uuid.compareTo(resume.getUuid()) == 0)
                return listStorage.indexOf(resume);
        }
        return -1;
    }

    protected boolean existKey(int key){
        return key != -1;
    }

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[listStorage.size()];
        listStorage.toArray(resumes);
        return resumes;
    }

    @Override
    public int size() {
        return listStorage.size();
    }
}
