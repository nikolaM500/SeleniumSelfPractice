package com.vytrack;

/*Verify that truck driver is able to reset by clicking reset button on grid*/

import com.library.util;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class B203_89 {

    public static void main(String[] args) {
        WebDriver driver = util.getDriver();
        driver.get("https://qa2.vytrack.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("prependedInput")).sendKeys("user153");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123"+ Keys.ENTER);
        driver.get("https://qa2.vytrack.com/entity/Extend_Entity_VehiclesOdometer");
        util.sleep(5);
        driver.findElement(By.xpath("//*[@id=\"grid-custom-entity-grid-1224405351\"]/div[2]/div[1]/div/div[3]/div[1]/div/a[1]/i")).click();

    }



}
