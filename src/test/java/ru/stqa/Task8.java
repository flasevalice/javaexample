package ru.stqa;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Task8 extends LoginInLitecat {

    @Test
    public void checkStickers() {
        driver.get("http://localhost/litecart/en/");
        List<WebElement> products = driver.findElements(By.xpath(".//div[@class='image-wrapper']"));
        for (WebElement e : products) {
            if (e.findElements(By.className("sticker")).size() == 1) {
                System.out.println("стикер один");
            }
        }
    }
}

