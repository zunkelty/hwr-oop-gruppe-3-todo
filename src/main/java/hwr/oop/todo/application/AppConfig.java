package hwr.oop.todo.application;

import java.io.FileInputStream;
import java.util.Properties;

public class AppConfig {
    private static Properties properties;
    private static String filePath = "src/AppConfig.properties";
    public AppConfig(String filePath) {
        this.filePath = filePath;
    }

    public static void loadProperties() {
        properties = new Properties();
        try(FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties.load(fileInputStream);
        } catch (Exception e) {
            throw new RuntimeException("Could not load properties file");
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(key);
    }

}
