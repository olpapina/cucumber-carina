package com.solvd.cucumbercarina.gui.site.pages;

import com.solvd.cucumbercarina.database.domain.User;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class UserDataPage extends AbstractPage {

    @FindBy(id="first-name")
    private ExtendedWebElement firstNameField;

    @FindBy(id="last-name")
    private ExtendedWebElement lastNameField;

    @FindBy(id="postal-code")
    private ExtendedWebElement zipCodeField;

    @FindBy(id="continue")
    private ExtendedWebElement continueButton;

    @FindBy(xpath="//*[contains(@class,'error-message-container')]")
    private ExtendedWebElement errorMessage;


    public UserDataPage(WebDriver driver) {
        super(driver);
        setPageURL("checkout-step-one.html");
    }

    public void typeData(User user) {
        firstNameField.type(user.getUserName());
        lastNameField.type(user.getLastName());
        zipCodeField.type(user.getZipCode());
    }

    public OverviewPage clickContinueButton() {
        continueButton.click();
        return new OverviewPage(driver);
    }

    public UserDataPage clickContinue() {
        continueButton.click();
        return new UserDataPage(driver);
    }

    public Boolean isErrorPresent() {
        return errorMessage.isPresent();
    }
}
