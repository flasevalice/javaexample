package ru.stqa;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/*
* Сделайте сценарий для регистрации нового пользователя в учебном приложении litecart (не в админке, а в клиентской части магазина).

Сценарий должен состоять из следующих частей:

1) регистрация новой учётной записи с достаточно уникальным адресом электронной почты (чтобы не конфликтовало с ранее созданными пользователями, в том числе при предыдущих запусках того же самого сценария),
2) выход (logout), потому что после успешной регистрации автоматически происходит вход,
3) повторный вход в только что созданную учётную запись,
4) и ещё раз выход.

В качестве страны выбирайте United States, штат произвольный. При этом формат индекса -- пять цифр.
Проверки можно никакие не делать, только действия -- заполнение полей, нажатия на кнопки и ссылки. Если сценарий дошёл до конца, то есть созданный пользователь смог выполнить вход и выход -- значит создание прошло успешно.
*/
public class Task11 extends LoginInLitecat {
    private Integer count = 0;

    @Test
    public void createAccount() {
        driver.get("http://litecart.stqa.ru/ru/");
        String emailStr = "test"+ ThreadLocalRandom.current().nextInt(1, 1000 + 1) +"@mail.ru";
        driver.findElement(By.xpath("//*[@id='box-account-login']/div/form/table/tbody/tr[5]/td/a")).click();
       // driver.getTitle();
        wait.until(ExpectedConditions.titleIs("Create Account | My store"));

        driver.findElement(By.name("tax_id")).sendKeys(count.toString());
        count++;
        driver.findElement(By.name("company")).sendKeys("Company");
        driver.findElement(By.name("firstname")).sendKeys("Firstname");
        driver.findElement(By.name("lastname")).sendKeys("Lastname");
        driver.findElement(By.name("address1")).sendKeys("Address");
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("city")).sendKeys("New York");

        WebElement countries = driver.findElement(By.name("country_code"));
        Select country = new Select (countries);
        country.selectByVisibleText("United States");

        wait.until(ExpectedConditions.elementToBeClickable( driver.findElement(By.xpath("//*[@id='create-account']/div/form/table/tbody/tr[5]/td[2]/select"))));
        driver.findElement(By.xpath("//*[@id='create-account']/div/form/table/tbody/tr[5]/td[2]/select/option[2]")).click();
        driver.findElement(By.name("email")).sendKeys(emailStr);
        driver.findElement(By.name("phone")).sendKeys("+12345678900");
        driver.findElement(By.name("password")).sendKeys("test1");
        driver.findElement(By.name("confirmed_password")).sendKeys("test1");

        driver.findElement(By.name("create_account")).click();
        wait.until(ExpectedConditions.titleIs("Online Store | My store"));
        logout();
        //login
        driver.findElement(By.name("email")).sendKeys(emailStr);
        driver.findElement(By.name("password")).sendKeys("test1");
        driver.findElement(By.name("login")).click();

        logout();
    }

    @Test
    public void logout() {
        driver.findElement(By.xpath("//*[@id='box-account']/div/ul/li[4]/a")).click();
    }
}
