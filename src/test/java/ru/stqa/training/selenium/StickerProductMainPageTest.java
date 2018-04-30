package ru.stqa.training.selenium;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 *
 *Сделайте сценарий, проверяющий наличие стикеров у всех товаров в учебном приложении litecart на главной странице.
 * Стикеры -- это полоски в левом верхнем углу изображения товара, на которых написано New или Sale или что-нибудь другое.
 * Сценарий должен проверять, что у каждого товара имеется ровно один стикер.
 */


public class StickerProductMainPageTest extends BaseTest {

    @Test
    public void StickerProductMainPageTest(){
        driver.get("http://localhost/litecart/en/");

        List <WebElement> ProductList = driver.findElements(By.cssSelector("li.product"));
        List <WebElement> stickersProduct;

        for (WebElement product:ProductList){

            stickersProduct = product.findElements(By.cssSelector("li.product [class ^= sticker]"));
            Assert.assertTrue(stickersProduct.size() == 1);

        }
    }
}