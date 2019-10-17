package ru.stqa.Task19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends Page {

    public MainPage (WebDriver driver) {
        super(driver);
    }
    @Override
    public void open(){
        driver.get("http://litecart.stqa.ru/ru/");
    }

    public void chooseFirstItem() {
        driver.findElement(By.className("product")).click();
    }
}
