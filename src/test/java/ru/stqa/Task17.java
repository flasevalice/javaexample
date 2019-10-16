package ru.stqa;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/*
Сделайте сценарий, который проверяет, не появляются ли в логе браузера сообщения при открытии страниц в учебном приложении,
а именно -- страниц товаров в каталоге в административной панели.
Сценарий должен состоять из следующих частей:
1) зайти в админку
2) открыть каталог, категорию, которая содержит товары (страница http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1)
3) последовательно открывать страницы товаров и проверять, не появляются ли в логе браузера сообщения (любого уровня)
*/
public class Task17 extends LoginInLitecat {
    @Test
    void test() {
        loginInLitecat("http://localhost/litecart/admin/");
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        wait.until(titleIs("Catalog | My Store"));
        List<WebElement> products = driver.findElements(By.cssSelector("a[href*='product_id']"));
        List<String> productNames = new ArrayList<String>();
        for(int i = 0; i < products.size(); i+=2){
            productNames.add(products.get(i).getText());
        }

        for(String productName : productNames){
            driver.findElement(By.linkText(productName)).click();
            driver.findElement(By.name("cancel")).click();
        }

        LogEntries logs = driver.manage().logs().get("browser");
        for (LogEntry entry : logs) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
    }
}
