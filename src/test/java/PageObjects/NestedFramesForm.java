package PageObjects;

import BasePageObjects.BaseForm;
import Driver.Driver;
import Elements.Frame;
import org.openqa.selenium.By;

public class NestedFramesForm extends BaseForm {

    private static final By uniqueLocator = By.id("framesWrapper");
    private static final String name = "Nested Frame form";

    private static final By parentFrameLocator = By.xpath("//iframe[@id='frame1']");
    private static final By childFrameLocator = By.xpath("//iframe[contains(@srcdoc,'Child Iframe')]");

    public final Frame parentFrame = new Frame(parentFrameLocator,"Parent frame");
    public final Frame childFrame = new Frame(childFrameLocator,"Child frame");

    public NestedFramesForm() {
        super(uniqueLocator, name);
    }
}
