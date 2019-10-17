package ru.stqa.Task19;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class ProductPage extends Page {

    @FindBy(className = "quantity")
    WebElement quantity;

    @FindBy(className = "options")
    List<WebElement> options;

    @FindBy(name = "options[Size]")
    WebElement optionsSize1;

    @FindBy(name = "add_cart_product")
    WebElement addCartProduct;

    public ProductPage  (WebDriver driver) {
        super(driver);
    }

    public void addProductToCart() {
        Integer count = Integer.parseInt(quantity.getText()) + 1;;
        if (options.size() != 0 ) {
            WebElement optionsSize = optionsSize1;
            Select option = new Select(optionsSize);
            option.selectByValue("Small");
        }
        addCartProduct.click();
        wait.until(ExpectedConditions.textToBe(By.className("quantity"), count.toString()));
        Assert.assertTrue(quantity.getText().equals(count.toString()));
    }
}
