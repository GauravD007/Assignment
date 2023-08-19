package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
    public static WebDriver driver;

    public LogInPage (WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@id='username']")
    public WebElement UsernameTxtBox;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordTxtBox;

    @FindBy(xpath = "//input[@id='remember']")
    public WebElement rememberChkBox;

    @FindBy(xpath = "//a[text()='Forgot Password?']")
    public WebElement ForgotBtn;

    @FindBy(xpath = "//button[text()='Login']")
    public WebElement LoginBtn;

    @FindBy(xpath = "//a[text()='Register']")
    public WebElement RegisterBtn;

    @FindBy(xpath = "//img[@alt='Google sign-in']")
    public WebElement LoginWithGooglebtn;

    @FindBy(xpath = "//span[@class=\"logo-deep\"]")
    public WebElement AppLogo;

    @FindBy(xpath = "//strong[text()='Login Unsuccessful']")
    public WebElement LoginMsg;
//
//    @FindBy(xpath = "//img[@src=\"img/facebook.png\"]")
//    public WebElement facebookLogo;
//
//    @FindBy(xpath = "//img[@src=\"img/linkedin.png\"]")
//    public WebElement linkedinLogo;
//
//    @FindBy(xpath = "//span[text()='Sign in to Twitter']")
//    public WebElement twitterLoginPage;
//
//    @FindBy(xpath = "//div[text()='Log in to Facebook']")
//    public WebElement facebookLoginPage;
//
//    @FindBy(xpath = "//h1[text()='Sign in']")
//    public WebElement linkedLoginPage;
}
