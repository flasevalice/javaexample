package ru.stqa.Task19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CartPage extends Page {

    @FindBy (css = "a[href*='checkout']")
    WebElement checkout;

    @FindBy (className = "shortcut")
    List<WebElement> shortcut;

    @FindBy (className = "shortcut")
    WebElement oneShortcut;

    @FindBy (name = "remove_cart_item")
    WebElement removeCart;

    public CartPage (WebDriver driver) {
        super(driver);
    }

    public void open(){
        checkout.click();
    }

    public void clearCart(){
        int count = shortcut.size();
        for (int i=count; i>1; i--) {
            deleteOneItem();
        }
        removeCart.click();
        sleep(4);
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("table.dataTable"))));
        sleep(4);
    }

    protected void deleteOneItem() {
        oneShortcut.click();
        wait.until(titleIs("Checkout | My store"));
        sleep(4);
        removeCart.click();
        sleep(4);
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//*[@id='order_confirmation-wrapper']/table"))));
    }
}
