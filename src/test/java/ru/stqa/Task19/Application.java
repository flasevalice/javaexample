package ru.stqa.Task19;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.LoginInLitecat;

/*
Переделайте созданный в задании 13 сценарий для добавления товаров в корзину и удаления товаров из корзины, чтобы он использовал многослойную архитектуру.
А именно, выделите вспомогательные классы для работы с главной страницей (откуда выбирается товар),
для работы со страницей товара (откуда происходит добавление товара в корзину),
со страницей корзины (откуда происходит удаление), и реализуйте сценарий,
который не напрямую обращается к операциям Selenium, а оперирует вышеперечисленными объектами-страницами.
*/
//класс, где общие методы и инициализация других страниц
public class Application {
    public WebDriver driver;
    public WebDriverWait wait;

    public MainPage mainPage;
    public ProductPage productPage;
    public CartPage cartPage;

    public Application () {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @After
    public  void stop() {
        driver.quit();
        driver = null;
    }
}
