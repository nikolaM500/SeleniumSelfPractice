package com.nikola.practices;

import com.library.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
/*1. Open Chrome browser
2. Go to https://www.apple.com
3. Click to all of the headers one by one
a. Mac, iPad, iPhone, Watch, TV, Music, Support
4. Print out how many links on each page with the titles of the pages
5. Loop through all
6. Print out how many link is missing text TOTAL
7. Print out how many link has text TOTAL
8. Print out how many total link TOTAL*/

public class FindElementsApple2 {

    public static void main(String[] args) {
        WebDriver driver = util.getDriver();
        int linksWithText = 0;
        int linksWithoutText = 0;
        //Go to https://www.apple.com
        driver.get("https://www.apple.com");
        driver.findElement(By.xpath("//a[@class='ac-gn-link ac-gn-link-mac']")).click();
        util.sleep();

        System.out.println("Mac page: "+driver.getTitle());

        List<WebElement> linkElements = driver.findElements(By.xpath("//body//a"));
        for (WebElement eachElement : linkElements) {
            System.out.println(eachElement.getText());
            if (eachElement.getText().isEmpty()){
                linksWithoutText++;
            }else {
                linksWithText++;
            }
        }


        driver.findElement(By.xpath("//a[@class='ac-gn-link ac-gn-link-ipad']")).click();
        util.sleep();
        System.out.println("Ipad page: "+driver.getTitle());
        List<WebElement> linkElementsIpad = driver.findElements(By.xpath("//body//a"));
        for (WebElement eachElement : linkElementsIpad) {
            System.out.println(eachElement.getText());
            if (eachElement.getText().isEmpty()){
                linksWithoutText++;
            }else {
                linksWithText++;
            }
        }


        driver.findElement(By.xpath("//a[@class='ac-gn-link ac-gn-link-watch']")).click();
        util.sleep();
        System.out.println("Watch page: "+driver.getTitle());
        List<WebElement> linkElementsWatch = driver.findElements(By.xpath("//body//a"));
        for (WebElement eachElement : linkElementsWatch) {
            System.out.println(eachElement.getText());
            if (eachElement.getText().isEmpty()){
                linksWithoutText++;
            }else {
                linksWithText++;
            }
        }


        driver.findElement(By.xpath("//a[@class='ac-gn-link ac-gn-link-tv']")).click();
        util.sleep();
        System.out.println("TV page: "+driver.getTitle());
        List<WebElement> linkElementsTV = driver.findElements(By.xpath("//body//a"));
        for (WebElement eachElement : linkElementsTV) {
            System.out.println(eachElement.getText());
            if (eachElement.getText().isEmpty()){
                linksWithoutText++;
            }else {
                linksWithText++;
            }
        }


        driver.findElement(By.xpath("//a[@class='ac-gn-link ac-gn-link-music']")).click();
        util.sleep();
        System.out.println("Music page: "+driver.getTitle());
        List<WebElement> linkElementsMusic = driver.findElements(By.xpath("//body//a"));
        for (WebElement eachElement : linkElementsMusic) {
            System.out.println(eachElement.getText());
            if (eachElement.getText().isEmpty()){
                linksWithoutText++;
            }else {
                linksWithText++;
            }
        }


        driver.findElement(By.xpath("//a[@class='ac-gn-link ac-gn-link-support']")).click();
        util.sleep();
        System.out.println("Support page: "+driver.getTitle());
        List<WebElement> linkElementsSupport = driver.findElements(By.xpath("//body//a"));
        for (WebElement eachElement : linkElementsSupport) {
            System.out.println(eachElement.getText());
            if (eachElement.getText().isEmpty()){
                linksWithoutText++;
            }else {
                linksWithText++;
            }
        }


        System.out.println("Links without text: "+linksWithoutText);
        System.out.println("Links with text: "+linksWithText);


    }
}
