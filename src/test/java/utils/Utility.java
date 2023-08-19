package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Utility {
    public static String captureScreenshot(WebDriver driver) throws IOException
    {
        TakesScreenshot ts = ((TakesScreenshot)driver);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        File destFile = new File("ScreenShots\\"+System.currentTimeMillis() +".png");
        FileUtils.copyFile(sourceFile,destFile);
        return destFile.getAbsolutePath();

    }
}