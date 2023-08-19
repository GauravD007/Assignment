package reports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import utils.Logging;
import java.io.IOException;
public class ExtentReport {
    ExtentReports extentReports;
    ExtentSparkReporter spark;

    public static ExtentTest extentTest;
    public ExtentReport()
    {
        extentReports=new ExtentReports();
        spark = new ExtentSparkReporter("C:\\Users\\gaura\\IdeaProjects\\Assignment1\\ExtentReports\\testCaseResults.html");
        spark.config().setDocumentTitle(" QAWingify Test Reports ");
        extentReports.attachReporter(spark);

    }
    public void pass(String message)
    {
        extentTest.log(Status.PASS,message);
    }
    public void fail(String message)
    {
        extentTest.log(Status.FAIL,message);
    }
    public void info(String message)
    {
        extentTest.log(Status.INFO,message);
    }
    public void flush()
    {
        extentReports.flush();
    }
    public ExtentTest createTest(String testcaseName)
    {
        extentTest = extentReports.createTest(testcaseName);
        Logging.startTestCase(testcaseName);
        return extentTest;
    }
    public void addScreenshot(WebDriver driver) throws IOException {
        extentTest.addScreenCaptureFromPath(utils.Utility.captureScreenshot(driver));
    }
}