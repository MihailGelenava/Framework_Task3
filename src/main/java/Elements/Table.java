package Elements;

import BasePageObjects.BaseElement;
import Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Table extends BaseElement {

    private final By rowsLocator;

    private List<WebElement> tableRows;


    public Table(By tableLocator, String name, By rowsLocator) {
        super(tableLocator, name);
        this.rowsLocator = rowsLocator;
    }

    public List<WebElement> getTableRows(){
        tableRows = Driver.getDriver().findElements(rowsLocator);

        logger.info("From " + getName() + " gotten list of rows");

        return tableRows;
    }

}
