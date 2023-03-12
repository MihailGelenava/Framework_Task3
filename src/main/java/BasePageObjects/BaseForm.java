package BasePageObjects;

import Driver.Driver;
import Utils.BrowserUtils;
import Utils.WaitsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class BaseForm {

    protected static final Logger logger = LogManager.getLogger(BaseForm.class.getName());

    protected final By uniqueFormLocator;
    protected final String formName;

    public BaseForm(By locator,String name){
        this.uniqueFormLocator = locator;
        this.formName = name;

        logger.info(name + " instance created with BaseForm constructor");
    }

    public String getFormName(){
        return formName;
    }

    public boolean isFormOpen(){

        logger.info(getFormName() + " opened");

        return WaitsUtil.waitForElementPresence(uniqueFormLocator);
    }

    protected void scrollIntoView(WebElement e){
        BrowserUtils.scrollToWebElement(e);
    }

    protected List<WebElement> getElements(By locator){
        return Driver.getDriver().findElements(locator);
    }

    public boolean isClosed(){
        return (Driver.getDriver().findElements(uniqueFormLocator).size() == 0);
    }

}
