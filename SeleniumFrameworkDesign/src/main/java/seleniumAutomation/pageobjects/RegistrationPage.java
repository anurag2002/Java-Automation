package seleniumAutomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import seleniumAutomation.AbstractComponents.AbstractComponents;

public class RegistrationPage extends AbstractComponents {

    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // PageFactory
    @FindBy(id = "firstName")
    WebElement firstNameInput;

    @FindBy(id = "lastName")
    WebElement lastNameInput;

    @FindBy(id = "userEmail")
    WebElement emailInput;

    @FindBy(id = "userMobile")
    WebElement phoneInput;

    @FindBy(css = "select[formcontrolname='occupation']")
    WebElement occupationDropdown;

    @FindBy(css = "input[type='radio'][value='Male']")
    WebElement genderMaleRadio;

    @FindBy(css = "input[type='radio'][value='Female']")
    WebElement genderFemaleRadio;

    @FindBy(id = "userPassword")
    WebElement passwordInput;

    @FindBy(id = "confirmPassword")
    WebElement confirmPasswordInput;

    @FindBy(css = "input[type='checkbox'][formcontrolname='required']")
    WebElement ageCheckbox;

    @FindBy(id = "login")
    WebElement registerBtn;

    @FindBy(className = "toast-success")
    WebElement successToast;

    @FindBy(className = "toast-error")
    WebElement errorToast;

    public void completeRegistration(
            String firstName, String lastName, String email, String phone,
            String occupation, String gender, String password, String confirmPassword) {

        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        phoneInput.sendKeys(phone);

        Select selectOccupation = new Select(occupationDropdown);
        selectOccupation.selectByVisibleText(occupation);

        if (gender.equalsIgnoreCase("Male")) {
            genderMaleRadio.click();
        } else if (gender.equalsIgnoreCase("Female")) {
            genderFemaleRadio.click();
        }

        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(confirmPassword);

        if (!ageCheckbox.isSelected()) {
            ageCheckbox.click();
        }

        registerBtn.click();
    }

    public String getSuccessMessage() {
        waitForElementToAppear(successToast);
        return successToast.getText();
    }

    public String getErrorMessage() {
        waitForElementToAppear(errorToast);
        return errorToast.getText();
    }
}