package ru.stqa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void  myFirstTest() {
        driver.get("http://www.google.com/");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("q")))).sendKeys("webdriver");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("btnK")))).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    @AfterEach
    public  void stop() {
       driver.quit();
       driver = null;
    }
}
