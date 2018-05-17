package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage removeAllfromCart() {

        List<WebElement> tableOfProductsInCart = driver.findElements(By.cssSelector("td.item"));

        for (int i = 0; i < tableOfProductsInCart.size(); i++) {
            tableOfProductsInCart = driver.findElements(By.cssSelector("td.item"));

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
        return this;

    }

}