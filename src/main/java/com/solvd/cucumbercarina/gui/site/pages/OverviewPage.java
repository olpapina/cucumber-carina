package com.solvd.cucumbercarina.gui.site.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class OverviewPage extends AbstractPage {

    @FindBy(xpath = "//*[@class='inventory_item_name']")
    private List<ExtendedWebElement> productTitles;

    @FindBy(id = "finish")
    private ExtendedWebElement finishButton;
    public OverviewPage(WebDriver driver) {
        super(driver);
        setPageURL("checkout-step-two.html");
    }

    public List<String> getProductTitles() {
        return  productTitles.stream()
                     .map(ExtendedWebElement::getText)
                     .collect(Collectors.toList());
    }

    public CompletePage clickFinishButton() {
        finishButton.click();
        return new CompletePage(driver);
    }
}
