package BasePageObjects;

import Driver.Driver;
import Utils.WaitsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BaseElement {

    protected static final Logger logger = LogManager.getLogger(BaseElement.class.getName());

    protected final By locator;
    protected final String name;

    public BaseElement(By locator,String name){
        this.locator = locator;
        this.name = name;

        logger.info(name + " instance created with BaseElement constructor");
    }
    public WebElement getElement(){
        return Driver.getDriver().findElement(locator);
    }
    public void click(){
        WaitsUtil.waitForElementPresence(locator);
        WaitsUtil.waitForElementToBeClickable(locator);
        getElement().click();

        logger.info(getName() + " clicked");
    }
    public String getName(){
        return name;
    }

}
