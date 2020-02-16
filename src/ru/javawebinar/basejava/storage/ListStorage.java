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
    protected void updateElement(Resume resume, Object index) {
        listStorage.set((Integer) index, resume);
    }

    @Override
    protected void saveElement(Resume resume, Object key) {
        listStorage.add(resume);
    }

    @Override
    protected Resume getElement(Object index) {
        return listStorage.get((Integer) index);
    }

    @Override
    protected void deleteElement(Object index) {
        listStorage.remove(((Integer) index).intValue());
    }

    @Override
    protected Object getKey(String uuid) {
        for (int i = 0; i < listStorage.size(); i++)
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        return null;
    }

    protected boolean isExist(Object index) {
        return index != null;
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
