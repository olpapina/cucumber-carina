package com.solvd.cucumbercarina.cucumber.steps;

import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import com.solvd.cucumbercarina.database.service.UserOrderService;
import com.solvd.cucumbercarina.database.service.UserService;
import com.solvd.cucumbercarina.database.service.impl.UserOrderServiceImpl;
import com.solvd.cucumbercarina.database.service.impl.UserServiceImpl;
import com.solvd.cucumbercarina.gui.site.components.Product;
import com.solvd.cucumbercarina.database.domain.User;
import com.solvd.cucumbercarina.database.domain.UserOrder;
import com.solvd.cucumbercarina.gui.site.pages.*;
import com.zebrunner.carina.webdriver.IDriverPool;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.solvd.cucumbercarina.utils.StringSplitter.getOrderProducts;

public class SwagLogsStandardSteps extends CucumberRunner implements IDriverPool {

    User user = null;
    List<String> products = null;
    LoginPage loginPage = null;
    ProductPage productPage = null;
    CartPage cartPage = null;
    UserDataPage userDataPage = null;
    OverviewPage overviewPage = null;
    CompletePage completePage = null;


    @Given("^I am on login page as standard user$")
    public boolean iAmOnLoginPage() {
        loginPage = new LoginPage(getDriver());
        loginPage.open();
        return loginPage.isPageOpened();
    }

    @When("^I log in using credentials from DB$")
    public void iLogIntoAccount() {
        UserService userService = new UserServiceImpl();
        user = userService.selectById(3L);
        loginPage.typeUserName(user.getUserName());
        loginPage.typePassword(user.getPassword());
        productPage = loginPage.clickLoginButton();
        Assert.assertTrue(productPage.isPageOpened(), "Product page is not opened!");
    }

    @Then("^page 'Products' should be open$")
    public void productPageShouldBeOpen() {
        Assert.assertTrue(productPage.isPageOpened(), "Product page is not opened!");
    }

    @And("^page 'Products' should contains 6 items$")
    public void productPageShouldContainsSixItems() {
        List<Product> products = productPage.getProducts();
        Assert.assertEquals(products.size(), 6, "Quantity of product doesn't match 6");
    }

    @When("^I add the products from DB in a cart$")
    public void iAddProductsInCart() {
        UserOrderService userOrderService = new UserOrderServiceImpl();
        List <UserOrder> userOrders = userOrderService.selectByUserId(3L);
        String order = userOrders.get(0).getOrderItems();

        products = getOrderProducts(order);
        products.forEach(product -> productPage.clickAddToCartButton(product));
    }

    @And("^I go to 'Cart' page$")
    public void iGoToCartPage() {
        cartPage = productPage.clickCartIcon();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page is not opened!");
    }

    @Then("^page 'Cart' should be open$")
    public void cartPageShouldBeOpen() {
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page is not opened!");
    }

    @And("^page 'Cart' should contains added products$")
    public void cartPageShouldContainsAddedProducts() {
        SoftAssert sa = new SoftAssert();
        products.forEach(product -> sa.assertTrue(cartPage.getProductNames().contains(product), "Cart page doesn't contain the product " + product));
        sa.assertAll();
    }

    @When("^I click Checkout button$")
    public void iClickCheckout() {
        userDataPage = cartPage.clickCheckoutButton();
        Assert.assertTrue(userDataPage.isPageOpened(), "UserData page is not opened!");
    }

    @Then("^page with user data should be open$")
    public void userDataPageShouldBeOpen() {
        Assert.assertTrue(userDataPage.isPageOpened(), "UserData page is not opened!");
    }

    @When("^I enter data$")
    public void iEnterUserData() {
        userDataPage.typeData(user);
        overviewPage = userDataPage.clickContinueButton();
        Assert.assertTrue(overviewPage.isPageOpened(), "Overview page is not opened!");
    }

    @Then("^page 'Overview' should be open$")
    public void pageOverViewShouldBeOpened() {
        Assert.assertTrue(overviewPage.isPageOpened(), "Overview page is not opened!");
    }

    @And("^page 'Overview' should contains added products$")
    public void overviewPageShouldContainsAddedProducts() {
        SoftAssert sa = new SoftAssert();
        products.forEach(product -> sa.assertTrue(overviewPage.getProductTitles().contains(product), "Overview page doesn't contain the product " + product));
        sa.assertAll();
    }

    @When("^I click 'Finish' button")
    public void  clickFinish() {
        completePage = overviewPage.clickFinishButton();
    }

    @Then("^page 'Complete' should be open$")
    public void completePageShouldBeOpened() {
        Assert.assertTrue(completePage.isPageOpened(), "Complete page is not opened!");
    }

    @And("^page 'Complete' should contains successful message")
    public void completePageShouldContains() {
        Assert.assertTrue(completePage.isCompletedPresent(), "Complete page doesn't contain successful message!");
    }
}
