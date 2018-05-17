package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.pages.BasePage;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ProductPage extends BasePage
{

    public ProductPage(WebDriver driver)
    {
        super(driver);
    }

    public ProductPage addtocart()
    {
        wait.until(visibilityOfElementLocated(By.cssSelector("#cart .quantity")));
        String qb = driver.findElement(By.cssSelector("#cart .quantity")).getAttribute("textContent");
        if (driver.findElement(By.tagName("h1")).getAttribute("textContent").equals("Yellow Duck"))
            new Select(driver.findElement(By.cssSelector("[name='options[Size]']"))).selectByValue("Large");
        driver.findElement(By.cssSelector(".quantity button")).click();
        wait.until(not(textToBePresentInElementLocated(By.cssSelector("#cart .quantity"),qb)));

        return this;
    }

    public CheckoutPage gotoCheckout()
    {
        driver.findElement(By.cssSelector("#cart .link")).click();
        return new CheckoutPage(driver);
    }

}