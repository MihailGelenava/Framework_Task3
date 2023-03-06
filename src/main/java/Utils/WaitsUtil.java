package Utils;

import Driver.BrowserConfiguration;
import Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitsUtil {
    private static final int EXPLICIT_WAIT_SECONDS =
            (int)BrowserConfiguration.getConfig().get("EXPLICIT_WAIT_SECONDS");

    private static final WebDriverWait waiter =
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_SECONDS));

    public static WebDriverWait getWaiter(){
        return waiter;
    }

    public static boolean waitForElementPresence(By locator){
        waiter.until(ExpectedConditions.presenceOfElementLocated(locator));
        return true;
    }

    public static boolean waitForElementToBeClickable(By locator){
        waiter.until(ExpectedConditions.elementToBeClickable(locator));
        return true;
    }

    public static boolean waitForElementVisible(By locator){
        waiter.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return true;
    }

}
