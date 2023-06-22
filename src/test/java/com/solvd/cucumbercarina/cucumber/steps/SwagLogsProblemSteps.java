package com.solvd.cucumbercarina.cucumber.steps;

import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import com.solvd.cucumbercarina.database.domain.User;
import com.solvd.cucumbercarina.database.domain.UserOrder;
import com.solvd.cucumbercarina.database.service.UserOrderService;
import com.solvd.cucumbercarina.database.service.UserService;
import com.solvd.cucumbercarina.database.service.impl.UserOrderServiceImpl;
import com.solvd.cucumbercarina.database.service.impl.UserServiceImpl;
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

public class SwagLogsProblemSteps extends CucumberRunner implements IDriverPool {
    User user = null;
    List<String> products = null;
    LoginPage loginPage = null;
    ProductPage productPage = null;
    CartPage cartPage = null;
    UserDataPage userDataPage = null;

    @Given("^I am on Login page as problem user$")
    public boolean iAmOnLoginPage() {
        loginPage = new LoginPage(getDriver());
        loginPage.open();
        return loginPage.isPageOpened();
    }

    @When("^I log in using credentials$")
    public void iLogIntoAccount() {
        UserService userService = new UserServiceImpl();
        user = userService.selectById(5L);
        loginPage.typeUserName(user.getUserName());
        loginPage.typePassword(user.getPassword());
        productPage = loginPage.clickLoginButton();
        Assert.assertTrue(productPage.isPageOpened(), "Product page is not opened!");
    }

    @Then("^page 'Products' is opened$")
    public void productPageShouldBeOpen() {
        Assert.assertTrue(productPage.isPageOpened(), "Product page is not opened!");
    }

    @And("^page 'Products' should contains 6 the same pictures instead of different product images$")
    public void productPageShouldContainsSixItems() {
        List<String> productPageImages = productPage.getImages();
        SoftAssert sa = new SoftAssert();
        productPageImages.forEach(productPageImage -> sa.assertEquals(productPageImages.get(0), productPageImages.get(1)));
        sa.assertAll();
    }

    @When("^I click 'Add to cart' button for products from DB$")
    public void iAddProductsInCart() {
        UserOrderService userOrderService = new UserOrderServiceImpl();
        List<UserOrder> userOrders = userOrderService.selectByUserId(5L);
        String order = userOrders.get(0).getOrderItems();

        products = getOrderProducts(order);
        products.forEach(product -> productPage.clickAddToCartButton(product));
    }

    @Then("^products are in the cart, check the quantity$")
    public void checkQuantityProducts() {
        Integer getQuantityProductPage = productPage.getQuantity();
        Assert.assertEquals(getQuantityProductPage, products.size(), "please");
    }

    @When("^I click 'Cart' button$")
    public void goToCart() {
        cartPage = productPage.clickCartIcon();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page is not opened!");
    }

    @Then("^'Cart' page is opened$")
    public void cartPageShouldBeOpen() {
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page is not opened!");
    }

    @And("^'Cart' page should contains added products$")
    public void cartPageShouldContainsAddedProducts() {
        SoftAssert sa = new SoftAssert();
        products.forEach(product -> sa.assertTrue(cartPage.getProductNames().contains(product), "Cart page doesn't contain the product " + product));
        sa.assertAll();
    }

    @When("^I click 'Checkout' button$")
    public void iClickCheckout() {
        userDataPage = cartPage.clickCheckoutButton();
        Assert.assertTrue(userDataPage.isPageOpened(), "UserData page is not opened!");
    }

    @Then("^User data page should be open$")
    public void userDataPageShouldBeOpen() {
        Assert.assertTrue(userDataPage.isPageOpened(), "UserData page is not opened!");
    }

    @When("^I add user info$")
    public void iEnterUserData() {
        userDataPage.typeData(user);
    }

    @And("^click Continue button$")
    public void iClickContinue() {
        userDataPage = userDataPage.clickContinue();
        Assert.assertTrue(userDataPage.isPageOpened(), "User is not on the same page");
    }

    @Then("^Error message appears on the page$")
    public void iCheckError() {
        Assert.assertTrue(userDataPage.isErrorPresent(), "Error message doesn't appear");
    }
}
