package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class AddingProductToCart extends BaseTest {


    @Test
    public void AddingProductToCart() {

        driver.get("http://localhost/litecart/en/");

        WebElement quantityProductInCart = driver.findElement(By.className("quantity"));
        Integer quantity = Integer.parseInt(quantityProductInCart.getText());

        while (quantity < 3) {

            WebElement productDuck = driver.findElement(By.xpath("//ul[@class = 'listing-wrapper products']//a[1]"));
            productDuck.click();



            if (driver.findElement(By.cssSelector("[itemprop = name]")).getText().equals("Yellow Duck")) {
                Select size = new Select(driver.findElement(By.name("options[Size]")));
                size.selectByValue("Small");
            }

            WebElement btnAddCart = driver.findElement(By.name("add_cart_product"));
            btnAddCart.click();

            String expectedQuantity = "" + (quantity + 1);
            wait.until(textToBePresentInElement(driver.findElement(By.className("quantity")), expectedQuantity));

            quantityProductInCart = driver.findElement(By.className("quantity"));
            quantity = Integer.parseInt(quantityProductInCart.getText());

            driver.get("http://localhost/litecart/en/");

        }


        /** Сохранение количества товаров в корзине*/
        quantityProductInCart = driver.findElement(By.className("quantity"));
        quantity = Integer.parseInt(quantityProductInCart.getText());

        /** Переход в корзину*/
        driver.findElement(By.cssSelector("div#cart a.content")).click();



        for (int i = 0; i < quantity; i++) {

            List<WebElement> tableOfProductsInCart = driver.findElements(By.cssSelector("td.item"));

            /**Если в таблице товаров только одна строка, то карусель товаров отсутствует.
             * Если больше одной строки, то нужно выбрать товар из скриншотов товаров, чтобы остановить карусель*/

            if (tableOfProductsInCart.size() > 1) {
                List<WebElement> shortcutProducts = driver.findElements(By.cssSelector("li.shortcut"));
                shortcutProducts.get(0).click();
            }
            wait.until(elementToBeClickable(By.name("remove_cart_item")));
            driver.findElement(By.name("remove_cart_item")).click();

            wait.until(stalenessOf(tableOfProductsInCart.get(0)));
        }
    }
}

