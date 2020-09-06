package com.nikola.tests.Craigslist;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Craigslist {
    public static void main(String[] args )  throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        Thread.sleep(1000);
        driver.findElement(By.name("q")).sendKeys("craigslist phoenix az" + Keys.ENTER);
        Thread.sleep(1000);
        driver.get("https://phoenix.craigslist.org/");
        String expRes = "craigslist: phoenix, AZ jobs, apartments, for sale, services, community, and events";
        String accRes = driver.getTitle();
        System.out.println("Phoenix home page");
        checkIfMatches(expRes,accRes);
        driver.findElement(By.linkText("18")).click();
        Thread.sleep(1000);
        expRes= "phoenix events/classes - craigslist";
        String accRes2=driver.getTitle();
        System.out.println("events by date");
        checkIfMatches(expRes,accRes2);
        driver.navigate().back();
        Thread.sleep(3000);
        System.out.println("Back to Phoenix home page");
        expRes = "craigslist: phoenix, AZ jobs, apartments, for sale, services, community, and events";
        accRes=driver.getTitle();
        checkIfMatches(expRes,accRes);
        driver.findElement(By.id("query")).sendKeys("passat 2014" + Keys.ENTER);
        System.out.println("Passat results");
        expRes = "phoenix for sale \"passat 2014\" - craigslist";
        accRes=driver.getTitle();
        checkIfMatches(expRes,accRes);
        WebElement searchTitlesOnly = driver.findElement(By.name("srchType"));
        searchTitlesOnly.click();
        Thread.sleep(1000);
        WebElement hasImage = driver.findElement(By.name("hasPic"));
        hasImage.click();
        Thread.sleep(1000);
        WebElement bundleDuplicates = driver.findElement(By.name("bundleDuplicates"));
        bundleDuplicates.click();
        Thread.sleep(1000);
        driver.findElement(By.id("query")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("query")).sendKeys("Audi A6" + Keys.ENTER);
        Thread.sleep(1000);
        System.out.println("Audi results");
        expRes="phoenix for sale \"Audi A6\" - craigslist";
        accRes=driver.getTitle();
        checkIfMatches(expRes,accRes);
        driver.findElement(By.name("logoLink")).click();
        Thread.sleep(1000);
        expRes = "craigslist: phoenix, AZ jobs, apartments, for sale, services, community, and events";
        accRes = driver.getTitle();
        System.out.println("Back to home page");
        checkIfMatches(expRes,accRes);
        driver.manage().window().maximize();
        driver.findElement(By.className("sss")).click();
        driver.findElement(By.id("subcatAbb")).click();
        Select dropdown = new Select(driver.findElement(By.id("subcatAbb")));
        dropdown.selectByVisibleText("cars+trucks");
        System.out.println("For sale cars and trucks");
        expRes="phoenix cars & trucks - craigslist";
        accRes=driver.getTitle();
        checkIfMatches(expRes,accRes);
        driver.findElement(By.name("srchType")).click();
        driver.findElement(By.name("hasPic")).click();
        driver.findElement(By.name("bundleDuplicates")).click();
        driver.findElement(By.name("search_distance")).sendKeys("50");
        driver.findElement(By.name("postal")).sendKeys("85251");
        Thread.sleep(1000);
        driver.findElement(By.name("min_price")).sendKeys("5000");
        driver.findElement(By.name("max_price")).sendKeys("15000");
        Thread.sleep(1000);
        driver.findElement(By.name("auto_make_model")).sendKeys("BMW X5");
        Thread.sleep(1000);
        driver.findElement(By.name("min_auto_year")).sendKeys("2008");
        driver.findElement(By.name("max_auto_year")).sendKeys("2013");
        Thread.sleep(1000);
        driver.findElement(By.name("min_auto_miles")).sendKeys("70000");
        driver.findElement(By.name("max_auto_miles")).sendKeys("200000"+Keys.ENTER);
        Thread.sleep(1000);
        System.out.println("BMW X5 results");
        expRes = "https://phoenix.craigslist.org/search/cta?srchType=T&hasPic=1&bundleDuplicates=1&search_distance=50&postal=85251&min_price=5000&max_price=15000&auto_make_model=BMW+X5&min_auto_year=2008&max_auto_year=2013&min_auto_miles=70000&max_auto_miles=200000";
        accRes = driver.getCurrentUrl();
        checkIfMatches(expRes,accRes);
        Thread.sleep(6000);
        driver.close();



















    }

    public static void checkIfMatches(String exp, String acc){
        if (exp.equalsIgnoreCase(acc)){
            System.out.println("passed");
        }else{
            System.out.println("failed");
        }
    }

    public static void checkIFContains(String exp, String acc){
        if (acc.contains(exp)){
            System.out.println("passed");
        }else{
            System.out.println("failed");
        }
    }
/*

        driver.findElement(By.className("plusminus")).click();
        driver.findElement(By.linkText("select all")).click();
        driver.findElement(By.linkText("cars & trucks")).click();
        */



}
