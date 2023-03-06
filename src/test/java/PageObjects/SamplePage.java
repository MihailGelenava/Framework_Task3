package PageObjects;

import BasePageObjects.BaseForm;
import org.openqa.selenium.By;

public class SamplePage extends BaseForm {
    private static final By uniqueLocator = By.id("sampleHeading");
    private static final String name = "Sample page in New Tab";

    public SamplePage() {
        super(uniqueLocator, name);
    }
}
