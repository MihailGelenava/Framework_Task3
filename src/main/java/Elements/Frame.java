package Elements;

import BasePageObjects.BaseForm;
import Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Frame extends BaseForm {

    private final By frameTextLocator;

    public Frame(By locator, String name,By frameTextLocator) {
        super(locator, name);
        this.frameTextLocator = frameTextLocator;
    }

    public void switchToFrame(){
        logger.info("Browser switched to frame: " + getFormName());

        Driver.getDriver().switchTo().frame(getFrame());
    }

    public WebElement getFrame(){
        return Driver.getDriver().findElement(uniqueFormLocator);
    }

    public String searchTextInFrame(){
        return Driver.getDriver().findElement(frameTextLocator).getText();
    }

    public static void switchToDefaultFrame(){
        logger.info("Browser switched to frame: Default Content");

        Driver.getDriver().switchTo().defaultContent();
    }
}
