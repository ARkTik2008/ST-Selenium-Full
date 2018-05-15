package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class getBrowserLogs  extends BaseTest{

    @Test
    public void getBrowserLogs(){

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        List<WebElement> listOfProduct = driver.findElements(By.xpath(".//tr/td[3]/a")); //xpath
        List<String> products = new ArrayList<>();

        for (WebElement element : listOfProduct)
        {
            if (!element.getText().equals("Subcategory"))
                products.add(element.getAttribute("textContent"));
        }

        for (String product : products)
        {
            driver.findElement(By.linkText(product)).click();
            Assert.assertTrue(driver.manage().logs().get("browser").getAll().size() == 0);
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        }
    }
}
