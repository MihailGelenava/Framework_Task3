package PageObjects;

import BasePageObjects.BaseForm;
import Elements.Button;
import Elements.Input;
import model.User;
import org.openqa.selenium.By;

public class RegistrationForm extends BaseForm {

    private static final By uniqueLocator = By.cssSelector(".modal-content");
    private static final String name = "Registration form";

    private final Button submitButton = new Button(
            By.id("submit"),
            "Submit button for registration form");

    private final Input firstNameInput = new Input(By.id("firstName"),"First name input field");
    private final Input lastNameInput = new Input(By.id("lastName"),"Last name input field");
    private final Input emailInput = new Input(By.id("userEmail"),"Email input field");
    private final Input ageInput = new Input(By.id("age"),"Age input field");
    private final Input salaryInput = new Input(By.id("salary"),"Salary input field");
    private final Input departmentInput = new Input(By.id("department"),"Department input field");

    private final Button closeButton = new Button(
            By.xpath("//div[@class='modal-content']//span[text()='×']"),
            "Close registration form button");

    public void fillFirstNameInput(String firstName){
        firstNameInput.sendText(firstName);
    }

    public void fillLastNameInput(String lastName){
        lastNameInput.sendText(lastName);
    }

    public void fillEmailInput(String email){
        emailInput.sendText(email);
    }

    public void fillAgeInput(int age){
        ageInput.sendText(age);
    }

    public void fillSalaryInput(int salary){
        salaryInput.sendText(salary);
    }

    public void fillDepartmentInput(String department){
        departmentInput.sendText(department);
    }

    public void submitRegistration(){
        submitButton.click();
    }

    public void fillRegistrationForm(User user){
        fillFirstNameInput(user.firstName);
        fillLastNameInput(user.lastName);
        fillEmailInput(user.email);
        fillAgeInput(user.age);
        fillSalaryInput(user.salary);
        fillDepartmentInput(user.department);
    }

    public RegistrationForm() {
        super(uniqueLocator, name);
    }

}
