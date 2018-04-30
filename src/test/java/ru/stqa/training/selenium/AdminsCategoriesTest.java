package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
*
*   Сделайте сценарий, который выполняет следующие действия в учебном приложении litecart.
*
*   1) входит в панель администратора http://localhost/litecart/admin
*   2) прокликивает последовательно все пункты меню слева, включая вложенные пункты
*   3) для каждой страницы проверяет наличие заголовка (то есть элемента с тегом h1)
*/

public class AdminsCategoriesTest extends BaseTest {

    @Test
    public void AdminsCategoriesTest(){

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> AdminCategoriesList;
        List<WebElement> AdminCategoriesChildsList;

        AdminCategoriesList = driver.findElements(By.cssSelector("li#app-"));

        for (int i = 0; i < AdminCategoriesList.size(); i++){


            AdminCategoriesList.get(i).click();
            Assert.assertTrue(isElementPresent(driver, By.cssSelector("h1")));

            AdminCategoriesChildsList = driver.findElements(By.cssSelector("li[id^=doc]"));

            for (int j = 1; j < AdminCategoriesChildsList.size(); j++){
                AdminCategoriesChildsList.get(j).click();
                Assert.assertTrue(isElementPresent(driver, By.cssSelector("h1")));

                AdminCategoriesChildsList =driver.findElements(By.cssSelector("li[id^=doc]"));
            }

            AdminCategoriesList = driver.findElements(By.cssSelector("li#app-"));
        }


    }

}
