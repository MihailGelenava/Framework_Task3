package Utils;

import Driver.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Objects;

public class BrowserUtils {

    public static int getWindowsCount (){
        return Driver.getDriver().getWindowHandles().size();
    }
    public static void switchNextWindow (){
        String originalWindow = Driver.getDriver().getWindowHandle();
        for (String windowHandle : Driver.getDriver().getWindowHandles()) {
            if (!Objects.equals(originalWindow,windowHandle)){
                Driver.getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }
    public static void scrollToWebElement(WebElement e){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView();",e);
    }
    public static void closeTab(){
        Driver.getDriver().close();
    }

    public static void switchToDefaultWindow(){
        ArrayList<String> tabs = new ArrayList<String>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tabs.get(0));
    }

}
