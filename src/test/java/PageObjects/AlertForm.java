package PageObjects;

import BasePageObjects.BaseForm;
import Elements.Button;
import Elements.Text;
import Utils.WaitsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;

public class AlertForm extends BaseForm {
    private static final By uniqueLocator = By.id("javascriptAlertsWrapper");
    private static final String name = "Alert form";

    private static final By alertsWrapper = By.id("javascriptAlertsWrapper");
    private static final By alertButtonLocator = By.id("alertButton");
    private static final By confirmButtonLocator = By.id("confirmButton");
    private static final By confirmResultLocator = By.id("confirmResult");
    private static final By promtButtonLocator = By.id("promtButton");
    private static final By promtResultLocator = By.id("promptResult");

    public final Button alertButton = new Button(alertButtonLocator,"Button shows alert");
    public final Button confirmButton = new Button(confirmButtonLocator,"Button shows confirm alert");
    public final Text confirmText = new Text(confirmResultLocator,"Confirm result");
    public final Button pormtButton = new Button(promtButtonLocator,"Button shows promt alert");
    public final Text promtText = new Text(promtResultLocator,"Prompt result");

    public boolean alertsWrapperVisible(){
        return WaitsUtil.waitForElementVisible(alertsWrapper);
    }

    public boolean alertIsActive(){
        try{
            WaitsUtil.waitForElementPresence(uniqueLocator);
            return false;
        } catch(UnhandledAlertException e){
            return true;
        }
    }

    public AlertForm() {
        super(uniqueLocator, name);
    }
}
