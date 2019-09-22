package ru.stqa;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class Task7 extends LoginInLitecat {

    @Test
    public void checkRegistry() {
        int sizeMenu = driver.findElements(By.xpath("//ul[@class='list-vertical']//li/a[count(a)=0]")).size();
        for (int i=0; i<sizeMenu ; i++) {

            String mainMenuItem = driver.findElements(By.xpath("//ul[@class='list-vertical']/li/a")).get(i).getText();
            wait.until(ExpectedConditions.elementToBeClickable(
                    driver.findElements(By.xpath("//ul[@class='list-vertical']/li/a")).get(i))).click();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("h1"))));
            System.out.println(mainMenuItem);

            int sizeInnerMenu = driver.findElements(By.xpath("//ul[@class='docs']/li/a[count(a)=0]")).size();
            if (!driver.findElements(By.xpath("//ul[@class='list-vertical']/li/a")).isEmpty()) {
                for (int j = 0; j < sizeInnerMenu; j++) {
                    String innerMenuItem = driver.findElements(By.xpath("//ul[@class='docs']/li/a")).get(j).getText();
                    wait.until(ExpectedConditions.elementToBeClickable(
                            driver.findElements(By.xpath("//ul[@class='docs']/li/a")).get(j))).click();
                    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("h1"))));
                    System.out.println("--"+innerMenuItem);
                }
            }
        }
    }
}
