package com.solvd.cucumbercarina.gui.site.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Product extends AbstractUIObject {

    @FindBy(xpath = ".//*[contains(@class,'inventory_item_name')]")
    private ExtendedWebElement productName;

    @FindBy(xpath = ".//*[contains(@id, 'add-to-cart')]")
    private ExtendedWebElement addToCartButton;

    public Product(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
}

