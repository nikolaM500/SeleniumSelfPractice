package com.vytrack;

import com.library.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class vytruckA {

    WebDriver driver;

    @BeforeMethod
    public void setup(){

        driver = util.getDriver();
        driver.get("https://qa2.vytrack.com/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("user153");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();
        driver.manage().window().maximize();

    }


    @Test

    public void homePage(){

        String actual = driver.getTitle();
        String exp = "Dashboard";
        Assert.assertEquals(actual,exp,"It is not on homepage");
        WebElement fleetManagement = driver.findElement(By.xpath("//h1[@class='logo logo-text']"));
        Assert.assertTrue(fleetManagement.isDisplayed(),"Fleet Management is missing");

        WebElement quickLaunchpad = driver.findElement(By.cssSelector("h1[class='oro-subtitle']"));
        Assert.assertTrue(quickLaunchpad.isDisplayed(),"Quick Launchpad is missing");

        WebElement accounts = driver.findElement(By.xpath("//a[@class='fa-suitcase widget-image']"));
        Assert.assertTrue(accounts.isDisplayed(),"Acounts is missing");

        WebElement contacts = driver.findElement(By.xpath("//a[@class='fa-users widget-image']"));
        Assert.assertTrue(contacts.isDisplayed(),"Contacts is missing");

        WebElement rightBarMenu = driver.findElement(By.xpath("//ul[@class='nav pull-right user-menu']"));
        Assert.assertTrue(rightBarMenu.isDisplayed(),"User menu is missing");

        WebElement navBar = driver.findElement(By.xpath("//div[@id='main-menu']"));
        Assert.assertTrue(navBar.isDisplayed(),"Nav bar is missing");

    }

    @Test

    public void mainTopMenu(){

        WebElement navBar = driver.findElement(By.xpath("//div[@id='main-menu']"));
        Assert.assertTrue(navBar.isDisplayed(),"Nav bar is missing");

        WebElement fleet = driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[1]/a/span"));
        Assert.assertTrue(fleet.isDisplayed(),"Fleet is missing");

        WebElement customers = driver.findElement(By.xpath("//div[@id='main-menu']/ul/li[2]"));
        Assert.assertTrue(customers.isDisplayed(),"Customers is missing");

        WebElement activities = driver.findElement(By.xpath("//div[@id='main-menu']/ul/li[3]"));
        Assert.assertTrue(activities.isDisplayed(),"Activities is missing");

        WebElement system = driver.findElement(By.xpath("//div[@id='main-menu']/ul/li[4]"));
        Assert.assertTrue(system.isDisplayed(),"Activities is missing");
    }

    @Test

    public void userMenu(){

        WebElement helpButton = driver.findElement(By.className("fa-question-circle"));
        Assert.assertTrue(helpButton.isDisplayed(),"Help button is missing");

        WebElement dotMenu = driver.findElement(By.xpath("//i[@class='fa-bars']"));
        Assert.assertTrue(dotMenu.isDisplayed(),"Dot menu is missing");

        WebElement mail = driver.findElement(By.xpath("//i[@class='mail-icon']"));
        Assert.assertTrue(mail.isDisplayed(),"Mail button is missing");

        WebElement userMenuDropdown = driver.findElement(By.xpath("//li[@id='user-menu']/a"));
        Assert.assertTrue(userMenuDropdown.isDisplayed(),"User menu dropdown is missing");



    }

    @AfterMethod

    public void setDown(){
        driver.close();

    }


}
