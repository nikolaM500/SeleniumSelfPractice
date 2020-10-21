package com.bitrix;

import com.github.javafaker.Faker;
import com.library.ConfigurationReader;
import com.library.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBaseBitrix {

    Faker faker = new Faker();
    String bitrixLoginUrl = ConfigurationReader.getProperty("bitrixLoginUrl");
    String bitrixPassword = ConfigurationReader.getProperty("bitrixPassword");
    String bitrixUserName = ConfigurationReader.getProperty("bitrixUserName");
    String bitrixHomepageurl = ConfigurationReader.getProperty("bitrixHomePageUrl");
    WebDriver driver = Driver.getDriver();





    @BeforeMethod
    public void setupMethod(){
        driver.get(bitrixLoginUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name='USER_LOGIN']")).sendKeys(bitrixUserName);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(bitrixPassword+ Keys.ENTER);

    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
