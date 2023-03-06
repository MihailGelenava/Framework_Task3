package PageObjects;

import BasePageObjects.BaseForm;
import Elements.Link;
import org.openqa.selenium.By;

public class LinksForm extends BaseForm {
    private static final By uniqueLocator = By.id("linkWrapper");
    private static final String name = "Elements -> Links page";

    private static final By homeLinkLocator = By.id("simpleLink");

    public Link homeLink = new Link(homeLinkLocator,"Home link");

    public LinksForm() {
        super(uniqueLocator, name);
    }
}
