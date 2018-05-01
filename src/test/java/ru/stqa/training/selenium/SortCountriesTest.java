package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 *
 * на странице http://localhost/litecart/admin/?app=countries&doc=countries
 * а) проверить, что страны расположены в алфавитном порядке
 * б) для тех стран, у которых количество зон отлично от нуля -- открыть страницу этой страны и там проверить, что зоны расположены в алфавитном порядке
 */


public class SortCountriesTest extends BaseTest {

    @Test
    public void SortCountriesTest(){


        String countryCurrent;
        String countryLast = "A";
        String zoneCurrent;
        String zoneLast;
        Integer countryNumberOfZones;
        int sortResult;


        List<WebElement> rowsCountry;
        List<WebElement> rowsZone;


        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Countries')]")).click();



        rowsCountry =  driver.findElements(By.cssSelector("tr.row"));

        for (int i = 0; i < (rowsCountry.size()); i++ ){

            WebElement rowCountry = rowsCountry.get(i);

            countryCurrent = rowCountry.findElement(By.xpath("./td[5]")).getText();
            countryNumberOfZones = Integer.parseInt(rowCountry.findElement(By.xpath("./td[6]")).getText());

            /** Проверка правильности сортировки. */
            sortResult = countryLast.compareTo(countryCurrent);
            Assert.assertTrue(sortResult <= 0);

            countryLast = countryCurrent;

            if (countryNumberOfZones > 0){
                zoneLast = "A";
                rowCountry.findElement(By.xpath("./td[5]//a")).click();
                rowsZone =  driver.findElements(By.cssSelector(".dataTable tr"));



                for (int row = 1; row < (rowsZone.size() - 1); row++ ){
                    zoneCurrent = rowsZone.get(row).findElement(By.xpath("./td[3]")).getText();
                    int sortZonesResult = zoneLast.compareTo(zoneCurrent);
                    System.out.println("Z" + zoneCurrent + "  " + sortZonesResult);
                    Assert.assertTrue(sortZonesResult <= 0);

                    zoneLast = zoneCurrent;
                }
                driver.findElement(By.cssSelector("[name = cancel]")).click();
                rowsCountry =  driver.findElements(By.cssSelector("tr.row"));
            }
        }

    }
}
