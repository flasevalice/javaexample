package ru.stqa.Task19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {
    @FindBy(className = "product")
    WebElement product;

    public MainPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @Override
    public void open(){
        driver.get("http://litecart.stqa.ru/ru/");
    }

    public void chooseFirstItem() {
        product.click();
    }
}
