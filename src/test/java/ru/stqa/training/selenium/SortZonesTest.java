package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 *
 * на странице http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones
 * зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке
 */
public class SortZonesTest extends BaseTest{

    @Test
    public void SortZonesTest(){
        List <WebElement> rowsCountry;
        List <WebElement> rowsZone;

        String zoneLast;
        String zoneCurrent;

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Geo Zones')]")).click();

        rowsCountry =  driver.findElements(By.cssSelector("tr.row"));

        for (int i = 0; i < (rowsCountry.size()); i++ ){
            WebElement rowCountry = rowsCountry.get(i);
            rowCountry.findElement(By.xpath("./td[3]//a")).click();
            zoneLast = "A";
            rowsZone =  driver.findElements(By.cssSelector(".dataTable tr"));
            for (int row = 1; row < (rowsZone.size() - 1); row++ ){
                WebElement rowZone = rowsZone.get(row);
                zoneCurrent = new Select(rowZone.findElement(By.xpath("./td[3]/select"))).getFirstSelectedOption().getText();

                int sortZonesResult = zoneLast.compareTo(zoneCurrent);
                Assert.assertTrue(sortZonesResult <= 0);

                zoneLast = zoneCurrent;
            }
            driver.findElement(By.cssSelector("[name = cancel]")).click();
            rowsCountry =  driver.findElements(By.cssSelector("tr.row"));
        }
    }
}
