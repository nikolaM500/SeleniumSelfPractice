package com.nikola.practices;

import com.library.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Prectice2 {

    /*TC #2: PracticeCybertek.com_AddRemoveElements WebElement verification
1. Open Chrome browser
2. Go to http://practice.cybertekschool.com/add_remove_elements/
3. Click to “Add Element” button
4. Verify “Delete” button is displayed after clicking.
5. Click to “Delete” button.
6. Verify “Delete” button is NOT displayed after clicking.
USE XPATH and/or CSS Selector LOCATOR FOR ALL WEBELEMENT LOCATORS*/

    public static void main(String[] args) {
        WebDriver driver = util.getDriver();
        driver.get("http://practice.cybertekschool.com/add_remove_elements/");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/button")).click();
        WebElement deleteButton = driver.findElement(By.xpath("//*[@id=\"elements\"]/button"));
        if (deleteButton.isDisplayed()){
            System.out.println("Add element was clicked\nDelete button is now displayed");
        }else{
            System.out.println("failed");
        }
        System.out.println(driver.findElement(By.cssSelector("button[class='added-manually']")).isDisplayed());
        deleteButton.click();
        System.out.println(driver.findElement(By.cssSelector("button[class='added-manually']")).isDisplayed());


        /*if (!deleteButton.isDisplayed()){
            System.out.println("Delete button was clicked\nDelete button is not displayed now");
        }else{
            System.out.println("failed");
        }
        driver.close();*/


    }
}
