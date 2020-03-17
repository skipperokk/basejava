package ru.javawebinar.basejava.storage;

import java.io.*;
import java.util.Properties;

public class SqlStorageTest extends AbstractStorageTest {
    protected static final File PROPS = new File("config\\resumes.properties");
    private static Properties props = new Properties();
    private static String url;
    private static String user;
    private static String password;

   static {
        try (InputStream is=new FileInputStream(PROPS)) {
            props.load(is);
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SqlStorageTest() {
        super(new SqlStorage(url,user,password));
    }
}