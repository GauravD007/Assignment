package scripts;
import dataProvider.ConfigFileReader;
import dataProvider.ReadWriteExcelData;
import driverManager.DriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LogInPage;
import reports.ExtentReport;
import utils.Logging;
import java.io.IOException;


public class TestCase {
    ExtentReport extentReport;
    HomePage homePage;
    LogInPage logInPage;
    WebDriver driver;
    SoftAssert softAssert;
    ReadWriteExcelData excelData;

    @BeforeSuite
    public void beforeSuitSetup() throws IOException {
        extentReport = new ExtentReport();
        driver = DriverManager.getDriver();
        driver.get(ConfigFileReader.GetUrl());
        logInPage = new LogInPage(driver);
        homePage = new HomePage(driver);
        softAssert = new SoftAssert();
        excelData = new ReadWriteExcelData();
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }

    @AfterSuite
    public void afterSuit() {

        softAssert.assertAll();
        extentReport.flush();
        driver.quit();
    }

    @BeforeMethod()
    public void beforemethod() {
    }

    @AfterMethod()
    public void afterMethod() {
    }

    @Test(description = "Test Case 001 : Validate that user should login with valid credentials.", priority = 1)
    public void TestCase001() throws IOException {
        XSSFSheet sheet = excelData.Sheet("Sheet5");
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            extentReport.createTest("Test Case 001 : Validate that user should login with valid credentials.");
            extentReport.info("User is on the Login page");

            logInPage.UsernameTxtBox.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
            extentReport.info("User enter username.");
            Logging.info("User entered username");

            logInPage.passwordTxtBox.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
            extentReport.info("User enter password");
            Logging.info("User entered password");

            logInPage.LoginBtn.click();
            extentReport.info("User click on login button");
            Logging.info("User click on login button");

            if (homePage.WelcomeMsg.isDisplayed()) {
                Logging.info("Test Case Pass : User successfully Login to the application");
                extentReport.addScreenshot(driver);
                extentReport.pass("Test Case Pass : User successfully Login to the application");
            } else {
                Logging.info("Test Case Failed : User is unable to Login with valid credentials");
                extentReport.addScreenshot(driver);
                extentReport.fail("Test Case Failed : User is unable to Login with valid credentials");

            }
            Logging.endTestCase();
        }
        homePage.Dropdown.click();
        homePage.Logout.click();
    }


    @Test(description = "Test Case 002 : Validate that user should not login with invalid credentials.", priority = 2)
    public void TestCase002() throws IOException, InterruptedException {
        XSSFSheet sheet = excelData.Sheet("Sheet4");
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            extentReport.createTest("Test Case 002 : Validate that user should not login with invalid credentials.");
            extentReport.info("User is on the Login page");
            logInPage.UsernameTxtBox.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
            extentReport.info("User entered invalid username");
            Logging.info("User entered invalid username");

            logInPage.passwordTxtBox.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
            extentReport.info("User entered invalid password");
            Logging.info("User entered invalid password");

            logInPage.LoginBtn.click();
            extentReport.info("User click on login button");
            Logging.info("User click on login button");
            Thread.sleep(2000);
            if (logInPage.LoginMsg.isDisplayed()) {
                extentReport.info("User is unable to Login with invalid credentials");
                Logging.info("User is unable to Login with invalid credentials");
                extentReport.addScreenshot(driver);
                extentReport.pass("Test Case Passed ");
            } else {
                extentReport.info("User is able to Login with invalid credentials");
                Logging.info("User is able to Login with invalid credentials");
                extentReport.addScreenshot(driver);
                extentReport.fail("Test Case Failed ");
            }
        }
    }

    @Test(description = "Test Case 003 : Validate that appropriate error messages are displayed for invalid login attempts.", priority = 3)
    public void TestCase003() {
        try {
            XSSFSheet sheet = excelData.Sheet("Sheet3");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                extentReport.createTest(" Test Case 003 : Validate that appropriate error messages are displayed for invalid login attempts.");
                extentReport.info("User is on the Login page");
                logInPage.UsernameTxtBox.clear();
                logInPage.passwordTxtBox.clear();
                logInPage.UsernameTxtBox.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
                extentReport.info("User entered invalid username");
                Logging.info("User entered invalid username");

                logInPage.passwordTxtBox.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
                extentReport.info("User entered invalid password");
                Logging.info("User entered invalid password");

                logInPage.LoginBtn.click();
                extentReport.info("User click on Login button");
                Logging.info("User click on Login button");

                Thread.sleep(2000);

                String expectedMessage = "Invalid login credentials";
                String actualMessage = driver.findElement(By.xpath("//p[text()='Invalid login credentials']")).getText();
                if (actualMessage.equals(expectedMessage)) {
                    extentReport.info("\"Invalid login credentials\" error message is displayed when user enter invalid credentials");
                    extentReport.addScreenshot(driver);
                    extentReport.pass(" Test Case Passed");
                } else {
                    extentReport.info("\"Invalid login credentials\" error message is not displayed on the page");
                    extentReport.addScreenshot(driver);
                    extentReport.pass(" Test Case Failed");
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            XSSFSheet sheet1 = excelData.Sheet("Sheet2");
            for (int i = 1; i <= sheet1.getLastRowNum(); i++) {
                extentReport.info("User is on the Login page");
                logInPage.UsernameTxtBox.clear();
                logInPage.passwordTxtBox.clear();

                logInPage.UsernameTxtBox.sendKeys(sheet1.getRow(i).getCell(0).getStringCellValue());
                extentReport.info("User entered invalid username");
                Logging.info("User entered invalid username");

                logInPage.LoginBtn.click();
                extentReport.info("User click on Login button");
                Logging.info("User click on Login button");

                Thread.sleep(2000);

                String actualMessage = driver.findElement(By.xpath("//p[text()='Please specify both a username and password']")).getText();
                String expectedMessage = "Please specify both a username and password";

                if (actualMessage.equals(expectedMessage)) {
                    extentReport.info("\"Please specify both a username and password\" error message displayed when user click on Login button without entering password");
                    extentReport.addScreenshot(driver);
                    extentReport.pass(" Test Case Passed");
                } else {
                    extentReport.info("error message is not displayed on the page");
                    extentReport.addScreenshot(driver);
                    extentReport.fail("Test Case Failed");
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            XSSFSheet sheet2 = excelData.Sheet("Sheet1");
            for (int i = 1; i <= sheet2.getLastRowNum(); i++) {
                extentReport.info("User is on the Login page");
                logInPage.UsernameTxtBox.clear();
                logInPage.passwordTxtBox.clear();

                logInPage.passwordTxtBox.sendKeys(sheet2.getRow(i).getCell(0).getStringCellValue());
                extentReport.info("User entered invalid password");
                Logging.info("User entered invalid password");

                logInPage.LoginBtn.click();
                extentReport.info("User click on Login button");
                Logging.info("User click on Login button");

                Thread.sleep(2000);

                String actualMessage = driver.findElement(By.xpath("//p[text()='Please specify both a username and password']")).getText();
                String expectedMessage = "Please specify both a username and password";

                if (actualMessage.equals(expectedMessage)) {
                    extentReport.info("\"Please specify both a username and password\" error message displayed when user click on Login button without entering username");
                    extentReport.addScreenshot(driver);
                    extentReport.pass(" Test Case Passed");
                } else {
                    extentReport.info("error message is not displayed on the page");
                    extentReport.addScreenshot(driver);
                    extentReport.fail("Test Case Failed");
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test(description = "Test Case 004 : Validate that the user is redirected to the dashboard screen after successful login", priority = 4)
    public void TestCase004() {
        try {
            extentReport.createTest(" Test Case 004 : Validate that the user is redirected to the dashboard screen after successful login");
            extentReport.info("User is on the Login page");
            XSSFSheet sheet = excelData.Sheet("Sheet5");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                logInPage.UsernameTxtBox.clear();
                logInPage.passwordTxtBox.clear();

                logInPage.UsernameTxtBox.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
                extentReport.info("User entered valid username");
                Logging.info("User entered valid username");

                logInPage.passwordTxtBox.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
                extentReport.info("User entered valid password");
                Logging.info("User entered valid password");

                logInPage.LoginBtn.click();
                extentReport.info("User click on Login button");
                Logging.info("User click on Login button");

                Thread.sleep(2000);

                String actualURL = driver.getCurrentUrl();
                String expectedURL = "https://beta.deepthought.education/dashboard";

                if (actualURL.equals(expectedURL)) {
                    extentReport.info("User is on the dashbord after login successfully");
                    extentReport.addScreenshot(driver);
                    extentReport.pass("Test Case Passed");
                } else {
                    extentReport.info("User is not on the dashbord after login successfully");
                    extentReport.addScreenshot(driver);
                    extentReport.fail("Test Case Failed");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}