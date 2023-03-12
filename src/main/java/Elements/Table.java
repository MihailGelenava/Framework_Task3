package Elements;

import BasePageObjects.BaseForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Table extends BaseForm {

    private final By rowsLocator;

    public Table(By tableLocator, String name, By rowsLocator) {
        super(tableLocator, name);
        this.rowsLocator = rowsLocator;
    }

    public List<WebElement> getTableRows(){

        logger.info("From " + getFormName() + " gotten list of rows");

        return getElements(rowsLocator);
    }

}
