package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public static WebDriver driver;

    public HomePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//h5[text()='Welcome to DeepThought']")
    public WebElement WelcomeMsg;


    @FindBy(xpath = "//a[@id='dropdownMenuButton']")
    public WebElement Dropdown;

    @FindBy(xpath = "//span[text()=' Logout']")
    public WebElement Logout;
}