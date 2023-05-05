package com.solvd.cucumbercarina.gui.site.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(id = "user-name")
    private ExtendedWebElement userNameField;

    @FindBy(id = "password")
    private ExtendedWebElement passwordField;

    @FindBy(id="login-button")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//*[@class='error-message-container error']")
    private ExtendedWebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void typeUserName(String userName) {
        userNameField.type(userName);
    }

    public void typePassword(String password) {
        passwordField.type(password);
    }

    public ProductPage clickLoginButton() {
       loginButton.click(5L);
       return new ProductPage(driver);
    }

    public String getErrorText() {
        return errorMessage.getText();
    }
}
