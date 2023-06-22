package com.solvd.cucumbercarina.gui.site.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CompletePage extends AbstractPage {

    @FindBy(id="checkout_complete_container")
    private ExtendedWebElement successfulContainer;
    public CompletePage(WebDriver driver) {
        super(driver);
        setPageURL("checkout-complete.html");
    }

    public Boolean isCompletedPresent() {
        return successfulContainer.isPresent();
    }


}
