package com.svasta;

import com.library.util;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Scanner;

public class ytb {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Type the name of the song");
        String song = in.nextLine();
        WebDriver driver = util.getDriver();
        driver.manage().window().maximize();
        util.sleep();
        driver.get("https://www.youtube.com/");
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys(song+ Keys.ENTER);
        util.sleep(5);
        //driver.findElement(By.partialLinkText(song)).click();

        List<WebElement> songs = driver.findElements(By.xpath("//a[@id='thumbnail']"));
        songs.get(0).click();


        util.sleep();


    }
}
