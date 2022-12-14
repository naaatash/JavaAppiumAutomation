package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "id:org.wikipedia:id/page_external_link";
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[contains(@text, 'Add to reading list')]";
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        OK_BUTTON = "id:android:id/button1";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }
    public AndroidArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
