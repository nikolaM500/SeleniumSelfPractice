package com.nikola.practices;

import com.library.util;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UrosCase {
    public static void main(String[] args) {

        WebDriver driver = util.getDriver();
        driver.manage().window().maximize();
        driver.get("https://qa2.vytrack.com/");


        // driver.findElement(By.id("prependedInput")).sendKeys("user181");

        WebElement userName=driver.findElement(By.id("prependedInput"));
        userName.sendKeys("user181");



        //   driver.findElement(By.name("_password")).sendKeys("UserUser123"+ Keys.ENTER);

        WebElement password=driver.findElement(By.name("_password"));
        password.sendKeys("UserUser123"+ Keys.ENTER);


        driver.findElement(By.linkText("Fleet")).click();
        util.sleep(6);
        driver.findElement(By.linkText("Vehicles")).click();
        util.sleep(6);
        driver.findElement(By.cssSelector("a[title='Grid Settings']")).click();//goes to Grid settings
        util.sleep(6);
        //unmarking check boxes
        driver.findElement(By.xpath("//*[@id=\"column-c111\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c112\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c113\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c114\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c115\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c116\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c117\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c118\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c119\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c120\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c121\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c122\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c123\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c124\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c125\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c126\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c127\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c128\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c129\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"column-c130\"]")).click();

        driver.findElement(By.cssSelector("span[class='close']")).click();


        driver.findElement(By.cssSelector("a[title='Reset']")).click();




    }
}
