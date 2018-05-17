package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import ru.stqa.training.selenium.pages.*;

public class AddingProductToCartPageObject extends BaseTest {

    @Test
    public void AddingProductToCartPageObject() {
        MainPage shopPage = new MainPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);


        for (int i = 0; i < 3; i++) {
            shopPage.open();
            productPage = shopPage.chooseProduct();
            productPage.addtocart();
        }

        checkoutPage = productPage.gotoCheckout();
        checkoutPage.removeAllfromCart();


    }

}