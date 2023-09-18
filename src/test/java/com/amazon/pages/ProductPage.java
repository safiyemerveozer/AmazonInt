package com.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{

    @FindBy(id ="quantity")
    public WebElement quantitySelect;

    @FindBy(id="add-to-cart-button")
    public WebElement addToCartButton;
}
