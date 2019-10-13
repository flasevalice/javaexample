package ru.stqa;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/*
Сделайте сценарий для добавления нового товара (продукта) в учебном приложении litecart (в админке).
Для добавления товара нужно открыть меню Catalog, в правом верхнем углу нажать кнопку "Add New Product", заполнить поля с информацией о товаре и сохранить.
Достаточно заполнить только информацию на вкладках General, Information и Prices. Скидки (Campains) на вкладке Prices можно не добавлять.
Переключение между вкладками происходит не мгновенно, поэтому после переключения можно сделать небольшую паузу (о том, как делать более правильные ожидания, будет рассказано в следующих занятиях).
Картинку с изображением товара нужно уложить в репозиторий вместе с кодом. При этом указывать в коде полный абсолютный путь к файлу плохо, на другой машине работать не будет. Надо средствами языка программирования преобразовать относительный путь в абсолютный.
После сохранения товара нужно убедиться, что он появился в каталоге (в админке). Клиентскую часть магазина можно не проверять.
Можно оформить сценарий либо как тест, либо как отдельный исполняемый файл.
*/
public class Task12 extends LoginInLitecat {
    @Test
    public void addGood() {
        driver.get("http://localhost/litecart/admin/");
        wait.until(titleIs("My Store"));
        driver.findElement(By.xpath("//*[@id='app-']/a/span[2]")).click();
        wait.until(titleIs("Catalog | My Store"));
        driver.findElement(By.xpath("//*[@id='content']/div[1]/a[2]")).click();
        wait.until(titleIs("Add New Product | My Store"));

        //General
        //Status
        driver.findElement(By.cssSelector("input[name=status][value='1']")).click();
        //Name
        driver.findElement(By.name("name[en]")).sendKeys("Black Duck");
        //Code
        driver.findElement(By.name("code")).sendKeys("2222");
        //Categories
        driver.findElement(By.cssSelector("input[type=checkbox][value='0']")).click();
        driver.findElement(By.cssSelector("input[type=checkbox][value='1']")).click();
        //Gender
        driver.findElement(By.cssSelector("input[type=checkbox][value='1-2']")).click();
        //Quantity
        driver.findElement(By.name("quantity")).sendKeys("100" );
     //   driver.findElement(By.name("quantity")).sendKeys("50");
        //Upload file
   //     driver.findElement(By.name("new_images[]")). sendKeys(path);


        driver.findElement(By.name("save")).click();

        //Information
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/ul/li[2]/a")).click();
     //   Thread.sleep(1000);

    }
}
