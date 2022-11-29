package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {
    public AndroidMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
    static {
        ELEMENT_BY_NAME_TPL = "xpath://*[contains(@text, '{ELEMENT_NAME}')]";
    }
}
