package com.solvd.cucumbercarina;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.cucumbercarina.database.domain.User;
import com.solvd.cucumbercarina.database.domain.UserOrder;
import com.solvd.cucumbercarina.database.service.UserOrderService;
import com.solvd.cucumbercarina.database.service.UserService;
import com.solvd.cucumbercarina.database.service.impl.UserOrderServiceImpl;
import com.solvd.cucumbercarina.database.service.impl.UserServiceImpl;
import com.solvd.cucumbercarina.gui.site.pages.*;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.solvd.cucumbercarina.utils.StringSplitter.getOrderProducts;

public class SwagLogsPerformanceUserTest implements IAbstractTest {

    @Test(testName = "verify performance user checkout process using Cookie")
    public void verifyPerformanceUserFlowTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.addCookie(new Cookie("session-username","performance_glitch_user"));
        ProductPage productPage = new ProductPage(getDriver());
        productPage.open();

        UserService userService = new UserServiceImpl();
        User user = userService.selectById(6L);
        UserOrderService userOrderService = new UserOrderServiceImpl();
        List <UserOrder> userOrders = userOrderService.selectByUserId(6L);
        String order = userOrders.get(0).getOrderItems();

        List<String> items = getOrderProducts(order);
        items.forEach(productPage::clickAddToCartButton);
        CartPage cartPage = productPage.clickCartIcon();

        UserDataPage userDataPage = cartPage.clickCheckoutButton();
        userDataPage.typeData(user);
        OverviewPage overviewPage = userDataPage.clickContinueButton();

        CompletePage completePage = overviewPage.clickFinishButton();

        Assert.assertTrue(completePage.isCompletedPresent(), "Complete page doesn't contain successful message!");
    }
}
