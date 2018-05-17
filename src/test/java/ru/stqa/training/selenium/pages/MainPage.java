
package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MainPage extends BasePage
{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open()
    {
        driver.get("http://localhost/litecart/");
        return this;
    }

    public ProductPage chooseProduct()
    {
        driver.findElement(By.xpath("//ul[@class = 'listing-wrapper products']//a[1]")).click();
        return new ProductPage(driver);
    }


}