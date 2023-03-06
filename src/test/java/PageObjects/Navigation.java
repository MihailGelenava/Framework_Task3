package PageObjects;

import BasePageObjects.BaseForm;
import Elements.ItemsList;
import org.openqa.selenium.By;

public class Navigation extends BaseForm {
    private static final By uniqueLocator = By.xpath("//div[@class='accordion']");
    private static final String name = "Workspace Page";

    private static final By headNavigationListLocator = By.xpath("//div[@class='accordion']");
    private static final By headDropDownListLocator = By.xpath(
            "//div[contains(@class,'show') and contains(@class,'element-list')]");

    private final ItemsList navigationList = new ItemsList
            (headNavigationListLocator,"nav-menu","//div[text()='%s']");

    private final ItemsList dropdownList = new ItemsList
            (headDropDownListLocator,"DropDown navigation list","//span[text()='%s']");

    public void openExactDropDownList(String pointer){
        scrollIntoView(navigationList.getExactItem(pointer).getElement());
        navigationList.getExactItem(pointer).click();
    }

    public void openExactMenuItem(String pointer){
        scrollIntoView(dropdownList.getExactItem(pointer).getElement());
        dropdownList.getExactItem(pointer).click();
    }

    public Navigation() {
        super(uniqueLocator, name);
    }
}
