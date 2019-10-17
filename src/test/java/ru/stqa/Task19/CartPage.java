package ru.stqa.Task19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CartPage extends Page {

    public CartPage (WebDriver driver) {
        super(driver);
    }

    public void open(){
        driver.findElement(By.cssSelector("a[href*='checkout']")).click();
    }

    public void clearCart(){
        int count = driver.findElements(By.className("shortcut")).size();
        for (int i=count; i>1; i--) {
            deleteOneItem();
        }
        driver.findElement(By.name("remove_cart_item")).click();
        sleep(4);
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("table.dataTable"))));
        sleep(4);
    }

    protected void deleteOneItem() {
        driver.findElement(By.className("shortcut")).click();
        wait.until(titleIs("Checkout | My store"));
        sleep(4);
        driver.findElement(By.name("remove_cart_item")).click();
        sleep(4);
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//*[@id='order_confirmation-wrapper']/table"))));
    }
}
