package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.util.Random;

import static java.lang.Math.abs;

public class RegistrationTest extends BaseTest{


    @Test
    public void RegistrationTest(){

        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector("#box-account-login a")).click();

        Random rand = new Random();
        int  n = abs(rand.nextInt());

        WebElement taxID = driver.findElement(By.cssSelector("[name = tax_id]"));
        WebElement company = driver.findElement(By.cssSelector("[name = company]"));
        WebElement firstName = driver.findElement(By.cssSelector("[name = firstname]"));
        WebElement lastName = driver.findElement(By.cssSelector("[name = lastname]"));
        WebElement address1 = driver.findElement(By.cssSelector("[name = address1]"));
        WebElement address2 = driver.findElement(By.cssSelector("[name = address2]"));
        WebElement postcode = driver.findElement(By.cssSelector("[name = postcode]"));
        WebElement city = driver.findElement(By.cssSelector("[name = city]"));
        WebElement email = driver.findElement(By.cssSelector("[name = email]"));
        WebElement phone = driver.findElement(By.cssSelector("[name = phone]"));
        WebElement password = driver.findElement(By.cssSelector("[name = password]"));
        WebElement passwordConfirmed = driver.findElement(By.cssSelector("[name = confirmed_password]"));
        WebElement btnCreateAccount = driver.findElement(By.cssSelector("[name = create_account]"));
        WebElement country = driver.findElement(By.cssSelector("[name = country_code]"));


        String taxIdRandom = ""+ abs(rand.nextInt());
        String companyTest = "Test";
        String firstNameTest = "Malov";
        String lastNameTest = "Nikolay";
        String address1Test = "Address1_Test";
        String address2Test = "Address2_Test";
        String postcodeTest = "12345";
        String countryTest = "United States";
        String cityTest = "Moscow";
        String emailTest = "test" + abs(rand.nextInt()) + "@mail.com";
        String phoneTest = "+19685139281";
        String passwordTest = "test";


        taxID.sendKeys(taxIdRandom);
        company.sendKeys(companyTest);
        firstName.sendKeys(firstNameTest);
        lastName.sendKeys(lastNameTest);
        address1.sendKeys(address1Test);
        address2.sendKeys(address2Test);
        postcode.sendKeys(postcodeTest);
        city.sendKeys(cityTest);
        country.sendKeys(countryTest + Keys.ENTER);
        email.sendKeys(emailTest);
        phone.sendKeys(phoneTest);
        password.sendKeys(passwordTest);
        passwordConfirmed.sendKeys(passwordTest);

        btnCreateAccount.click();


        WebElement lnkLogout = driver.findElement(By.xpath("//div[@class = 'left']//a[contains(.,'Logout')]"));

        lnkLogout.click();

        WebElement emailMainPage = driver.findElement(By.cssSelector("[name = email]"));
        WebElement passwordMainPage = driver.findElement(By.cssSelector("[name = password]"));
        WebElement btnLogin = driver.findElement(By.cssSelector("[name = login]"));

        emailMainPage.sendKeys(emailTest);
        passwordMainPage.sendKeys(passwordTest);
        btnLogin.click();

        lnkLogout = driver.findElement(By.xpath("//div[@class = 'left']//a[contains(.,'Logout')]"));
        lnkLogout.click();

    }
}
