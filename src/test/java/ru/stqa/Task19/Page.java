package ru.stqa.Task19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private int timeout = 3;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void open(By selector){
        driver.findElement(selector).click();
    }

    public void open(){}

    public void sleep(Integer sec) {
        try {
            Thread.sleep(sec);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }


    protected void choiseFromSelect(By locator, int size, boolean isFirst) {
        Select menu = new Select(driver.findElement(locator));
        int index = (int)(Math.random()*size);
        if (!isFirst && index==0) index++;
        menu.selectByIndex(index);
    }

    public void goToMainPage() {
        driver.findElement(By.cssSelector("a[href*='/']")).click();
    }
}
