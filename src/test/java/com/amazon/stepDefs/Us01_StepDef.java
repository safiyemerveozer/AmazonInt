package com.amazon.stepDefs;

import com.amazon.pages.AmazonCartPage;
import com.amazon.pages.BasePage;
import com.amazon.pages.ProductPage;
import com.amazon.pages.ResultPage;
import com.amazon.utility.ConfigReader;
import com.amazon.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Us01_StepDef extends BasePage {
    ResultPage resultPage = new ResultPage();
    ProductPage productPage = new ProductPage();
    AmazonCartPage amazonCartPage = new AmazonCartPage();

    @Given("user is on the amazon home page")
    public void user_is_on_the_amazon_home_page() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }

    @When("user search for {string}")
    public void user_search_for(String string) {
        searchBox.sendKeys(string);
    }

    @When("user click the search button")
    public void user_click_the_search_button() {
        searchButton.click();
    }

    @When("users clicks first hat link")
    public void users_clicks_first_hat_link() {
        resultPage.firstResultLink.click();
    }

    Select select = null;

    @When("user make the first appearing hat quantity {int}")
    public void user_make_the_first_appearing_hat_quantity(Integer quantity) {
        select = new Select(productPage.quantitySelect);

        select.selectByValue(quantity + "");
    }

    @When("user add hats to cart")
    public void user_add_hats_to_cart() {
        productPage.addToCartButton.click();

    }

    double price = 0;
    double priceTotal = 0;

    String unitPrice = "";
    String totalPrice = "";

    @When("user open cart and assert that the total price and quantity are correct")
    public void user_open_cart_and_assert_that_the_total_price_and_quantity_are_correct() {
        amazonCartPage.goToCart.click();

        Assert.assertTrue(select.getFirstSelectedOption().getText().equals("2"));

        unitPrice = amazonCartPage.oneUnitPrice.getText();
        unitPrice = unitPrice.substring(1);
        price = Double.parseDouble(unitPrice);
        System.out.println(unitPrice);

        totalPrice = amazonCartPage.totalPrice.getText();
        totalPrice = totalPrice.substring(1);
        priceTotal = Double.parseDouble(totalPrice);
        System.out.println(totalPrice);

        Assert.assertTrue("Total price verification fail", priceTotal == (2 * price));

    }

    @When("user reduce the quantity from two to one in Cart for the item selected")
    public void user_reduce_the_quantity_from_two_to_one_in_cart_for_the_item_selected() {
        select.selectByValue("1");
    }

    @Then("Verify that the total price and quantity has been correctly changed")
    public void verify_that_the_total_price_and_quantity_has_been_correctly_changed() {

        Assert.assertTrue(select.getFirstSelectedOption().getText().trim().equals("1"));


        unitPrice = amazonCartPage.oneUnitPrice.getText();
        unitPrice = unitPrice.substring(1);
        price = Double.parseDouble(unitPrice);
        System.out.println(price);

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(amazonCartPage.totalPrice, unitPrice));

        totalPrice = amazonCartPage.totalPrice.getText();
        totalPrice = totalPrice.substring(1);
        priceTotal = Double.parseDouble(totalPrice);
        System.out.println(totalPrice);

        Assert.assertTrue(price == priceTotal);
    }
}
