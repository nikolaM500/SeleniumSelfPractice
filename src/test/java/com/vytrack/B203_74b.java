package com.vytrack;

import com.library.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class B203_74b {
    public static void main(String[] args) {
        WebDriver driver = util.getDriver();
        driver.get("https://qa2.vytrack.com/user/login");
        driver.manage().window().maximize();
        driver.findElement(By.id("prependedInput")).sendKeys("user153");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();
        driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[3]")).click();
        util.sleep(1);
        driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[3]/div/div/ul/li[3]/a/span")).click();
        util.sleep(3);
        driver.findElement(By.partialLinkText("Create Calendar")).click();

        try {
            driver.findElement(By.partialLinkText("Create Calendar")).click();
        } catch ( Exception e ){
            System.out.println(e.getMessage());
           // System.out.println(e.getCause());
        } finally {
            driver.findElement(By.partialLinkText("Create Calendar")).click();
        }
/*
        driver.get("https://qa2.vytrack.com/calendar/event/create");
        driver.findElement(By.id("oro_calendar_event_form_title-uid-5f57b5c30f3d1")).sendKeys("Drivers meeting");
        driver.findElement(By.className("btn btn-success action-button")).click();
*/





    }
}

