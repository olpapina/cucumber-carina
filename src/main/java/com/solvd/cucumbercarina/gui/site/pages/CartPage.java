package com.solvd.cucumbercarina.gui.site.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends AbstractPage {

    @FindBy(xpath = "//*[@class='inventory_item_name']")
    private List<ExtendedWebElement> cartProducts;

    @FindBy(id = "checkout")
    private ExtendedWebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        setPageURL("cart.html");
    }

    public List<String> getProductNames() {
        return cartProducts.stream()
                .map(ExtendedWebElement::getText)
                .collect(Collectors.toList());
    }

    public UserDataPage clickCheckoutButton() {
        checkoutButton.click();
        return new UserDataPage(driver);
    }
}
