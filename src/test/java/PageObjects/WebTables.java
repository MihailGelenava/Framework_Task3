package PageObjects;

import BasePageObjects.BaseForm;
import Elements.Button;
import Elements.Table;
import Utils.BrowserUtils;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class WebTables extends BaseForm {

    private static final By uniqueLocator = By.cssSelector(".web-tables-wrapper");
    private static final String name = "Web Tables page";

    private static final By deleteRowLocator = By.xpath(".//span[contains(@id,'delete-record')]");

    private static final String columnSignature = ".//div[@role='gridcell' and position()='%d']";

    private final Table table = new Table(
            By.cssSelector(".rt-table"),
            "Users Table body",
            By.xpath("//div[@class='rt-tr-group']//div[text()[not(contains(.,'span'))] and @class[not(contains(.,'action-buttons'))] ]/..")
    );

    private final Button addButton = new Button(
            By.id("addNewRecordButton"),
            "Add button");

    private static HashMap<String, Integer> columnNames;
    static{
        columnNames = new HashMap<>();
        columnNames.put("First Name",1);
        columnNames.put("Last Name",2);
        columnNames.put("Age",3);
        columnNames.put("Email",4);
        columnNames.put("Salary",5);
        columnNames.put("Department",6);
        columnNames.put("Action",7);
    }

    public Button getAddButton(){
        return addButton;
    }

    public WebTables() {
        super(uniqueLocator, name);
    }

    public String getColumnValue(WebElement row, String fieldName){
        return row.findElement(
                By.xpath(
                        String.format(columnSignature, columnNames.get(fieldName)))
        ).getText();
    }

    public List<User> getTableUsers(){
        List<User> users = new ArrayList<>();
        List<WebElement> rows = table.getTableRows();
        for (WebElement row : rows){
            String firstName = getColumnValue(row,"First Name");
            String lastName = getColumnValue(row,"Last Name");
            String age = getColumnValue(row,"Age");
            String email = getColumnValue(row,"Email");
            String salary = getColumnValue(row,"Salary");
            String department = getColumnValue(row,"Department");
            users.add(new User(firstName,lastName,email,Integer.parseInt(age),Integer.parseInt(salary),department));
        }
        return users;
    }

    public WebElement getExactUserRow(User u) {
        List<WebElement> rows = table.getTableRows();
        for (WebElement row : rows){
            String firstName = getColumnValue(row,"First Name");
            String lastName = getColumnValue(row,"Last Name");
            String age = getColumnValue(row,"Age");
            String email = getColumnValue(row,"Email");
            String salary = getColumnValue(row,"Salary");
            String department = getColumnValue(row,"Department");
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
