package PageObjects;

import BasePageObjects.BaseForm;
import Elements.ItemsList;
import org.openqa.selenium.By;

public class Navigation extends BaseForm {
    private static final By uniqueLocator = By.xpath("//div[@class='accordion']");
    private static final String name = "Workspace Page";

    private final ItemsList navigationList = new ItemsList(
            By.xpath("//div[@class='accordion']"),
            "nav-menu",
            "//div[text()='%s']");

    private final ItemsList dropdownList = new ItemsList(
            By.xpath("//div[contains(@class,'show') and contains(@class,'element-list')]"),
            "DropDown navigation list",
            "//span[text()='%s']");

    private Navigation openExactDropDownList(String pointer){
        scrollIntoView(navigationList.getExactItem(pointer).getElement());
        navigationList.getExactItem(pointer).click();
        return this;
    }

    private Navigation openExactMenuItem(String pointer){
        scrollIntoView(dropdownList.getExactItem(pointer).getElement());
        dropdownList.getExactItem(pointer).click();
        return this;
    }

    public Navigation openNavElements(){
        openExactDropDownList("Elements");
        return this;
    }

    public Navigation openAlertsFrameWindows(){
        openExactMenuItem("Alerts");
        return this;
    }

    public Navigation openNestedFrames(){
        openExactMenuItem("Nested Frames");
        return this;
    }

    public Navigation openFrames(){
        openExactMenuItem("Frames");
        return this;
    }

    public Navigation openWebTables(){
        openExactMenuItem("Web Tables");
        return this;
    }

    public Navigation openBrowserWindows(){
        openExactMenuItem("Browser Windows");
        return this;
    }

    public Navigation openLinks(){
        openExactMenuItem("Links");
        return this;
    }

    public Navigation() {
        super(uniqueLocator, name);
    }
}
