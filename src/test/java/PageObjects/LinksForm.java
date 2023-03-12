package PageObjects;

import BasePageObjects.BaseForm;
import Elements.Link;
import org.openqa.selenium.By;

public class LinksForm extends BaseForm {
    private static final By uniqueLocator = By.id("linkWrapper");
    private static final String name = "Elements -> Links page";

    private Link homeLink = new Link(
            By.id("simpleLink"),
            "Home link");

    public Link getHomeLink(){
        return homeLink;
    }

    public LinksForm() {
        super(uniqueLocator, name);
    }
}
