package com.library;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;

import java.util.List;

public class util {
    public static WebDriver getDriver() {

        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();

    }

    public static WebDriver getDriver(String browserType) {

        if (browserType.equalsIgnoreCase("chrome")) {
            //System.setProperty("webdriver.chrome.driver", "path");

            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();

        } else if (browserType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else if (browserType.equalsIgnoreCase("opera")) {
            WebDriverManager.operadriver().setup();
            return new OperaDriver();
        }else if (browserType.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        }else {
            System.out.println("Given browser does not exist. Driver = null!");
            return null;
        }

    }

    public static void checkIfMatches(String exp, String acc){
        if (exp.equalsIgnoreCase(acc)){
            System.out.println("passed");
        }else{
            System.out.println("failed");
        }
    }

    public static void checkIFContains(String exp, String acc){
        if (acc.contains(exp)){
            System.out.println("passed");
        }else{
            System.out.println("failed");
        }
    }

    public static void sleep(){

        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){

        }

    }

    public static void sleep(int sec){
        int ms = sec*1000;
        try{
            Thread.sleep(ms);
        }catch (InterruptedException e){

        }

    }

    public static void sleep(double seconds){
        try {
            Thread.sleep((long)(seconds * 1000));
        }catch (InterruptedException e){

        }
    }

    public static void loginToSmartBear(WebDriver driver){

        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();

    }


    public static void verifyNameSmartBear(WebDriver driver, String name){

        List<WebElement> nameList = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr/td[2]"));

        for (WebElement nameElement : nameList) {

            if (nameElement.getText().equalsIgnoreCase(name)) {

                Assert.assertTrue(true);
                Assert.assertTrue(nameElement.getText().equalsIgnoreCase(name));
                return;
            }


        }
        Assert.fail("Name "+name+" does not exist in the list");
        Assert.fail("Verification fails");


    }

    public static void verifyNameNotOnListSmartBear(WebDriver driver, String name){

        List<WebElement> nameList = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr/td[2]"));

        for (WebElement nameElement : nameList) {

            if (nameElement.getText().equalsIgnoreCase(name)) {
                Assert.fail("Name "+name+" exists in the list");
                Assert.fail("Verification fails");
                return;


            }


        }
        Assert.assertTrue(true);



    }

    public static void removeNameSmartBear(WebDriver driver, String name){
        driver.findElement(By.xpath("//td[.='"+name+"']/..//td[1]")).click();
        driver.findElement(By.id("ctl00_MainContent_btnDelete")).click();
        util.sleep();

        verifyNameNotOnListSmartBear(driver,name);



    }





}
