package ru.stqa.training.selenium;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Scanner;

/**
 *
 * Сделайте сценарий, который проверяет, что при клике на товар открывается правильная страница товара в учебном приложении litecart.
 * Более точно, нужно открыть главную страницу, выбрать первый товар в блоке Campaigns и проверить следующее:

 * а) на главной странице и на странице товара совпадает текст названия товара
 * б) на главной странице и на странице товара совпадают цены (обычная и акционная)
 * в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
 * г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
 *  (цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
 * д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
 */


public class ProductTest extends BaseTest {
    @Test
    public void ProductTest() {


            driver.get("http://localhost/litecart/en/");

            WebElement productMainPage = driver.findElement(By.cssSelector("#box-campaigns li:first-child"));
            String NameMainPage = productMainPage.findElement(By.cssSelector(".name")).getText();


            WebElement CampaignPriceMainPage = productMainPage.findElement(By.cssSelector(".campaign-price"));
            String CampaignPriceValueMainPage = CampaignPriceMainPage.getText();
            String ColorOfCampaignPriceMainPage  = CampaignPriceMainPage.getCssValue("color");
            String FontSizeOfCampaignPriceMainPage  = CampaignPriceMainPage.getCssValue("font-size");
            String FontWeightOfCampaignPriceMainPageAttr  = CampaignPriceMainPage.getCssValue("font-weight");
            String FontWeightOfCampaignPriceMainPageTag  = CampaignPriceMainPage.getTagName();

            WebElement RegularPriceMainPage = productMainPage.findElement(By.cssSelector(".regular-price"));
            String RegularPriceValueMainPage = RegularPriceMainPage.getText();
            String ColorOfRegularPriceMainPage  = RegularPriceMainPage.getCssValue("color");
            String FontSizeOfRegularPriceMainPage  = RegularPriceMainPage.getCssValue("font-size");
            String TextDecorationLineOfRegularPriceMainPageAttr  = RegularPriceMainPage.getCssValue("text-decoration-line");//line-through
            String TextDecorationLineOfRegularPriceMainPageTag = RegularPriceMainPage.getTagName();



        productMainPage.click();
            String NameProductPage = driver.findElement(By.cssSelector("h1")).getText();

            WebElement CampaignPriceProductPage = driver.findElement(By.cssSelector(".campaign-price"));
            String CampaignPriceValueProductPage = CampaignPriceProductPage.getText();
            String ColorOfCampaignPriceProductPage  = CampaignPriceProductPage.getCssValue("color");
            String FontSizeOfCampaignPriceProductPage  = CampaignPriceProductPage.getCssValue("font-size");
            String FontWeightOfCampaignPriceProductPage  = CampaignPriceProductPage.getCssValue("font-weight");
            String FontWeightOfCampaignPriceProductPageTag  = CampaignPriceProductPage.getTagName();

            WebElement RegularPriceProductPage = driver.findElement(By.cssSelector(".regular-price"));
            String RegularPriceValueProductPage = RegularPriceProductPage.getText();
            String ColorOfRegularPriceProductPage  = RegularPriceProductPage.getCssValue("color");
            String FontSizeOfRegularPriceProductPage  = RegularPriceProductPage.getCssValue("font-size");
            String TextDecorationLineOfRegularPriceProductPageAttr  = RegularPriceProductPage.getCssValue("text-decoration-line");//line-through
            String TextDecorationLineOfRegularPriceProductPageTag = RegularPriceProductPage.getTagName();


            String[] colorRegularMainPage = ColorOfRegularPriceMainPage.split("[^0-9]+");
            String[] colorRegularProductPage = ColorOfRegularPriceProductPage.split("[^0-9]+");
            String[] colorCampaignMainPage = ColorOfCampaignPriceMainPage.split("[^0-9]+");
            String[] colorCampaignProductPage = ColorOfCampaignPriceProductPage.split("[^0-9]+");

            String delimFont = "px";
            String[] fontSizeRegularMainPage = FontSizeOfRegularPriceMainPage.split(delimFont);
            String[] fontSizeRegularProductPage = FontSizeOfRegularPriceProductPage.split(delimFont);
            String[] fontSizeCampaignMainPage = FontSizeOfCampaignPriceMainPage.split(delimFont);
            String[] fontSizeCampaignProductPage = FontSizeOfCampaignPriceProductPage.split(delimFont);

            /**Проверка названий продуктов*/

            Assert.assertTrue(NameMainPage.equals(NameProductPage));
            /**Проверка цен*/
            Assert.assertTrue(RegularPriceValueMainPage.equals(RegularPriceValueProductPage));
            Assert.assertTrue(CampaignPriceValueMainPage.equals(CampaignPriceValueProductPage));
            /**Проверка шрифтов */
            Assert.assertTrue(Double.parseDouble(fontSizeCampaignMainPage[0]) > Double.parseDouble(fontSizeRegularMainPage[0]));
            Assert.assertTrue(Double.parseDouble(fontSizeCampaignProductPage[0]) > Double.parseDouble(fontSizeRegularProductPage[0]));

            /**Проверка стиля базовой цены*/
            Assert.assertTrue(TextDecorationLineOfRegularPriceMainPageAttr.equals("line-through") || TextDecorationLineOfRegularPriceMainPageTag.equals("s"));
            Assert.assertTrue(TextDecorationLineOfRegularPriceProductPageAttr.equals("line-through") || TextDecorationLineOfRegularPriceProductPageTag.equals("s"));
            Assert.assertTrue(colorRegularMainPage[1].equals(colorRegularMainPage[2]) && colorRegularMainPage[1].equals(colorRegularMainPage[3]));
            Assert.assertTrue(colorRegularProductPage[1].equals(colorRegularProductPage[2]) && colorRegularProductPage[1].equals(colorRegularProductPage[3]));

            /**Проверка стиля акционной цены*/
            Assert.assertTrue(Integer.parseInt(FontWeightOfCampaignPriceMainPageAttr) > 500 || FontWeightOfCampaignPriceMainPageTag.equals("strong"));
            Assert.assertTrue(Integer.parseInt(FontWeightOfCampaignPriceProductPage) > 500  || FontWeightOfCampaignPriceProductPageTag.equals("strong"));
            Assert.assertTrue(!(colorCampaignProductPage[1].equals("0")) && colorCampaignProductPage[2].equals("0") && colorCampaignProductPage[3].equals("0") );
            Assert.assertTrue(!(colorCampaignMainPage[1].equals("0")) && colorCampaignMainPage[2].equals("0") && colorCampaignMainPage[3].equals("0") );

    }
    }
