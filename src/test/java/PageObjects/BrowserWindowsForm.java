package PageObjects;

import BasePageObjects.BaseForm;
import Elements.Button;
import org.openqa.selenium.By;

public class BrowserWindowsForm extends BaseForm {
    private static final By uniqueLocator = By.id("browserWindows");
    private static final String name = "Browser Windows form";

    private Button newTabButton = new Button(
            By.id("tabButton"),
            "New Tab button");

    public Button getNewTabButton(){
        return newTabButton;
    }

    public BrowserWindowsForm() {
        super(uniqueLocator, name);
    }
}
