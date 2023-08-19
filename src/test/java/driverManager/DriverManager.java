package driverManager;

import dataProvider.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.Logging;


import java.util.concurrent.TimeUnit;

public class DriverManager {
  public static WebDriver driver;
  public static WebDriver getDriver()
  {
      if (driver==null)
          createDriver();
      return driver;
  }
  public static void createDriver()
    {
        switch (ConfigFileReader.browser().toUpperCase())
        {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
                Logging.info("Chrome Driver Created");
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                Logging.info("Firefox Driver Created");
                break;
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                driver=new EdgeDriver();
                Logging.info("Edge Driver Created");
                break;
            default:
                System.out.println("No matching browser found");
                Logging.info("No matching browser found");
                System.exit(0);
        }
        driver.manage().window().maximize();
        Logging.info("Browser maximized");

        driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigFileReader.implicitWait()),TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(ConfigFileReader.pageLoad()),TimeUnit.SECONDS);

    }

}
