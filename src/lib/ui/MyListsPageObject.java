package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObject extends MainPageObject{
    public static final String
    ELEMENT_BY_NAME_TPL = "xpath://*[contains(@text, '{ELEMENT_NAME}')]";
    private static String getElementXpathByName(String nameOfFolder)
    {
        return ELEMENT_BY_NAME_TPL.replace("{ELEMENT_NAME}", nameOfFolder);
    }
    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
    public void openFolderByName(String nameOfFolder)
    {
        this.waitForElementAndClick(getElementXpathByName(nameOfFolder), "Cannot find folder by name " + nameOfFolder);
    }
    public void swipeArticleToDelete(String articleTitle)
    {
        this.waitForArticleApearByTitle(articleTitle);
        this.swipeElementToLeft(getElementXpathByName(articleTitle), "Cannot find and swipe article");
        this.waitForArticleDisapearByTitle(articleTitle);

    }

    public void waitForArticleDisapearByTitle(String articleTitle)
    {
        String articleTitle_xpath = getElementXpathByName(articleTitle);
        this.waitForElementAbsence(articleTitle_xpath, "Article absence test failed", 5);
    }
    public void waitForArticleApearByTitle(String articleTitle)
    {
        String articleTitle_xpath = getElementXpathByName(articleTitle);
        this.waitForElementPresent(articleTitle_xpath, "Cannot find article", 5);
    }
}
