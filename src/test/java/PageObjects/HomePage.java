package PageObjects;

import BasePageObjects.BaseForm;
import Elements.ItemsList;
import org.openqa.selenium.By;

public class HomePage extends BaseForm {

    private static final By uniqueLocator = By.xpath("//div[contains(@class,'home-body')]");
    private static final String name = "Home page";

    private static final By cardsListLocator = By.cssSelector(".category-cards");

    private final ItemsList HomePageCards = new ItemsList
            (cardsListLocator,"List of cards on Home Page",
                    "//h5[contains(text(),'%s')]/../../..");

    public HomePage() {
        super(uniqueLocator, name);
    }

    public HomePage openCard(String pointer){
        scrollIntoView(HomePageCards.getExactItem(pointer).getElement());
        HomePageCards.getExactItem(pointer).getElement().click();
        return this;
    }

}
