package com.svasta;

import com.github.javafaker.Faker;
import com.library.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SmartBear {

    WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = util.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");

        util.loginToSmartBear(driver);
    }

    @Test
    public void printLInks(){
        List<WebElement> allLinks = driver.findElements(By.xpath("//body//a"));
        System.out.println("Count of all links: " + allLinks.size());

        for (WebElement eachElement : allLinks) {
            System.out.println(eachElement.getText());
        }
    }

    @Test
    public void orderPlacing(){
        driver.findElement(By.xpath("//*[@id='ctl00_menu']/li[3]/a")).click();
        Select select = new Select(driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")));

        select.selectByVisibleText("FamilyAlbum");
        WebElement quantity = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));

        quantity.clear();
        quantity.sendKeys("2");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Faker faker = new Faker();

        WebElement customerName = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName"));
        customerName.sendKeys(faker.name().fullName());

        WebElement street = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2"));
        street.sendKeys(faker.address().streetAddress());

        WebElement city = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3"));
        city.sendKeys(faker.address().city());

        WebElement state = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4"));
        state.sendKeys(faker.address().city());

        WebElement zipcode = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5"));
        zipcode.sendKeys(faker.address().zipCode().replaceAll("-",""));

        driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0")).click();
        WebElement cardNo = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6"));
        cardNo.sendKeys(faker.finance().creditCard().replaceAll("-",""));

        WebElement expireDate = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1"));
        expireDate.sendKeys("05/23");
        util.sleep();
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();

        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        WebElement successMessage = driver.findElement(By.xpath("//strong"));

        Assert.assertTrue(successMessage.isDisplayed(),"Order is not placed");


    }

    @Test
    public void verificationOrder(){
        util.verifyNameSmartBear(driver,"Johny Bee");
    }

    @Test
    public void deleteOrder(){
       //1.Open browser and login to SmartBear
       //2.Delete “Mark Smith” from the list
        driver.findElement(By.xpath("//table[@id=\"ctl00_MainContent_orderGrid\"]//tbody//tr[3]//td[1]")).click();
        driver.findElement(By.id("ctl00_MainContent_btnDelete")).click();
        util.sleep();
        //3.Assert it is deleted from the list
        util.verifyNameNotOnListSmartBear(driver,"Mark Smith");

    }

    @Test
    public void deleteOrder2(){
        util.removeNameSmartBear(driver,"Mark Smith");
    }

    @Test
    public void editOrder(){
        //Click to edit button from the right for “Steve Johns”
        driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']//tbody//tr[3]//td[13]")).click();
        //3.Change name to “Michael Jordan”
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).clear();
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys("Michael Jordan");
        //4.Click Update
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1")).click();
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_UpdateButton")).click();
        util.sleep();
        //5.Assert “Michael Jordan” is in the lis
        util.verifyNameSmartBear(driver,"Michael Jordan");
    }




    @AfterMethod
    public void setDown(){
       //driver.close();
    }









}

