package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class ConfigFileReader {
    private static Properties properties;
    private static final String ConfigFilePath = "src/main/resources/config.properties";

    static {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(ConfigFilePath));
            properties = new Properties();
            try {
                properties.load(bufferedReader);
                bufferedReader.close();
            } catch (IOException ioException) {
                System.out.println("Exception found" + ioException);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Exception found " + fileNotFoundException);
        }
    }

    public static String GetUrl() {
        String siteURL = properties.getProperty("url");
        if (siteURL != null)
            return siteURL;
        else throw new RuntimeException("url is not defined in config.propeties file");
    }

    public static String browser() {
        String getBrowser = properties.getProperty("browser");
        if (getBrowser != null)
            return getBrowser;
        else throw new RuntimeException("browser is not defined in config.propeties file");
    }

    public static String mode() {
        String headlessMode = properties.getProperty("mode");
        if (headlessMode != null)
            return headlessMode;
        else throw new RuntimeException("mode is not defined in config.propeties file");
    }

    public static String implicitWait() {
        String wait = properties.getProperty("implicitWait");
        if (wait != null)
            return wait;
        else throw new RuntimeException("wait is not defined in config.properties file");
    }

    public static String pageLoad() {
        String pageLoadTime = properties.getProperty("pageLoadTimeout");
        if (pageLoadTime != null)
            return pageLoadTime;
        else throw new RuntimeException("page load timeout is not defined in config.properties file");
    }

    public static String Testdata() {
        String testdataFilepath = properties.getProperty("testDataFilePath");
        if (testdataFilepath != null)
            return testdataFilepath;
        else throw new RuntimeException("test data file path is not defined in config.properties file");
    }

    public static String Username() throws Exception {
        String username = properties.getProperty("username");
        if (username != null)
            return username;
        else throw new Exception("Username not found");
    }

    public static String Password() throws Exception {
        String password = properties.getProperty("password");
        if (password != null)
            return password;
        else throw new Exception("Password not found");
    }
}