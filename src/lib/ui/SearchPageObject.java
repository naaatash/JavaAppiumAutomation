package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import java.util.List;

public class SearchPageObject extends MainPageObject{

    private static final String
        SEARCH_INIT = "xpath://*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]",
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_LOCATOR = "id:org.wikipedia:id/view_list_card_list",
        EMPTY_SEARCH_RESULT_LABEL = "xpath://*[@text='No results found']",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container'] //*[contains(@text,'{SUBSTRING}')]",
        SEARCH_RESULT_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and (@text='{SUBSTRING}')]",
        SEARCH_RESULT_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description' and (@text='{SUBSTRING}')]";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getSearchResultElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getSearchResultTitle(String title)
    {
        return SEARCH_RESULT_TITLE_TPL.replace("{SUBSTRING}", title);
    }
    private static String getSearchResultDescription(String description)
    {
        return SEARCH_RESULT_DESCRIPTION_TPL.replace("{SUBSTRING}", description);
    }
    /* TEMPLATES METHODS */

    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String searchResultTitleXpath = getSearchResultTitle(title);
        String searchResultDescriptionXpath = getSearchResultDescription(description);
        this.waitForElementPresent(searchResultTitleXpath, "Cannot find search result by title. Check xpath: " + searchResultTitleXpath, 5);
        this.waitForElementPresent(searchResultDescriptionXpath, "Cannot find result by description. Check xpath: " + searchResultDescriptionXpath, 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find Search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementAbsence(SEARCH_CANCEL_BUTTON, "Cannot find Search cancel button", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click Search cancel button", 5);
    }

    public void initSearchInput()
    {
        this.waitForElementPresent(SEARCH_INIT, "Cannot find search_init_elenemt");
        this.waitForElementAndClick(SEARCH_INIT, "Cannot click search_init_elenemt");
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search_input", 5);
    }

    public void waitForSearchResult(String element_text)
    {
        this.waitForElementPresent(getSearchResultElement(element_text), "Cannot find search result with substring " + element_text);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getSearchResultElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }
    private int getAmountOfElements(String locator){
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(SEARCH_RESULT_LOCATOR, "Cannot find found articles");
        return getAmountOfElements(SEARCH_RESULT_LOCATOR);
    }
    public void getEmptySearchResultLabel()
    {
        this.waitForElementPresent(EMPTY_SEARCH_RESULT_LABEL, "Cannot find empty result label", 5);
    }
    public void assertThereIsNoSearchResult(String search_text)
    {
        this.waitForElementAbsence(getSearchResultElement(search_text), "Seems like Search results is here " + SEARCH_RESULT_LOCATOR, 5);
    }
}
