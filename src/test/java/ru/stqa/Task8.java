package ru.stqa;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/*Сделайте сценарий, проверяющий наличие стикеров у всех товаров в учебном приложении litecart на главной странице.
Стикеры -- это полоски в левом верхнем углу изображения товара, на которых написано New или Sale или что-нибудь другое.
Сценарий должен проверять, что у каждого товара имеется ровно один стикер.*/
public class Task8 extends LoginInLitecat {

    @Test
    public void checkStickers() {
        driver.get("http://localhost/litecart/en/");

        String[] boxType = {"box-most-popular", "box-campaigns", "box-latest-products"};
        for (String box : boxType) {
            System.out.println("Категория товаров: " + box.replace("-"," "));
            List<WebElement> locator = driver.findElements(By.xpath("//*[@id='" + box + "']/div/ul/li[contains(@class, 'product')]"));

            for (WebElement e : locator) {
                if (e.findElements(By.className("sticker")).size() == 1) {
                    System.out.println("стикер один");
                } else {
                    System.out.println("стикер не является единственным");
                }
            }
        }
    }
}

