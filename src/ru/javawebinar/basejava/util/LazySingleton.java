package ru.javawebinar.basejava.util;

public class LazySingleton {
    int i;
    volatile private static LazySingleton INSTANCE;

    double sin = Math.sin(13.0);

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingleton.class) {
                if (INSTANCE == null) {
                    int i = 13;
                    INSTANCE = new LazySingleton();
                }
            }
        }
        return INSTANCE;
    }
}
