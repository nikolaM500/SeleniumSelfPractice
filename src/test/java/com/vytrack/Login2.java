package com.vytrack;

import com.library.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login2 {
    public static void main(String[] args) {

        WebDriver driver = util.getDriver();
        driver.get("https://qa2.vytrack.com/user/login");
        driver.manage().window().maximize();
        driver.findElement(By.id("prependedInput")).sendKeys("user153");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();
        String expTitle = "Dashboard";
        String accTitle = driver.getTitle();
        util.checkIfMatches(expTitle,accTitle);
        driver.close();

    }
}
