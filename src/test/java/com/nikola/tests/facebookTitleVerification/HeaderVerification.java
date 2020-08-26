package com.nikola.tests.facebookTitleVerification;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HeaderVerification {

    /*TC #3: Facebook header verification
    1.Open Chrome browser
    2.Go to https://www.facebook.com3.Verify
    header text is as expected:
    Expected: “Connect with friends and the world around you on Facebook.

    ”TC #4: Facebook header verification1.Open Chrome browser
    2.Go to https://www.facebook.com
    3.Verify “Create a page” link href value contains text:
    Expected: “registration_form”*/
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get(" https://www.facebook.com");
        String expHeader = "Connect with friends and the world around you on Facebook.";
        String accHeader = driver.findElement(By.tagName("h2")).getText();

        if (expHeader.equalsIgnoreCase(accHeader)){
            System.out.println("Step1 passed");
        }else{
            System.out.println("Step1 failed");
        }

        String accLink = driver.findElement(By.linkText("Create a Page")).getAttribute("href");
        String expPartOfLink = "registration_form";

        if (accLink.contains(expPartOfLink)){
            System.out.println("Step2 passed");
        }else{
            System.out.println("Step2 failed");
        }
    }

}
