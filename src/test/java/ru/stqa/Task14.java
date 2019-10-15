package ru.stqa;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/*
* Сделайте сценарий, который проверяет, что ссылки на странице редактирования страны открываются в новом окне.

Сценарий должен состоять из следующих частей:

1) зайти в админку
2) открыть пункт меню Countries (или страницу http://localhost/litecart/admin/?app=countries&doc=countries)
3) открыть на редактирование какую-нибудь страну или начать создание новой
4) возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой -- они ведут на внешние страницы и открываются в новом окне, именно это и нужно проверить.

Конечно, можно просто убедиться в том, что у ссылки есть атрибут target="_blank". Но в этом упражнении требуется именно кликнуть по ссылке, чтобы она открылась в новом окне, потом переключиться в новое окно, закрыть его, вернуться обратно, и повторить эти действия для всех таких ссылок.

Не забудьте, что новое окно открывается не мгновенно, поэтому требуется ожидание открытия окна.
*/
public class Task14 extends LoginInLitecat {
    @Test
    public void test() {
        loginInLitecat("http://localhost/litecart/admin/");
        driver.findElement(By.cssSelector("a[href*='countries']")).click();
        wait.until(titleIs("Countries | My Store"));
        driver.findElement(By.cssSelector("a[href*='edit_country']")).click();

        String existingWindow = driver.getWindowHandle(); //текущее окно
        System.out.println(existingWindow);

        List<WebElement> links = driver.findElements(By.className("fa-external-link")); //ссылки с иконкой в виде квадратика со стрелкой
        for(int i = 0; i < links.size(); i++){
            links.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> windows = driver.getWindowHandles();//список всех открытых окон
            windows.remove(existingWindow);
            String newWindow = windows.iterator().next();
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(existingWindow);
        }

    }

}
