package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private static final String NAME_OF_FOLDER = "My list";
    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
    ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
    NavigationUI NavigationUI = NavigationUIFactory.get(driver);

    MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

    @Test
    public void testSaveTwoArticlesAndDeleteOneOfThem(){

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented");
        ArticlePageObject.waitForTitleElement();
        String articleTitle = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()){
        ArticlePageObject.addArticleToMyList(NAME_OF_FOLDER);
        }
        else
        {
            ArticlePageObject.addArticlesToMySaved();
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation");
        ArticlePageObject.addArticleToMyList(NAME_OF_FOLDER);
        ArticlePageObject.closeArticle();
        NavigationUI.clickMyLists();

        if(Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(NAME_OF_FOLDER);
        }
        MyListsPageObject.swipeArticleToDelete(articleTitle);
    }
}
