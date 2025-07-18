package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tests.BaseTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BasePage {

    WebDriver driver;
    final int maxRetries = 3;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WebDriverWait webDriverWait;

    BaseTest baseTest = new BaseTest();
    public void click(WebElement element, String log) throws Exception {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        int retryCount = 0;
        while(retryCount<maxRetries){
            try {
                Actions actions = new Actions(driver);
                actions.moveToElement(element)
                        .build()
                        .perform();

                element.click();
                System.out.println("Clicked: "+log);
                break;
            } catch (Exception e) {
                retryCount++;
                System.out.println("Retry: "+retryCount + "to click on the "+log);
                if(retryCount==maxRetries){
                    baseTest.reportScreenshot(getCurrentDateTime()+"failedClick","Failed to click");
                    throw new Exception(getCurrentDateTime()+"Failed to click element: "+log);
                }
            }
        }


    }

    public void typeText(WebElement element, String text, String log) throws Exception {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int retryCount = 0;
        while(retryCount<maxRetries){
            try {
                webDriverWait.until(ExpectedConditions.visibilityOf(element));
                element.sendKeys(text);
                System.out.println(getCurrentDateTime()+" Typed: "+log);
                break;
            } catch (Exception e) {
                retryCount++;
                System.out.println("Retry: "+retryCount + "to type to "+log);
                if(retryCount==maxRetries){
                    baseTest.reportScreenshot(getCurrentDateTime()+"failedType","Failed to type");
                    throw new Exception(getCurrentDateTime()+"Failed to type to element: "+log);
                }
            }
        }
    }

    public void typeText(WebElement element, Keys key, String log) throws Exception {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int retryCount = 0;
        while(retryCount<maxRetries){
            try {
                webDriverWait.until(ExpectedConditions.visibilityOf(element));
                element.sendKeys(key);
                System.out.println(getCurrentDateTime()+" Typed: "+log);
                break;
            } catch (Exception e) {
                retryCount++;
                System.out.println("Retry: "+retryCount + "to type to "+log);
                if(retryCount==maxRetries){
                    baseTest.reportScreenshot(getCurrentDateTime()+"failedType","Failed to type");
                    throw new Exception(getCurrentDateTime()+"Failed to type to element: "+log);
                }
            }
        }
    }

    public void assertEQ(String actual, String expected, String log){
        Assert.assertEquals(actual,expected);
        System.out.println(getCurrentDateTime()+" Verified: "+log);
    }

    public String getCurrentDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
