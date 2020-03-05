package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".gitignore";
        File file = new File(".\\.gitignore");
        try {
            System.out.println(file.getCanonicalFile());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File("C:\\Users\\Admin\\Desktop\\basejava");
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (
                FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //HomeWork Les.8 part 2

        final String path = "./src/";
        File mainDir = new File(path);
        printDirectoryDeeply(mainDir, " ");
    }

    private static void printDirectoryDeeply(File mainDir, String indent) {
        File[] list;
        try {
            list = mainDir.listFiles();
            for (File file : Objects.requireNonNull(list)) {
                if (file.isFile()) {
                    System.out.println(indent + "File: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(indent + "Directory: " + file.getName());
                    printDirectoryDeeply(mainDir.getCanonicalFile(),indent + " ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

