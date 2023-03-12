package PageObjects;

import BasePageObjects.BaseForm;
import Elements.Button;
import Elements.Text;
import Utils.WaitsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;

public class AlertForm extends BaseForm {
    private static final By alertsWrapper = By.id("javascriptAlertsWrapper");
    private static final String name = "Alert form";

    private final Button alertButton = new Button(
            By.id("alertButton"),
            "Button shows alert");

    private final Button confirmButton = new Button(
            By.id("confirmButton"),
            "Button shows confirm alert");

    private final Text confirmText = new Text(
            By.id("confirmResult"),
            "Confirm result");

    private final Button promtButton = new Button(
            By.id("promtButton"),
            "Button shows promt alert");

    private final Text promtText = new Text(
            By.id("promptResult"),
            "Prompt result");


    public boolean alertIsActive(){
        try{
            WaitsUtil.waitForElementPresence(alertsWrapper);
            return false;
        } catch(UnhandledAlertException e){
            return true;
        }
    }

    public Button getAlertButton(){
        return alertButton;
    }

    public Button getConfirmButton(){
        return confirmButton;
    }

    public Text getConfirmText(){
        return confirmText;
    }

    public Button getPromtButton(){
        return promtButton;
    }

    public Text getPromtText(){
        return promtText;
    }

    public AlertForm() {
        super(alertsWrapper, name);
    }
}
