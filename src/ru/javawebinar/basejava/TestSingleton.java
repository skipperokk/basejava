package ru.javawebinar.basejava;

//Ленивый - когда инициализируется в геттере, не работает для многопоточности

public class TestSingleton {
    private static TestSingleton instance;

    public static TestSingleton getInstance() {
        if (instance == null) {
            instance = new TestSingleton();
        }
        return instance;
    }

    private TestSingleton() {
    }

    public static void main(String[] args) {
        System.out.println(getInstance().toString());
    }
}
