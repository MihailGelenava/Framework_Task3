package PageObjects;

import BasePageObjects.BaseForm;
import Elements.Button;
import Elements.Table;
import Utils.BrowserUtils;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class WebTables extends BaseForm {

    private static final By uniqueLocator = By.cssSelector(".web-tables-wrapper");
    private static final String name = "Web Tables page";

    private static final By addButtonLocator = By.id("addNewRecordButton");
    private static final By deleteRowLocator = By.xpath(".//span[contains(@id,'delete-record')]");

    private static final By filledRowsLocator = By.xpath
            ("//div[@class='rt-tr-group']//div[text()[not(contains(.,'span'))] and @class[not(contains(.,'action-buttons'))] ]/..");
    private static final String columnSignature = ".//div[@role='gridcell' and position()='%d']";

    private final Table table = new Table(By.cssSelector(".rt-table"),"Users Table body",filledRowsLocator);


    public final Button addButton = new Button(addButtonLocator,"Add button");

    public WebTables() {
        super(uniqueLocator, name);
    }

    public List<User> getTableUsers(){
        List<User> users = new ArrayList<>();
        List<WebElement> rows = table.getTableRows();
        for (WebElement row : rows){
            String firstName = row.findElement(
                    By.xpath(String.format(columnSignature,1))).getText();
            String lastName = row.findElement(
                    By.xpath(String.format(columnSignature,2))).getText();
            String age = row.findElement(
                    By.xpath(String.format(columnSignature,3))).getText();
            String email = row.findElement(
                    By.xpath(String.format(columnSignature,4))).getText();
            String salary = row.findElement(
                    By.xpath(String.format(columnSignature,5))).getText();
            String department = row.findElement(
                    By.xpath(String.format(columnSignature,6))).getText();
            users.add(new User(firstName,lastName,email,Integer.parseInt(age),Integer.parseInt(salary),department));
        }
        return users;
    }

    public WebElement getExactUserRow(User u) {
        List<WebElement> rows = table.getTableRows();
        for (WebElement row : rows){
            String firstName = row.findElement(
                    By.xpath(String.format(columnSignature,1))).getText();
            String lastName = row.findElement(
                    By.xpath(String.format(columnSignature,2))).getText();
            String age = row.findElement(
                    By.xpath(String.format(columnSignature,3))).getText();
            String email = row.findElement(
                    By.xpath(String.format(columnSignature,4))).getText();
            String salary = row.findElement(
                    By.xpath(String.format(columnSignature,5))).getText();
            String department = row.findElement(
                    By.xpath(String.format(columnSignature,6))).getText();
            if (new User(firstName,lastName,email,Integer.parseInt(age),Integer.parseInt(salary),department).equals(u)){
                return row;
            }
        }
        return null;
    }

    public void deleteUser(User u){
        BrowserUtils.scrollToWebElement(getExactUserRow(u).findElement(
                deleteRowLocator
        ));
        getExactUserRow(u).findElement(
                deleteRowLocator
        ).click();
    }
}
