package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.Random;


public class AddProductTest extends BaseTest {


    @Test
    public void AddProductTest(){


        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
        driver.findElement(By.xpath("//a[contains(.,'Add New Product')]")).click();

        WebElement radioEnabled = driver.findElement(By.cssSelector("[type = radio][value = '1']"));
        radioEnabled.click();

        Random rand = new Random();
        int  n = Math.abs(rand.nextInt());

        WebElement nameEn = driver.findElement(By.cssSelector("[name = 'name[en]' ]"));
        String testProductName = "TestProduct" + n;
        nameEn.sendKeys(testProductName);

        WebElement code = driver.findElement(By.cssSelector("[name = code ]"));
        code.sendKeys("" + n);

        WebElement chckBxUnisex = driver.findElement(By.cssSelector("[value = '1-3']"));
        chckBxUnisex.click();

        WebElement quantity = driver.findElement(By.cssSelector("[name = quantity ]"));
        quantity.clear();
        quantity.sendKeys("1");

        // add a image of new product with absolute path
        File duckPic = new File("src/test/java/diver duck.jpg");
        String filepath = duckPic.getAbsolutePath();
        driver.findElement(By.cssSelector("[name='new_images[]']")).sendKeys(filepath);
        driver.findElement(By.cssSelector("#add-new-image")).click();

        WebElement dateFrom = driver.findElement(By.cssSelector("[name =  date_valid_from ]"));
        dateFrom.sendKeys("01012018");

        WebElement dateTo = driver.findElement(By.cssSelector("[name =  date_valid_to ]"));
        dateTo.sendKeys("01012019");

        driver.findElement(By.xpath("//a[contains(.,'Information')]")).click();

        try {
            Thread.sleep(1000);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        Select manufacture = new Select(driver.findElement(By.name("manufacturer_id")));
        manufacture.selectByValue("1");

        WebElement keyword = driver.findElement(By.name("keywords"));
        keyword.sendKeys("TestKeywords");


        WebElement shortDescription = driver.findElement(By.name("short_description[en]"));
        shortDescription.sendKeys("TestShortDescription");

        WebElement description = driver.findElement(By.className("trumbowyg-editor"));//driver.findElement(By.name("description[en]"));
        description.sendKeys("TestDescription");

        WebElement headTitle = driver.findElement(By.name("head_title[en]"));
        headTitle.sendKeys("Test");

        WebElement metaDescription = driver.findElement(By.name("meta_description[en]"));
        metaDescription.sendKeys("TestDescription");


        driver.findElement(By.xpath("//a[contains(.,'Prices')]")).click();

        try {
            Thread.sleep(1000);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        WebElement purchasePrice = driver.findElement(By.name("purchase_price"));
        purchasePrice.clear();
        purchasePrice.sendKeys("1");
        //purchase_price_currency_code

        Select purchasePriceCurrencyCode = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        purchasePriceCurrencyCode.selectByValue("USD");


        WebElement pricesUSD = driver.findElement(By.name("prices[USD]"));
        pricesUSD.sendKeys("123");


        WebElement pricesEUR = driver.findElement(By.name("prices[EUR]"));
        pricesEUR.sendKeys("123");

        driver.findElement(By.name("save")).click();

        String xpathTestProduct = "//a[contains(.,'" + testProductName + "')]";

        WebElement testProduct = driver.findElement(By.xpath(xpathTestProduct));
        String testProductNameResult = testProduct.getText();
        Assert.assertTrue(testProductNameResult.equals(testProductName));

    }

}
