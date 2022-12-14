package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iosSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains, (@name='{SUBSTRING}')]";
        EMPTY_SEARCH_RESULT_LABEL = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RESULT_LOCATOR = "xpath://XCUIElementTypeLink";
    }
    public iosSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
