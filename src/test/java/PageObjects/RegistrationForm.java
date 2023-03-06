package PageObjects;

import BasePageObjects.BaseForm;
import Elements.Button;
import Elements.Input;
import model.User;
import org.openqa.selenium.By;

public class RegistrationForm extends BaseForm {

    private static final By uniqueLocator = By.cssSelector(".modal-content");
    private static final String name = "Registration form";

    private static final By firstNameInputLocator = By.id("firstName");
    private static final By lastNameInputLocator = By.id("lastName");
    private static final By emailInputLocator = By.id("userEmail");
    private static final By ageInputLocator = By.id("age");
    private static final By salaryInputLocator = By.id("salary");
    private static final By departmentInputLocator = By.id("department");

    private static final By submitButtonLocator = By.id("submit");

    public final Button submitButton = new Button(submitButtonLocator,"Submit button for registration form");

    public final Input firstNameInput = new Input(firstNameInputLocator,"First name input field");
    public final Input lastNameInput = new Input(lastNameInputLocator,"Last name input field");
    public final Input emailInput = new Input(emailInputLocator,"Email input field");
    public final Input ageInput = new Input(ageInputLocator,"Age input field");
    public final Input salaryInput = new Input(salaryInputLocator,"Salary input field");
    public final Input departmentInput = new Input(departmentInputLocator,"Department input field");

    public final Button closeButton = new Button(
            By.xpath("//div[@class='modal-content']//span[text()='×']"),"Close registration form button");

    public void fillRegistrationForm(User user){
        firstNameInput.sendText(user.firstName);
        lastNameInput.sendText(user.lastName);
        emailInput.sendText(user.email);
        ageInput.sendText(user.age);
        salaryInput.sendText(user.salary);
        departmentInput.sendText(user.department);

        submitButton.click();
    }

    public RegistrationForm() {
        super(uniqueLocator, name);
    }

    public boolean isClosed(){
        return (Driver.Driver.getDriver().findElements(uniqueLocator).size() == 0);
    }
}
