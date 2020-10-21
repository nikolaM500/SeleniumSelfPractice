package com.nikola.practices;

import com.library.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FindElements_Apple {
    /*1. Open Chrome browser
2. Go to https://www.apple.com
3. Click to iPhone
4. Print out the texts of all links
5. Print out how many link is missing text
6. Print out how many link has text
7. Print out how many total link*/

    public static void main(String[] args) {
        //1. Open Chrome browser
        WebDriver driver = util.getDriver();
        //Go to https://www.apple.com
        driver.get("https://www.apple.com");
        //Click to iPhone
        driver.findElement(By.xpath("//*[@id=\"ac-globalnav\"]/div/ul[2]/li[4]/a")).click();
        //Print out the texts of all links
        List<WebElement> linkElements = driver.findElements(By.xpath("//body//a"));
        for (WebElement eachElement : linkElements) {
            System.out.println(eachElement.getText());
        }
        //Print out how many link is missing text
        int linksNoText = 0;

        for (WebElement eachElement : linkElements) {

           if (eachElement.getText().isEmpty()){
               linksNoText++;
           }
        }

        System.out.println("Number of links without text: "+ linksNoText);

        //Print out how many link has text

        int linksWithText = 0;

        for (WebElement eachElement : linkElements) {

            if (!eachElement.getText().isEmpty()){
                linksWithText++;
            }
        }
        System.out.println("Number of links with text: "+ linksWithText);

        //Print out how many total link

        System.out.println("Links in total: "+ linkElements.size());


    }


}
