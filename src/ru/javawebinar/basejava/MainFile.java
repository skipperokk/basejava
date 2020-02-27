package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        final String path = "./";
        List<String> res = new ArrayList<>();

        try {
            directory(path, res);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String s : res)
            System.out.println(s);
    }

    private static void directory(String path, List<String> res) {

        File dir = new File(path);
        File[] list;
        try {
            //Не проходит по скрытым каталогам
            if (!dir.isHidden()) {
                list = dir.listFiles();
                for (File file : Objects.requireNonNull(list)) {
                    if (file.isFile()) {
                        res.add(file.getName());
                    } else {
                        directory(file.getCanonicalPath(), res);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

