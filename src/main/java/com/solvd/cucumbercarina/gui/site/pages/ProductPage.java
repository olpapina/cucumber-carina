package com.solvd.cucumbercarina.gui.site.pages;

import com.solvd.cucumbercarina.gui.site.components.Product;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends AbstractPage {

    @FindBy(xpath = "//*[text() = '%s']//ancestor::div[@class = 'inventory_item_description']//button[contains(@id, 'add-to-cart')]")
    private ExtendedWebElement addToCartButton;


    @FindBy(xpath = "//*[@class='inventory_item_description']")
    private List<Product> products;

    @FindBy(id = "shopping_cart_container")
    private ExtendedWebElement cartIcon;

    public ProductPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("https://www.saucedemo.com/inventory.html");
    }

    public List<Product> getProducts() {
        return products;
    }

    public CartPage clickCartIcon() {
        cartIcon.click(5L);
        return new CartPage(driver);
    }

    public void clickAddToCartButton(String product) {
        addToCartButton.format(product).click();
    }
}
