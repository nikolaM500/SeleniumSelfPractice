package com.nikola.tests.facebookTitleVerification;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TitleVerification {
    /*TC #1: Facebook title verification1.Open Chrome browser
    2.Go to https://www.facebook.com3.Verify
    title: Expected: “Facebook -Log In or Sign Up”TC
    #2: Facebook incorrect login title verification1.
    Open Chrome browser
    2.Go to https://www.facebook.com3.Enter incorrect username
    4.Enter incorrect password
    5.Verify title changed to: Expected: “Log into Facebook | Facebook”
     */

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.facebook.com");

        String expTitle = "Facebook - Log In or Sign Up";
        String accTitle = driver.getTitle();

        if (expTitle.equalsIgnoreCase(accTitle)){
            System.out.println("Step1 passed");
        }else{
            System.out.println("Step1 failed");
        }

        driver.findElement(By.id("email")).sendKeys("wrongemail@gmoil.com");
        driver.findElement(By.id("pass")).sendKeys("invalidpassword");
        driver.findElement(By.id("u_0_b")).click();

        String expTitle2 = "Log into Facebook | Facebook";
        String accTitle2 = driver.getTitle();

        if (expTitle2.equalsIgnoreCase(accTitle2)){
            System.out.println("Step2 passed");
        }else{
            System.out.println("Step2 failed");
        }

        System.out.println(driver.getTitle());







    }

}
