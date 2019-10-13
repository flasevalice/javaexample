package ru.stqa;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/*
Сделайте сценарий для добавления нового товара (продукта) в учебном приложении litecart (в админке).
Для добавления товара нужно открыть меню Catalog, в правом верхнем углу нажать кнопку "Add New Product", заполнить поля с информацией о товаре и сохранить.
Достаточно заполнить только информацию на вкладках General, Information и Prices. Скидки (Campains) на вкладке Prices можно не добавлять.
Переключение между вкладками происходит не мгновенно, поэтому после переключения можно сделать небольшую паузу (о том, как делать более правильные ожидания, будет рассказано в следующих занятиях).
Картинку с изображением товара нужно уложить в репозиторий вместе с кодом. При этом указывать в коде полный абсолютный путь к файлу плохо, на другой машине работать не будет. Надо средствами языка программирования преобразовать относительный путь в абсолютный.
После сохранения товара нужно убедиться, что он появился в каталоге (в админке). Клиентскую часть магазина можно не проверять.
*/
public class Task12 extends LoginInLitecat {

    void sleep(Integer sec) {
        try {
            Thread.sleep(sec);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void addGood() {
        loginInLitecat("http://localhost/litecart/admin/");
        wait.until(titleIs("My Store"));
        driver.findElement(By.cssSelector("a[href*='catalog']")).click();
        wait.until(titleIs("Catalog | My Store"));
        driver.findElement(By.xpath("//*[@id='content']/div[1]/a[2]")).click();
        wait.until(titleIs("Add New Product | My Store"));

        //General
        //Status
        driver.findElement(By.cssSelector("input[name=status][value='1']")).click();
        //Name
        driver.findElement(By.name("name[en]")).sendKeys("Black Duck");
        //Code
        driver.findElement(By.name("code")).sendKeys("2222");
        //Categories
        driver.findElement(By.cssSelector("input[type=checkbox][value='0']")).click();
        driver.findElement(By.cssSelector("input[type=checkbox][value='1']")).click();
        //Gender
        driver.findElement(By.cssSelector("input[type=checkbox][value='1-2']")).click();
        //Quantity
        driver.findElement(By.name("quantity")).sendKeys("10" );
        //Upload file
        File file = new File("./src/test/java/ru/stqa/black_duck.jpg");
        driver.findElement(By.name("new_images[]")).sendKeys(file.getAbsolutePath());

        //Date
        driver.findElement(By.cssSelector("[name='date_valid_from']")).sendKeys("13.10.2019");
        driver.findElement(By.cssSelector("[name='date_valid_to']")).sendKeys("13.10.2020");

        //Information
        driver.findElement(By.xpath("//*[@id='content']/form/div/ul/li[2]/a")).click();
        sleep(2);
        WebElement manufacturerId = driver.findElement(By.name("manufacturer_id"));
        Select manufacturer = new Select (manufacturerId);
        manufacturer.selectByVisibleText("ACME Corp.");
        driver.findElement(By.cssSelector("[name='short_description[en]']")).sendKeys("DESCRIPTION");
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("FULL DESCRIPTION");
        sleep(3);

        //Prices
        driver.findElement(By.xpath("//*[@id='content']/form/div/ul/li[4]/a")).click();
        sleep(3);
        WebElement price = driver.findElement(By.cssSelector("[name='purchase_price']"));
        price.clear();
        price.sendKeys("12");

        WebElement priceCode = driver.findElement(By.name("purchase_price_currency_code"));
        Select priceCodeValue = new Select (priceCode);
        priceCodeValue.selectByValue("USD");

        driver.findElement(By.cssSelector("[name='prices[USD]']")).sendKeys("10");
        driver.findElement(By.cssSelector("[name='prices[EUR]']")).sendKeys("16");
        sleep(3);

        driver.findElement(By.name("save")).click();

        //Проверка добавления
        driver.findElement(By.cssSelector("a[href*='catalog']")).click();
        driver.findElement(By.linkText("Rubber Ducks")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Black Duck")).isDisplayed());
    }
}
