package Elements;

import BasePageObjects.BaseElement;
import Driver.Driver;
import org.openqa.selenium.By;

public class Frame extends BaseElement {

    public Frame(By locator, String name) {
        super(locator, name);
    }

    public void switchToFrame(){
        logger.info("Browser switched to frame: " + getName());

        Driver.getDriver().switchTo().frame(getElement());
    }

    public String searchTextInFrame(){
        return Driver.getDriver().findElement(By.tagName("body")).getText();
    }
    public static void switchToDefaultFrame(){
        logger.info("Browser switched to frame: Default Content");

        Driver.getDriver().switchTo().defaultContent();
    }
}
