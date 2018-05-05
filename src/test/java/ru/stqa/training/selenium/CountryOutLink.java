package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.Set;

public class CountryOutLink extends BaseTest {

    @Test
    public void CountryOutLink() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Countries')]")).click();


        driver.findElement(By.cssSelector("a.button")).click();

        OpenCloseNewWindow(driver.findElement(By.xpath("//input[@name = 'iso_code_2']/../a")));
        OpenCloseNewWindow(driver.findElement(By.xpath("//input[@name = 'iso_code_3']/../a")));
        OpenCloseNewWindow(driver.findElement(By.xpath("//input[@name = 'tax_id_format']/../a")));
        OpenCloseNewWindow(driver.findElement(By.xpath("//textarea[@name = 'address_format']/../a[@target = '_blank']")));
        OpenCloseNewWindow(driver.findElement(By.xpath("//input[@name = 'postcode_format']/../a")));
        OpenCloseNewWindow(driver.findElement(By.xpath("//input[@name = 'currency_code']/../a")));
        OpenCloseNewWindow(driver.findElement(By.xpath("//input[@name = 'phone_code']/../a")));


        /*


        driver.findElement(By.xpath("//input[@name = 'iso_code_3']/../a"));

        driver.findElement(By.xpath("//input[@name = 'tax_id_format']/../a"));

        driver.findElement(By.xpath("//input[@name = 'address_format']/../a"));

        driver.findElement(By.xpath("//input[@name = 'postcode_format']/../a"));

        driver.findElement(By.xpath("//input[@name = 'currency_code']/../a"));

        driver.findElement(By.xpath("//input[@name = 'phone_code']/../a"));*/
    }

    public void OpenCloseNewWindow(WebElement element){

        String originalWindow = driver.getWindowHandle();
        Set<String> existingWindows = driver.getWindowHandles();

        element.click();
        String newWindow = wait.until(anyWindowOtherThan(existingWindows));

        driver.switchTo().window(newWindow);
        driver.close();

        driver.switchTo().window(originalWindow);

    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String>oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }



}
