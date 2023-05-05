package com.solvd.cucumbercarina.cucumber.steps;

import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import com.solvd.cucumbercarina.database.domain.User;
import com.solvd.cucumbercarina.database.service.UserService;
import com.solvd.cucumbercarina.database.service.impl.UserServiceImpl;
import com.solvd.cucumbercarina.gui.site.pages.LoginPage;
import com.solvd.cucumbercarina.gui.site.pages.ProductPage;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.IDriverPool;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class SwagLogsLockedSteps extends CucumberRunner implements IDriverPool {
    User user = null;
    List<String> products = null;
    LoginPage loginPage = null;

    @Given("^I am on Login page as locked user$")
    public boolean iAmOnLoginPage() {
        loginPage = new LoginPage(getDriver());
        loginPage.open();
        return loginPage.isPageOpened();
    }

    @When("^I try to log in my account$")
    public void iTryLogIntoAccount() {
        UserService userService = new UserServiceImpl();
        user = userService.selectById(4L);
        loginPage.typeUserName(user.getUserName());
        loginPage.typePassword(user.getPassword());
        ProductPage productPage = loginPage.clickLoginButton();
        Assert.assertFalse(productPage.isPageOpened(), "Product page is opened!");
    }

    @Then("^I am still on login page$")
    public void loginPageStillOpen() {
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened!");
    }

    @And("^Error message is present on the page$")
    public void errorMessageIsPresent() {
        String errorMessage = loginPage.getErrorText();
        String expectedMessage = R.TESTDATA.get("expectedMessage");
        Assert.assertEquals(errorMessage,expectedMessage, "error message is not equal!");
    }
}

