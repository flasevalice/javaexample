package ru.stqa;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/*Задание 10. Проверить, что открывается правильная страница товара
Сделайте сценарий, который проверяет, что при клике на товар открывается правильная страница товара в учебном приложении litecart.

Более точно, нужно открыть главную страницу, выбрать первый товар в блоке Campaigns и проверить следующее:

а) на главной странице и на странице товара совпадает текст названия товара
б) на главной странице и на странице товара совпадают цены (обычная и акционная)
в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
(цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)

Необходимо убедиться, что тесты работают в разных браузерах, желательно проверить во всех трёх ключевых браузерах (Chrome, Firefox, IE).
*/
public class Task10 extends LoginInLitecat {

    public void isGrey(String color) {
        String[] isGrey = color.replaceAll("[a-zA-Z()]", "").split(", ");
        Assert.assertEquals("Цвет не совпадает", isGrey[0], isGrey[1]);
        Assert.assertEquals("Цвет не совпадает", isGrey[0], isGrey[2]);
        Assert.assertEquals("Цвет не совпадает", isGrey[1], isGrey[2]);
    }

    public void isRed(String color) {
        String[] isRed = color.replaceAll("[a-zA-Z()]", "").split(", ");
        Assert.assertTrue("Цвет не совпадает", Integer.parseInt(isRed[0]) != 0);
        Assert.assertTrue("Цвет не совпадает", Integer.parseInt(isRed[1]) == 0);
        Assert.assertTrue("Цвет не совпадает", Integer.parseInt(isRed[2]) == 0);
    }

    @Test
    public void checkPage() {

        driver.get("http://localhost/litecart/en/");
        //сам товар на главной
        WebElement firstGoodInBoxCampaigns = driver.findElement(By.xpath("//*[@id='box-campaigns']/div/ul/li[1]"));
        //имя товара на главной
        String goodName = firstGoodInBoxCampaigns.findElement(By.className("name")).getAttribute("textContent");
        //обычная цена товара на главной
        String goodRegularPrice = firstGoodInBoxCampaigns.findElement(By.className("regular-price")).getAttribute("textContent");
        //скидочная цена товара на главной
        String goodCampaignPrice = firstGoodInBoxCampaigns.findElement(By.className("campaign-price")).getAttribute("textContent");

        String goodRegularPriceSize = firstGoodInBoxCampaigns.findElement(By.className("regular-price")).getCssValue("font-size").replace("px", "");
        String goodCampaignPriceSize = firstGoodInBoxCampaigns.findElement(By.className("campaign-price")).getCssValue("font-size").replace("px", "");
        //в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
        if (!(driver instanceof FirefoxDriver)) {
            Assert.assertTrue("обычная цена не зачеркнутая", firstGoodInBoxCampaigns.findElement(By.className("regular-price")).getTagName().equals("s"));
            isGrey(firstGoodInBoxCampaigns.findElement(By.className("regular-price")).getCssValue("color"));
        } else {
            Assert.assertTrue("обычная цена не зачеркнутая и не серая для страницы", firstGoodInBoxCampaigns.findElement(By.className("regular-price")).getTagName().equals("s"));
            isGrey(firstGoodInBoxCampaigns.findElement(By.className("regular-price")).getCssValue("color"));
        }

        // г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
        //(цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
        if (!(driver instanceof FirefoxDriver)) {
            Assert.assertTrue("акционная цена не жирная и не красная для страницы", firstGoodInBoxCampaigns.findElement(By.className("campaign-price")).getTagName().equals("strong"));
            isRed(firstGoodInBoxCampaigns.findElement(By.className("campaign-price")).getCssValue("color"));
        } else {
            Assert.assertTrue("акционная цена не жирная и не красная для страницы", firstGoodInBoxCampaigns.findElement(By.className("campaign-price")).getTagName().equals("strong"));
            isRed(firstGoodInBoxCampaigns.findElement(By.className("campaign-price")).getCssValue("color"));
        }
        //д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
        Assert.assertTrue(Float.valueOf(goodCampaignPriceSize) > Float.valueOf(goodRegularPriceSize));
        firstGoodInBoxCampaigns.click();
        //сам товар в карточке
        WebElement cardOfGood = driver.findElement(By.xpath("//*[@id='box-product']"));
        //имя товара на странице товара
        String goodNameCard = cardOfGood.findElement(By.className("title")).getAttribute("textContent");
        //String goodNameCard = cardOfGood.findElement(By.xpath("//*[@id='box-product']/div[1]/h1")));
        //обычная цена товара на странице товара
        String goodRegularPriceCard = cardOfGood.findElement(By.className("regular-price")).getAttribute("textContent");
        //скидочная цена товара на странице товара
        String goodCampaignPriceCard = driver.findElement(By.className("campaign-price")).getAttribute("textContent");

        String goodRegularPriceSizeCard = cardOfGood.findElement(By.className("regular-price")).getCssValue("font-size").replace("px", "");
        String goodCampaignPriceSizeCard = cardOfGood.findElement(By.className("campaign-price")).getCssValue("font-size").replace("px", "");

        if (driver.getTitle().startsWith(goodNameCard)) {
            //а) на главной странице и на странице товара совпадает текст названия товара
            Assert.assertTrue("не совпадает текст названия товара", goodName.equals(goodNameCard));
            //б) на главной странице и на странице товара совпадают цены (обычная и акционная)
            Assert.assertTrue("не совпадает обычная цена", goodRegularPrice.equals(goodRegularPriceCard));
            Assert.assertTrue("не совпадает акционная цена", goodCampaignPrice.equals(goodCampaignPriceCard));

            //в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
            if (!(driver instanceof FirefoxDriver)) {
                Assert.assertTrue("обычная цена не зачеркнутая и не серая для карточки", cardOfGood.findElement(By.className("regular-price")).getTagName().equals("s"));
                isGrey(cardOfGood.findElement(By.className("regular-price")).getCssValue("color"));
            } else {
                Assert.assertTrue("обычная цена не зачеркнутая и не серая для карточки", cardOfGood.findElement(By.className("regular-price")).getTagName().equals("s"));
                isGrey(cardOfGood.findElement(By.className("regular-price")).getCssValue("color"));
            }
            //г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
            //(цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
            if (!(driver instanceof FirefoxDriver)) {
                Assert.assertTrue("акционная цена не жирная и не красная для карточки", cardOfGood.findElement(By.className("campaign-price")).getTagName().equals("strong"));
                isGrey(cardOfGood.findElement(By.className("regular-price")).getCssValue("color"));
            } else {
                Assert.assertTrue("акционная цена не жирная и не красная для карточки", cardOfGood.findElement(By.className("campaign-price")).getTagName().equals("strong"));
                isGrey(cardOfGood.findElement(By.className("regular-price")).getCssValue("color"));
            }

            //д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
            Assert.assertTrue(Float.valueOf(goodCampaignPriceSizeCard) > Float.valueOf(goodRegularPriceSizeCard));
        }

    }

}
