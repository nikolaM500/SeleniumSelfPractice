package com.nikola.tests.Craigslist;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Test {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://phoenix.craigslist.org/d/for-sale/search/sss");
        driver.findElement(By.id("subcatAbb")).click();
        Select dropdown = new Select(driver.findElement(By.id("subcatAbb")));
        dropdown.selectByVisibleText("cars+trucks");

/*Select dropdown = new Select(driver.findElement(By.id("carPickerUsed_makerSelect")));
        dropdown.selectByVisibleText("Volkswagen");*/

    }
}
