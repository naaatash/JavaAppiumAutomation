package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
            SEARCH_INIT = "xpath://*[contains(@text, 'Search Wikipedia')]";
            SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]";
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
            SEARCH_RESULT_LOCATOR = "id:org.wikipedia:id/view_list_card_list";
            EMPTY_SEARCH_RESULT_LABEL = "xpath://*[@text='No results found']";
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container'] //*[contains(@text,'{SUBSTRING}')]";
            SEARCH_RESULT_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and (@text='{SUBSTRING}')]";
            SEARCH_RESULT_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description' and (@text='{SUBSTRING}')]";
    }

    public AndroidSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
