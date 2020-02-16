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
    protected void saveElement(Resume resume, int index) {
        listStorage.add(resume);
    }

    @Override
    protected void updateElement(Resume resume, int index) {
        listStorage.set(index, resume);
    }

    @Override
    protected Resume getElement(int index) {
        return listStorage.get(index);
    }

    @Override
    protected void deleteElement(int index) {
        listStorage.remove(index);
    }

    @Override
    protected int getKey(String uuid) {
        for (int i = 0; i < listStorage.size(); i++)
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        return -1;
    }

    protected boolean isExist(int index) {
        return index != -1;
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
