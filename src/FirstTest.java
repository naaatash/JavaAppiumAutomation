import io.appium.java_client.TouchAction;
import lib.CoreTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FirstTest extends CoreTestCase{

    @Test
    public void testSearchJava()
    {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' placeholder");
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java");
        waitForElementPresent(By.xpath("//*[contains(@text, 'Object-oriented')]"));
    }

    @Test
    public void testCancelSearch(){
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' placeholder");
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Test");

        waitForElementPresent(By.id("org.wikipedia:id/search_results_list"), "There is no any search results");
        List results = driver.findElements(By.className("android.widget.LinearLayout"));
        Assert.assertTrue("There is not as much results as expected",results.size() >= 2);

        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Cannot find 'search_close_btn'");
        waitForElementAbsence(By.id("org.wikipedia:id/search_results_list"),"Results is still here", 5);
    }

    @Test
    public void testCompareArticleTitle(){
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' placeholder");
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java");
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Object-oriented')]"), "Cannot find 'Object-oriented' text");
        WebElement titleElement = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"));
        String articleTitle = titleElement.getText();

        Assert.assertEquals(
                "Java (programming language)",
                articleTitle);

    }

    @Test
    public void testAssertElementHasExpectedText(){
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Cannot find 'search_container'");
        assertElementHasText(By.id("org.wikipedia:id/search_src_text"), "Search…", "There is no such text here. Expected text: " + "\n" + "Search…");
    }

    @Test
    public void testEveryResultHasTheWord(){
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find 'Search wikipedia'");
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java");

        List results = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        results.forEach(element -> assertElementHasText(By.id("org.wikipedia:id/page_list_item_title"), "Java", "There is no expected value"));
    }

    @Test
    public void testSwipeArticle(){
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find 'Search wikipedia'");
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Appium");
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Automation')]"), "Cannot find 'Automation'");
        swipeUpToFindElement(By.id("org.wikipedia:id/page_external_link"), "cant find element", 10);
    }

    @Test
    public void testSaveAndDeleteArticle(){
        waitForElementAndClick
                (By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find 'Search wikipedia'");
        waitForElementAndSendKeys
                (By.xpath("//*[contains(@text, 'Search…')]"), "Java");
        waitForElementAndClick
                (By.xpath("//*[contains(@text, 'Object-oriented')]"), "Cannot find element");

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"), "Cannot find element");
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Add to reading list')]"), "Cannot find element");
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"), "Cannot find element");
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"));
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"), "My list");
        waitForElementAndClick(
                By.id("android:id/button1"), "Cannot find OK button");
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "Cannot find element");
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView"), "Cannot find element");

        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"), "Cannot find element");

        WebElement item = waitForElementPresent(
                        By.id("org.wikipedia:id/page_list_item_title"),
                        "Cannot find item in list");

        String itemName = item.getText();

        Assert.assertTrue(
                itemName.contains("Java (programming language)"));

        swipeElementToLeft(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot swipe element");
    }

    @Test
    public void testAmountOfNoEmptySearch(){
        waitForElementAndClick
                (By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find 'Search wikipedia'");
        String search_line = "Linkin Park discography";
        waitForElementAndSendKeys
                (By.xpath("//*[contains(@text, 'Search…')]"), search_line);
        String search_result_locator = "org.wikipedia:id/view_list_card_list";
        waitForElementPresent(By.id(search_result_locator), "cannot find: " + search_result_locator);

        int amount_of_search_results = getAmountOfElements(By.id(search_result_locator));
        Assert.assertTrue("We found too few results",amount_of_search_results > 0 );
    }
    private int getAmountOfElements(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }

    @Test
    public void testAmountOfEmptySearch(){
        waitForElementAndClick
                (By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find 'Search wikipedia'");
        String search_line = "lnubkghfdfvbgyun";

        waitForElementAndSendKeys
                (By.xpath("//*[contains(@text, 'Search…')]"), search_line);
        String search_result_locator = "org.wikipedia:id/page_list_item_title";
        String empty_result_label = "//*[@text='No results found']";

        waitForElementPresent(By.xpath(empty_result_label), "found some results o_o", 10);

        assertElementNotPresent(By.id(search_result_locator), "Cannot find empty result label");

    }

    private void assertElementNotPresent(By by, String error_message){
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0){
            String default_message = "An element " + by.toString() + " suppoused to be not present";
            throw new  AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetText(By by, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                timeOutInSeconds
        );
        return element.getText();
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input");

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Object-oriented')]"),
                "Cannot find 'Object-oriented' article", 15);

        String title_before_rotation = waitForElementAndGetText(
                By.id("org.wikipedia:id/view_page_title_text"),
                "cannot find title text",
                15);
        System.out.println(title_before_rotation);
        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetText(By.id("org.wikipedia:id/view_page_title_text"), "cannot find title text", 5);

        Assert.assertEquals("Article title have been changed after screen rotation", title_before_rotation, title_after_rotation);
    }
    @Test
    public void testCheckArticleInBackground()
    {
        waitForElementAndClick
                (By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find 'Search wikipedia'");
        waitForElementAndSendKeys
                (By.xpath("//*[contains(@text, 'Search…')]"), "Java");
        waitForElementPresent
                (By.xpath("//*[contains(@text, 'Object-oriented')]"));

        driver.runAppInBackground(2);

        waitForElementPresent
                (By.xpath("//*[contains(@text, 'Object-oriented')]"), "Cannot find article after returning from background");
    }

    @Test
    public void testSaveTwoArticlesAndDeleteOneOfThem(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search wikipedia'"
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java"
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Object-oriented')]"),
                "Cannot find 'Object-oriented' text in articles"
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'More options' button");
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Add to reading list')]"),
                "Cannot find 'Add to reading list'");
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find onboarding_button");
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"));
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "My list");

        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find OK button");

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "Cannot find 'close' button");
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search wikipedia'"
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Appium"
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Automation')]"),
                "Cannot find 'Automation' text in articles"
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'More options' button");
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Add to reading list')]"),
                "Cannot find 'Add to reading list'");
        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Cannot find 'My reading list'");

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "Cannot find 'close' button");
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView"), "Cannot find 'My lists'");

        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"), "Cannot open first list in 'My lists screen'");


        WebElement appiumArticle = waitForElementPresent(
                By.xpath("//*[@text='Appium']"),
                "Cannot find item in list");

        WebElement javaArticle = waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find item in list");

        String javaArticleTitle = javaArticle.getText();

        swipeElementToLeft(
                By.xpath("//*[@text='Appium']"),
                "Cannot swipe element");

        waitForElementAndClick(By.id("org.wikipedia:id/page_list_item_container"), "cant find and open article in list");

        WebElement openedArticle = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"));
        String openedArticleTitle = openedArticle.getText();
        Assert.assertEquals(javaArticleTitle, openedArticleTitle);

    }

    @Test
    public void testAssertArticleHasTitle(){
        waitForElementAndClick(By.id("org.wikipedia:id/view_featured_article_card_image"), "Cannot open Featured article");
        WebElement element = driver.findElement(By.id("org.wikipedia:id/view_page_title_text"));
        Assert.assertNotNull(element);
    }

    @Test
    public void testWithLandscapeOrientation(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }
    @Test
    public void testCheckPortraitOrientation(){
        String expectedOrientation = "PORTRAIT";
        String actualOrientation = driver.getOrientation().toString();
        Assert.assertEquals(expectedOrientation, actualOrientation);
    }
    @Test
    public void secondTestWithLandscapeOrientation(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
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

    private WebElement waitForElementAndClick(By by, String error_message){
        WebElement element = waitForElementPresent(by, error_message);
        element.click();
        return element;
    }
    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
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

    private void assertElementHasText(By by, String expectedValue, String error_message){
        WebElement element = waitForElementPresent(by, error_message);
        String actualValue = element.getText();
        Assert.assertEquals(error_message,expectedValue,
                actualValue);
    }

    protected void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int)(size.height * 0.8);
        int end_y = (int)(size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }
    protected void swipeUpQuick(){
        swipeUp(200);
    }
    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){
        int already_swiped = 0;

        while (driver.findElements(by).size() == 0){
            if (already_swiped > max_swipes){
                waitForElementPresent(by, error_message);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(
                by,
                error_message);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();


    }
}
