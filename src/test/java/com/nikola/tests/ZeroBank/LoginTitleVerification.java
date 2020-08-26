package com.nikola.tests.ZeroBank;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTitleVerification {

    public static void checkTitle(String exp, String acc){
        if (exp.equalsIgnoreCase(acc)){
            System.out.println("passed");
        }else{
            System.out.println("failed");
        }
    }

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://zero.webappsecurity.com/login.html");
        driver.findElement(By.id("user_login")).sendKeys("username");
        driver.findElement(By.id("user_password")).sendKeys("password"+ Keys.ENTER);

        String expTitle = "Zero - Account Summary";
        String accTitle = driver.getTitle();
/*
        if (expTitle.equalsIgnoreCase(accTitle)){
            System.out.println("Step1 passed");
        }else{
            System.out.println("Step1 failed");
        }

 */
        System.out.println("step 1");
        checkTitle(expTitle,accTitle);

        driver.findElement(By.linkText("Account Activity")).click();

        String expTitle2 = "Zero - Account Activity";
        String accTitle2 = driver.getTitle();
        System.out.println("step 2");
        checkTitle(expTitle2,accTitle2);
/*
        if (expTitle2.equalsIgnoreCase(accTitle2)){
            System.out.println("Step2 passed");
        }else{
            System.out.println("Step2 failed");
        }*/

        driver.findElement(By.linkText("Transfer Funds")).click();

        String expTitle3 = "Zero - Transfer Funds";
        String accTitle3 = driver.getTitle();
        System.out.println("step 3");
        checkTitle(expTitle3,accTitle3);

  /*      if (expTitle3.equalsIgnoreCase(accTitle3)){
            System.out.println("Step3 passed");
        }else{
            System.out.println("Step3 failed");
        }*/

        driver.findElement(By.linkText("Pay Bills")).click();

        String expTitle4 = "Zero - Pay Bills";
        String accTitle4 = driver.getTitle();
        System.out.println("step 4");
        checkTitle(expTitle4,accTitle4);

   /*     if (expTitle4.equalsIgnoreCase(accTitle4)){
            System.out.println("Step4 passed");
        }else{
            System.out.println("Step4 failed");
        }*/

        driver.findElement(By.linkText("My Money Map")).click();

        String expTitle5 = "Zero - My Money Map";
        String accTitle5 = driver.getTitle();
        System.out.println("step 5");
        checkTitle(expTitle5,accTitle5);

    /*    if (expTitle5.equalsIgnoreCase(accTitle5)){
            System.out.println("Step5 passed");
        }else{
            System.out.println("Step5 failed");
        }*/

        driver.findElement(By.linkText("Online Statements")).click();

        String expTitle6 = "Zero - Online Statements";
        String accTitle6 = driver.getTitle();
        System.out.println("step 6");
        checkTitle(expTitle6,accTitle6);

  /*      if (expTitle6.equalsIgnoreCase(accTitle6)){
            System.out.println("Step6 passed");
        }else{
            System.out.println("Step6 failed");
        }*/





    }
}
