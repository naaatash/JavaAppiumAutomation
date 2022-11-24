package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String
    TITLE = "id:org.wikipedia:id/view_page_title_text",
    FOOTER_ELEMENT = "id:org.wikipedia:id/page_external_link",
    OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[contains(@text, 'Add to reading list')]",
    ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
    MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
    OK_BUTTON = "id:android:id/button1",
    CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";




    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 10);
    }

    public String getArticleTitle()
    {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find footer element", 15
        );
    }

    public void addArticleToMyList(String nameOfFolder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON, "Cannot find element");
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find element");
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY, "Cannot find element");
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT, "Cannot find and clear: " + MY_LIST_NAME_INPUT);
        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT, nameOfFolder, "Cannot find input or type", 5);
        this.waitForElementAndClick(
                OK_BUTTON, "Cannot find OK button");
    }
    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON, "Cannot find element");
    }
}
