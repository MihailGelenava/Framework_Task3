package PageObjects;

import BasePageObjects.BaseForm;
import Elements.Frame;
import org.openqa.selenium.By;

public class NestedFramesForm extends BaseForm {

    private static final By uniqueLocator = By.id("framesWrapper");
    private static final String name = "Nested Frame form";

    private final Frame parentFrame = new Frame(
            By.xpath("//iframe[@id='frame1']"),
            "Parent frame",
            By.xpath("//body")
            );

    private final Frame childFrame = new Frame(
            By.xpath("//iframe[contains(@srcdoc,'Child Iframe')]"),
            "Child frame",
            By.xpath("//body//p")
            );

    public Frame getParentFrame(){
        return parentFrame;
    }

    public Frame getChildFrame(){
        return childFrame;
    }

    public NestedFramesForm() {
        super(uniqueLocator, name);
    }
}
