package Elements;

import BasePageObjects.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

public class ItemsList extends BaseElement {

    private final String itemListSignature;

    public ItemsList(By locator, String name, String itemListSignature) {
        super(locator, name);
        this.itemListSignature = itemListSignature;
    }

    public Item getExactItem(String point){
        return new Item(
                new ByChained(locator, By.xpath(String.format(itemListSignature,point))),point
        );
    }
}
