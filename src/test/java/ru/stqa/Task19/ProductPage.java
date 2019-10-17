package ru.stqa.Task19;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class ProductPage extends Page {

    public ProductPage  (WebDriver driver) {
        super(driver);
    }

    public void addProductToCart() {
        Integer count = Integer.parseInt(driver.findElement(By.className("quantity")).getText()) + 1;;
        if (driver.findElements(By.className("options")).size() != 0 ) {
            WebElement optionsSize = driver.findElement(By.name("options[Size]"));
            Select option = new Select(optionsSize);
            option.selectByValue("Small");
        }
        driver.findElement(By.name("add_cart_product")).click();
        wait.until(ExpectedConditions.textToBe(By.className("quantity"), count.toString()));
        Assert.assertTrue(driver.findElement(By.className("quantity")).getText().equals(count.toString()));
    }
}
