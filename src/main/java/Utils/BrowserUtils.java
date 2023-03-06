package Utils;

import Driver.Driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class BrowserUtils {

    private static String rememberedTabHandle;

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

    public static void rememberTabHandle(){
        rememberedTabHandle = Driver.getDriver().getWindowHandle();
    }

    public static void switchToDefaultWindow(){
        if (rememberedTabHandle != null){
            Driver.getDriver().switchTo().window(rememberedTabHandle);
        } else{
            throw new RuntimeException("You need to pick default window before open it");
        }
    }

    public static Alert switchToAlert(){
        return Driver.getDriver().switchTo().alert();
    }
}
