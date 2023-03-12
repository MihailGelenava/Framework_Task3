package PageObjects;

import BasePageObjects.BaseForm;
import Elements.Frame;
import org.openqa.selenium.By;

public class FrameForm extends BaseForm {

    private static final By uniqueLocator = By.id("frame1Wrapper");
    private static final String name = "Frame form";

    private final Frame bigFrame = new Frame(
            By.id("frame1"),
            "Big frame",
            By.id("sampleHeading")
            );

    private final Frame smallFrame = new Frame(
            By.id("frame2"),
            "Small frame",
            By.id("sampleHeading")
            );

    public Frame getBigFrame(){
        return bigFrame;
    }

    public Frame getSmallFrame(){
        return smallFrame;
    }

    public FrameForm() {
        super(uniqueLocator, name);
    }
}
