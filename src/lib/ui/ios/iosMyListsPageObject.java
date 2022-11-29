package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iosMyListsPageObject extends MyListsPageObject {
    public iosMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
    static {
        ELEMENT_BY_NAME_TPL = "xpath://XCUIElementTypeLink[contains(@name='{TITLE}')]";
    }
}
