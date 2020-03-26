package ru.javawebinar.basejava;

import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final File storageDir;
    private final Storage storage;

    private static final File PROPS = new File(getHomedir(), "config\\resumes.properties");
    private static final Config INSTANCE = new Config();

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            Properties props = new Properties();
            props.load(is);
            storage = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
            storageDir = new File(props.getProperty("storage.dir"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public static Config get() {
        return INSTANCE;
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Storage getStorage() {
        return storage;
    }

    private static File getHomedir() {
        String prop = System.getProperty("homeDir");
        File homeDir = new File(prop==null ? "." : prop);
        if (!homeDir.isDirectory()){
            throw new IllegalStateException(homeDir + "is not directory");
        }
        return homeDir;
    }
}
