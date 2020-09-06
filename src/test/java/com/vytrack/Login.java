package com.vytrack;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa2.vytrack.com/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("user153");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();
        String expTitle = "Dashboard";
        String accTitle = driver.getTitle();
        checkTitle(expTitle,accTitle);



    }
    public static void checkTitle(String exp, String acc){
        if (exp.equalsIgnoreCase(acc)){
            System.out.println("passed");
        }else{
            System.out.println("failed");
        }
    }

}
