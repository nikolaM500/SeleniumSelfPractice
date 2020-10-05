package com.bitrix;

import com.library.ConfigurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenBitrix extends TestBaseBitrix {

    String eventTitle = ConfigurationReader.getProperty("bitrixEventName");
    String linkText = ConfigurationReader.getProperty("attachingLinkText");

    @Test
    public void addEvent(){
        //event button

        driver.findElement(By.id("feed-add-post-form-tab-calendar")).click();

        WebElement eventNameBox = driver.findElement(By.id("feed-cal-event-namecal_3Jcl"));
        eventNameBox.sendKeys(eventTitle);
        WebElement sendButton = driver.findElement(By.id("blog-submit-button-save"));
        sendButton.click();
        WebElement newEvent = driver.findElement(By.linkText(eventTitle));


        Assert.assertEquals(newEvent.getText(),eventTitle);

        System.out.println(bitrixLoginUrl);
    }

    @Test
    public void attachLink() {
        driver.findElement(By.id("feed-add-post-form-tab-message")).click();

        driver.findElement(By.xpath("//span[@title='Link']/i")).click();


        String linkUrl = faker.internet().url();

        driver.findElement(By.id("linkidPostFormLHE_blogPostForm-text")).sendKeys(linkText);
        driver.findElement(By.id("linkidPostFormLHE_blogPostForm-href")).sendKeys(linkUrl);
        driver.findElement(By.id("undefined")).click();


        WebElement iframe = driver.findElement(By.xpath("//iframe[@class='bx-editor-iframe']"));

        driver.switchTo().frame(iframe);

        WebElement newLink = driver.findElement(By.xpath("//body[@contenteditable='true']//a"));

        Assert.assertTrue(newLink.isDisplayed());


    }







}
