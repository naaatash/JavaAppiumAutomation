package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import java.time.Duration;

public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIos();
    }
    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {driver.rotate(ScreenOrientation.PORTRAIT);}
    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }
    protected void backgroundApp(int seconds){driver.runAppInBackground(Duration.ofSeconds(seconds));}
    private void skipWelcomePageForIos(){
        if (Platform.getInstance().isIos()){
            WelcomePageObject WelcomePage = new WelcomePageObject(driver);
            WelcomePage.clickSkip();
        }
    }
}
