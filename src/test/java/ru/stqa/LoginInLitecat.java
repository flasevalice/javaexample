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

public class LoginInLitecat {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void loginInLitecat() {
        try {
            driver.get("http://localhost/litecart/admin/");

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("username")))).sendKeys("admin");
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("password")))).sendKeys("admin");
            driver.findElement(By.name("login")).click();
            wait.until(titleIs("My Store"));
        } catch (Exception e) {
            System.err.print("Error test");
            stop();
        }
    }

    @AfterEach
    public  void stop() {
        driver.quit();
        driver = null;
    }
}