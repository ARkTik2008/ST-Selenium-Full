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

        List<WebElement> externalLinks = driver.findElements(By.cssSelector("form a[target = _blank]"));

        for (WebElement externalLink : externalLinks) {
            OpenCloseNewWindow(externalLink);
        }

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
