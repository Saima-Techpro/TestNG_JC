package myApp.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    // This class is used to read the values from configuration.properties file

    private static Properties properties;

    // Create static block => static block is called before this class is initialised
    // We usually use static block to run pre-requisite

    static {
        String path = "configuration.properties";

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
            properties = new Properties();
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // This method returns VALUE of the key that we provide.
    // The key=value is stored in configuration.properties file

    public static String getProperty(String key){
        String value = properties.getProperty(key);
        return value;
    }


}
