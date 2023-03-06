package PageObjects;

import BasePageObjects.BaseForm;
import Elements.Frame;
import org.openqa.selenium.By;

public class FrameForm extends BaseForm {

    private static final By uniqueLocator = By.id("frame1Wrapper");
    private static final String name = "Frame form";

    private static final By frame1Locator = By.id("frame1");
    private static final By frame2Locator = By.id("frame2");

    public final Frame bigFrame = new Frame(frame1Locator,"Big frame");
    public final Frame smallFrame = new Frame(frame2Locator,"Small frame");


    public FrameForm() {
        super(uniqueLocator, name);
    }
}
