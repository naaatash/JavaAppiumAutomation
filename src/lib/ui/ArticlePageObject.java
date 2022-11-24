package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String
    TITLE = "org.wikipedia:id/view_page_title_text",
    FOOTER_ELEMENT = "org.wikipedia:id/page_external_link",
    OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[contains(@text, 'Add to reading list')]",
    ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
    MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
    OK_BUTTON = "android:id/button1",
    CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";




    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 10);
    }

    public String getArticleTitle()
    {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.id(FOOTER_ELEMENT),
                "Cannot find footer element", 15
        );
    }

    public void addArticleToMyList(String nameOfFolder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON), "Cannot find element");
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON), "Cannot find element");
        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY), "Cannot find element");
        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT));
        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT), nameOfFolder, "Cannot find input or type", 5);
        this.waitForElementAndClick(
                By.id(OK_BUTTON), "Cannot find OK button");
    }
    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON), "Cannot find element");
    }
}
