package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iosSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT = "";
        SEARCH_INPUT = "";
        SEARCH_CANCEL_BUTTON = "";
        SEARCH_RESULT_LOCATOR = "";
        EMPTY_SEARCH_RESULT_LABEL = "";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "";
        SEARCH_RESULT_TITLE_TPL = "";
        SEARCH_RESULT_DESCRIPTION_TPL = "";
    }
    public iosSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
