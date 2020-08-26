package com.nikola.tests.ZeroBank;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ZeroBankTitleVerification {

    /*TC #1: Zero Bank title verification1.Open Chrome browser
    2.Go to http://zero.webappsecurity.com/login.html
    3.Verify titleExpected: “Zero -Log in”*/
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://zero.webappsecurity.com/login.html");

        String expTitle = "Zero - Log in";
        String accTitle = driver.getTitle();

        if (expTitle.equalsIgnoreCase(accTitle)){
            System.out.println("Step1 passed");
        }else{
            System.out.println("Step1 failed");
        }

        String accLink = driver.findElement(By.linkText("Zero Bank")).getAttribute("href");
        String expPartOfLink = "index.html";

        if (accLink.contains(expPartOfLink)){
            System.out.println("Step2 passed");
        }else{
            System.out.println("Step2 failed");
        }

    }
}
