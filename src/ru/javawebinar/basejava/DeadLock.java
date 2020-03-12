package ru.javawebinar.basejava;

public class DeadLock {
    public static void main(String[] args) {
        Object object1 = new Object("Object_1");
        Object object2 = new Object("Object_2");
        makeThreadWithObject(object1, object2);
        makeThreadWithObject(object2, object1);
    }

    private static void makeThreadWithObject(Object object1, Object object2) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": захватил монитор " + object1);
            synchronized (object1) {
                System.out.println(Thread.currentThread().getName() + ": ожидает захвата монитора" + object2);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    System.out.println(Thread.currentThread().getName() + ": захватил монитор" + object2);
                }
            }
        }).start();
    }
}

class Object {
    private final String name;

    public Object(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
