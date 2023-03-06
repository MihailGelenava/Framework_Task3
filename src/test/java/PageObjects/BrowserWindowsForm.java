package PageObjects;

import BasePageObjects.BaseForm;
import Elements.Button;
import org.openqa.selenium.By;

public class BrowserWindowsForm extends BaseForm {
    private static final By uniqueLocator = By.id("browserWindows");
    private static final String name = "Browser Windows form";

    private static final By newTabLocator = By.id("tabButton");

    public Button newTabButton = new Button(newTabLocator,"New Tab button");


    public BrowserWindowsForm() {
        super(uniqueLocator, name);
    }
}
