package hwr.oop.todo.application;

import java.io.FileInputStream;
import java.util.Properties;

public class AppConfig {
    private static Properties properties;

    private AppConfig(){}

    private static void loadProperties() {
        try(FileInputStream fileInputStream = new FileInputStream("AppConfig.properties")){
            AppConfig.properties = new Properties();
            AppConfig.properties.load(fileInputStream);
        } catch (Exception e) {
            AppConfig.properties = null;
            throw new FailedLoadPropertiesException(e);
        }
    }

    public static String getProperty(String key) {
        if (AppConfig.properties == null) {
            loadProperties();
        }
        return properties.getProperty(key);
    }

}
