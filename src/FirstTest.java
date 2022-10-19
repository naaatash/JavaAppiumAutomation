import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/user/Projects/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void searchJava()
    {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"));
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java");
        waitForElementPresent(By.xpath("//*[contains(@text, 'Object-oriented')]"));
    }

    @Test
    public void cancelSearch(){
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"));
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java");
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"));
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"));
        waitForElementAbsence(By.id("org.wikipedia:id/search_close_btn"), "lol", 5);
    }

    @Test
    public void compareArticleTitle(){
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"));
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java");
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Object-oriented')]"));
        WebElement titleElement = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"));
        String articleTitle = titleElement.getText();

        Assert.assertEquals(
                "Java (programming language)",
                articleTitle);

    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + '\n');
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    private WebElement waitForElementPresent(By by, String error_message){
        return waitForElementPresent(by, error_message, 5);
    }
    private WebElement waitForElementPresent(By by){
        return waitForElementPresent(by, "Can't find element by: " + "\n" + by, 5);
    }

    private WebElement waitForElementAndClick(By by){
        WebElement element = waitForElementPresent(by);
        element.click();
        return element;
    }
    private WebElement waitForElementAndSendKeys(By by, String value){
        WebElement element = waitForElementPresent(by);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementAbsence(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by){
        WebElement element = waitForElementPresent(by);
        element.clear();
        return element;
    }
}
