package ru.stqa;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/*
* Сделайте сценарий для добавления товаров в корзину и удаления товаров из корзины.

1) открыть главную страницу
2) открыть первый товар из списка
2) добавить его в корзину (при этом может случайно добавиться товар, который там уже есть, ничего страшного)
3) подождать, пока счётчик товаров в корзине обновится
4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара
5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица
*/
public class Task13 extends LoginInLitecat {

    public void sleep(Integer sec) {
        try {
            Thread.sleep(sec);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public  void basket() {
        driver.get("http://litecart.stqa.ru/ru/");
        //Добавление товаров в корзину
        for (int i = 1; i<=3; i++) {
            driver.findElement(By.className("product")).click();
            Integer count = Integer.parseInt(driver.findElement(By.className("quantity")).getText()) + 1;
            //Size
            if (driver.findElements(By.className("options")).size() != 0) {
                WebElement optionsSize = driver.findElement(By.name("options[Size]"));
                Select option = new Select(optionsSize);
                option.selectByValue("Small");
            }
            driver.findElement(By.name("add_cart_product")).click();
            wait.until(ExpectedConditions.textToBe(By.className("quantity"), count.toString()));
            Assert.assertTrue(driver.findElement(By.className("quantity")).getText().equals(count.toString()));

            driver.findElement(By.cssSelector("a[href*='/']")).click();
        }
        //Удаление товаров из корзины
        driver.findElement(By.cssSelector("a[href*='checkout']")).click();
        wait.until(titleIs("Checkout | My store"));
        int count = driver.findElements(By.className("shortcut")).size();
        for (int i=count; i>1; i--) {
            driver.findElement(By.className("shortcut")).click();
            sleep(4);
            driver.findElement(By.name("remove_cart_item")).click();
            sleep(4);
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.xpath("//*[@id='order_confirmation-wrapper']/table/tbody/tr[2]/td[1]"), i));
            sleep(4);
        }
        driver.findElement(By.name("remove_cart_item")).click();
        sleep(4);
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//*[@id='order_confirmation-wrapper']/table"))));
    }
}
