package ru.stqa;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
* Сделайте сценарии, которые проверяют сортировку стран и геозон (штатов) в учебном приложении litecart.

1) на странице http://localhost/litecart/admin/?app=countries&doc=countries
а) проверить, что страны расположены в алфавитном порядке
б) для тех стран, у которых количество зон отлично от нуля
-- открыть страницу этой страны и там проверить, что зоны расположены в алфавитном порядке

2) на странице http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones
зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке
*/
public class Task9 extends LoginInLitecat {

    public void checkSort(List<WebElement> elementList) {
        //инициализация из начального списка строк
        List<String> obtainedList = new ArrayList<String>();

        //добавляем текст элементов в список
        for(WebElement we:elementList){
            obtainedList.add(we.getText());
        }
        System.out.println("Изначальный список:" + obtainedList);

        //инициализаация нового списка
        List<String> sortedList = new ArrayList<String>();
        for(String s:obtainedList){
            sortedList.add(s);
        }
        System.out.println("Отсортированный список:" + sortedList);
        Collections.sort(sortedList);
        Assert.assertTrue(sortedList.equals(obtainedList));
    }

    void checkZonesForCountries(List<String> links) {

        for (String link : links) {
            System.out.println("Текущая страница: "+ link);
            driver.get(link);
            List<WebElement> zone = driver.findElements(By.xpath("//*[@id='table-zones']/tbody/tr/td[3]"));
            for (int i=0;i<zone.size();i++) {
                if (zone.get(i).getText().equals("")) {
                    zone.remove(i);
                }
            }
            checkSort(zone);
        }
    }

    void checkZonesForGeoZones(List<String> links) {

        for (String link : links) {
            System.out.println("Текущая страница: "+ link);
            driver.get(link);
            List<WebElement> zone = driver.findElements(By.xpath("//table[contains(@class, 'dataTable')]/tbody/tr/td[3]/select/option[@selected='selected']"));
            checkSort(zone);
        }
    }

    @Test
    public void checkCountries() {
        loginInLitecat("http://localhost/litecart/admin/");
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        //список элементов стран
        List<WebElement> countriesList= driver.findElements(By.xpath("//table[contains(@class, 'dataTable')]/tbody/tr/td[5]"));;
        //сортировка
        checkSort(countriesList);

        //список элементов зон
        List<WebElement> zones = driver.findElements(By.xpath("//table[contains(@class, 'dataTable')]/tbody/tr/td[6]"));
        List<String> links = new ArrayList<String>();

        for (int i = 0; i < zones.size(); i++) {
            if (!zones.get(i).getText().equals("0")) {
                String link = countriesList.get(i).findElement(By.tagName("a")).getAttribute("href");
                links.add(link);
                System.out.println(link);
            }
        }
        checkZonesForCountries(links);
    }

    @Test
    public void checkGeoZones() {
        loginInLitecat("http://localhost/litecart/admin/");
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        List<WebElement> countries = driver.findElements(By.xpath("//table[contains(@class, 'dataTable')]/tbody/tr/td[3]/a"));
        List<String> links = new ArrayList<String>();
        for (int i = 0; i < countries.size(); i++) {
            String link = countries.get(i).getAttribute("href");
            links.add(link);
        }
        checkZonesForGeoZones(links);
    }

}
