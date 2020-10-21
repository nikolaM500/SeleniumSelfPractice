package com.nikola.practices;

import com.library.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
/*1. Open Chrome browser
2. Go to http://practice.cybertekschool.com/add_remove_elements
3. Click to “Add Element” button 50 times
4. Verify 50 “Delete” button is displayed after clicking.
5. Click to ALL “Delete” buttons to delete them.
6. Verify “Delete” button is NOT displayed after clicking.
USE XPATH LOCATOR FOR ALL WEBELEMENT LOCATORS
Hint: Need to use findElements method*/

public class Find50Elements {

    public static void main(String[] args) {

        WebDriver driver = util.getDriver();
        driver.get("http://practice.cybertekschool.com/add_remove_elements/");

        for (int i = 0; i<50; i++){
            driver.findElement(By.xpath("//*[@id=\"content\"]/div/button")).click();
        }

        List<WebElement> deleteButtons = driver.findElements(By.xpath("//*[@id=\"elements\"]/button"));

        System.out.println(deleteButtons.size());

        for (int i = 0; i<50; i++){
            driver.findElement(By.xpath("//div[@id='elements']/button[1]")).click();
        }

        List<WebElement> deletedButtons = driver.findElements(By.xpath("//*[@id=\"elements\"]/button"));


        System.out.println(deletedButtons.isEmpty());

        driver.close();




    }

}
