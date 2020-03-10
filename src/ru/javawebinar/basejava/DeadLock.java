package ru.javawebinar.basejava;

public class DeadLock {
    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": захватил монитор object1");
            synchronized (object1) {
                System.out.println(Thread.currentThread().getName() + ": ожидает захвата монитора object2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    System.out.println(Thread.currentThread().getName() + ": захватил монитор object2");
                }
            }
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": захватил монитор object2");
            synchronized (object2) {
                System.out.println(Thread.currentThread().getName() + ": ожидает захвата монитора object2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    System.out.println(Thread.currentThread().getName() + ": захватил монитор object1");
                }
            }
        }).start();
    }
}
