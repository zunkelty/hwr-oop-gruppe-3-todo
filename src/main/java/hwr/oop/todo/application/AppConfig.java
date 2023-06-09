package hwr.oop.todo.application;

import java.io.FileInputStream;
import java.util.Properties;

public class AppConfig {
    private static final Properties properties = new Properties();
    private static boolean isInitialized = false;

    private AppConfig(){
        throw new IllegalStateException("Utility class");
    }

    private static void loadProperties() {
        try(FileInputStream fileInputStream = new FileInputStream("AppConfig.properties")){
            properties.load(fileInputStream);
            isInitialized = true;
        } catch (Exception e) {
            throw new FailedLoadPropertiesException(e);
        }
    }

    public static String getProperty(String key) {
        if (!isInitialized) {
            loadProperties();
        }
        return properties.getProperty(key);
    }

}
