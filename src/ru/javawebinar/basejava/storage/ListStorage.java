package ru.javawebinar.basejava.storage;

/*Выделите общий класс AbstractStorage и реализуйте подкласс ListStorage.
Для этого вам необходимо вынести в AbstractStorage максимум кода, исключив тем самым его дублирование.

при поиске uuid не надо использовать методы, который сравнивают объекты Resume по equals,
 в следующих уроках добавим еще поля в Resume и в equals и данный вариант не подойдет
*/

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    public ListStorage() {
        super(new ArrayList<>());
    }

    @Override
    protected int getIndexList(String uuid) {
        for (Resume r : listStorage) {
            if (uuid.compareTo(r.getUuid()) == 0)
                return listStorage.indexOf(r);
        }
        return -1;
    }
}
