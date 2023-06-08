package hwr.oop.todo.application;

import java.io.FileInputStream;
import java.util.Properties;

public class AppConfig {
    private static Properties properties;

    private AppConfig() {
        throw new IllegalStateException("Utility class");
    }

    static void loadProperties() {
        properties = new Properties();
        try(FileInputStream fileInputStream = new FileInputStream("src/AppConfig.properties")) {
            properties.load(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(key);
    }

}
