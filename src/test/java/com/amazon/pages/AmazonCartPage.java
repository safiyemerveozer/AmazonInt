package com.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AmazonCartPage extends BasePage{

    @FindBy(xpath = "//a[@href='/cart?ref_=sw_gtc']")
    public WebElement goToCart;

    @FindBy(xpath = "//div[@class='sc-badge-price-to-pay']//span")
    public WebElement oneUnitPrice;

    @FindBy(xpath = "//span[@id='sc-subtotal-amount-activecart']/span")
    public WebElement totalPrice;

}
