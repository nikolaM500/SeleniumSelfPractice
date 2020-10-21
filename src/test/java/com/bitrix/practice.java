package com.bitrix;

import com.github.javafaker.Faker;
import com.library.util;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class practice {
    Faker faker = new Faker();
    WebDriver driver;
    @BeforeMethod
    public void setup(){
        driver = util.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://login2.nextbasecrm.com");
        driver.findElement(By.xpath("//input[@name='USER_LOGIN']")).sendKeys("helpdesk3@cybertekschool.com");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("UserUser"+ Keys.ENTER);
        driver.get("https://login2.nextbasecrm.com/stream/");

    }

    @AfterMethod
    public void setDown(){
      //driver.close();
    }

    @Test
    public void homepageContainsLogo(){

        WebElement logo = driver.findElement(By.id("logo_24_a"));

        Assert.assertTrue(logo.isDisplayed(),"Logo is missing");

    }

    @Test
    public void attachLink() {
        driver.findElement(By.id("feed-add-post-form-tab-message")).click();

        driver.findElement(By.xpath("//span[@title='Link']/i")).click();

        String linkText = "testLink";
        String linkUrl = faker.internet().url();

        driver.findElement(By.id("linkidPostFormLHE_blogPostForm-text")).sendKeys(linkText);
        driver.findElement(By.id("linkidPostFormLHE_blogPostForm-href")).sendKeys(linkUrl);
        driver.findElement(By.id("undefined")).click();


        WebElement iframe = driver.findElement(By.xpath("//iframe[@class='bx-editor-iframe']"));

        driver.switchTo().frame(iframe);

        WebElement newLink = driver.findElement(By.xpath("//body[@contenteditable='true']//a"));

        Assert.assertTrue(newLink.isDisplayed());


    }

    @Test

    public void addMention(){
        // Message tab from active stream
        driver.findElement(By.xpath("//span[@id='feed-add-post-form-tab-message']/span")).click();
        util.sleep(); // replace with "wait - sleep" method from your library or use Thread.sleep(2000);
        driver.findElement(By.id("bx-b-mention-blogPostForm")).click(); // add Mention icon
        util.sleep();// replace with "wait - sleep" method from your library or use Thread.sleep(2000);
        List<WebElement> providedContacts = driver.findElements(By.xpath("//span[@class='bx-finder-groupbox false']//a"));

        providedContacts.get(0).click(); // this selects first contact from provided list
        util.sleep();// replace with "wait - sleep" method from your library or use Thread.sleep(2000);

        //this is self explanatory :)
        WebElement addedMention = driver.findElement(By.xpath("//span[@id='feed-add-post-destination-item']/span[2]//span[1]"));

        Assert.assertTrue(addedMention.isDisplayed());


    }

    @Test
    public void creatingCheckListItem(){
        //clicks on task tab
        driver.findElement(By.xpath("//span[@id='feed-add-post-form-tab-tasks']//span")).click();
        //clicks on checklist
        driver.findElement(By.xpath("//span[@class='tasks-task-mpf-link']")).click();

        WebElement checkListTextBox = driver.findElement(By.xpath("//input[@class='js-id-checklist-is-form-title task-checklist-field-add']"));
        checkListTextBox.sendKeys("test111");

        //clicks on add button
        driver.findElement(By.xpath("//span[@class='js-id-checklist-is-open-form task-dashed-link-inner']")).click();
        //new checklist item
        WebElement checkListItem = driver.findElement(By.xpath("//label[@class='block-read task-checklist-field-label']"));
        Assert.assertTrue(checkListItem.isDisplayed(),"Check list item is missing");
    }

    @Test
    public void addingSeparator(){
        driver.findElement(By.xpath("//span[@id='feed-add-post-form-tab-tasks']//span")).click();

        driver.findElement(By.xpath("//span[@class='tasks-task-mpf-link']")).click();

        WebElement checkListTextBox = driver.findElement(By.xpath("//input[@class='js-id-checklist-is-form-title task-checklist-field-add']"));
        checkListTextBox.sendKeys("test111");
        driver.findElement(By.xpath("//span[@class='js-id-checklist-is-open-form task-dashed-link-inner']")).click();

        //clicks on separator button
        driver.findElement(By.xpath("//span[@class='js-id-checklist-is-add-separator task-dashed-link-inner']")).click();
        checkListTextBox.sendKeys("test111");
        driver.findElement(By.xpath("//span[@class='js-id-checklist-is-open-form task-dashed-link-inner']")).click();

        //list of elements contains 2 items and separator
        List<WebElement> checkListElements = driver.findElements(By.xpath("//div[@id='bx-component-scope-lifefeed_task_form-checklist']//div[2]//div[@style]"));

        Assert.assertEquals(checkListElements.size(),3);

    }

    @Test
    public void deleteCheckListItem(){
        driver.findElement(By.xpath("//span[@id='feed-add-post-form-tab-tasks']//span")).click();

        driver.findElement(By.xpath("//span[@class='tasks-task-mpf-link']")).click();

        WebElement checkListTextBox = driver.findElement(By.xpath("//input[@class='js-id-checklist-is-form-title task-checklist-field-add']"));
        checkListTextBox.sendKeys("test111");
        driver.findElement(By.xpath("//span[@class='js-id-checklist-is-open-form task-dashed-link-inner']")).click();

        //List<WebElement> checkListElements = driver.findElements(By.xpath("//div[@id='bx-component-scope-lifefeed_task_form-checklist']//div[2]//div[@style]"));
         //System.out.println(checkListElements.size());
        Actions actions = new Actions(driver);

        WebElement checkListItem = driver.findElement(By.xpath("//span[@class='js-id-checklist-is-i-title ']"));

        WebElement xButton = driver.findElement(By.xpath("//span[@class='js-id-checklist-is-i-delete task-field-title-del tasks-btn-delete']"));
        actions.moveToElement(checkListItem).build().perform();

        xButton.click();
        util.sleep();// replace with "wait - sleep" method from your library or use Thread.sleep(2000);
        List<WebElement> checkListElements = driver.findElements(By.xpath("//div[@id='bx-component-scope-lifefeed_task_form-checklist']//div[2]//div[@style]"));

        Assert.assertEquals(checkListElements.size(),0);




    }

    @Test
    public void settingEventTime(){
        //event button
        driver.findElement(By.id("feed-add-post-form-tab-calendar")).click();

        //start calendar
        WebElement startCalendar = driver.findElement(By.id("feed-cal-event-fromcal_3Jcl"));//.click();
        startCalendar.click();
        driver.findElement(By.xpath("//a[@class='bx-calendar-top-month']")).click();
        driver.findElement(By.xpath("//span[@data-bx-month='10']")).click();
        util.sleep();
        driver.findElement(By.xpath("//a[@data-date='1604361600000']")).click();
////div[@class='bx-calendar-layer']//div[1]//a[3]
        //start time
        WebElement startTime = driver.findElement(By.id("feed_cal_event_from_timecal_3Jcl"));//.click();
        startTime.click();
        util.sleep();

        WebElement leftHoursBox = driver.findElement(By.xpath("//div[@id='feed_cal_event_from_timecal_3Jcl_div']//div//table//tbody//tr//td//input[@title='Hours']"));
        leftHoursBox.clear();
        leftHoursBox.sendKeys("2");

        WebElement leftMinutesBox = driver.findElement(By.xpath("//div[@id='feed_cal_event_from_timecal_3Jcl_div']//div//table//tbody//tr//td//input[@title='Minutes']"));
        leftMinutesBox.clear();
        leftMinutesBox.sendKeys("30");
        //set button
        driver.findElement(By.xpath("//*[@id='feed_cal_event_from_timecal_3Jcl_div']/div[2]/input")).click();
        //end calendar
        WebElement endCalendar = driver.findElement(By.id("feed-cal-event-tocal_3Jcl"));//.click();
        endCalendar.click();
        driver.findElement(By.xpath("//a[@class='bx-calendar-top-month']")).click();
        driver.findElement(By.xpath("//span[@data-bx-month='10']")).click();
        util.sleep();
        driver.findElement(By.xpath("//a[@data-date='1604361600000']")).click();
        //end time
        util.sleep();

        WebElement endTime = driver.findElement(By.id("feed_cal_event_to_timecal_3Jcl"));//.click();
        endTime.click();
        WebElement hoursBox = driver.findElement(By.xpath("//div[@id='feed_cal_event_to_timecal_3Jcl_div']//div//table//tbody//tr//td//input[@title='Hours']"));
        hoursBox.clear();
        hoursBox.sendKeys("4");

        WebElement minutesBox = driver.findElement(By.xpath("//div[@id='feed_cal_event_to_timecal_3Jcl_div']//div//table//tbody//tr//td//input[@title='Minutes']"));
        minutesBox.clear();
        minutesBox.sendKeys("15");

        driver.findElement(By.xpath("//*[@id=\"feed_cal_event_to_timecal_3Jcl_div\"]/div[2]/input")).click();

        startCalendar.click();
        String acMonth = driver.findElement(By.xpath("//a[@class='bx-calendar-top-month']")).getText();
        String acDay = driver.findElement(By.xpath("//a[contains(@class,'active')]")).getText();

        String acc = acMonth+"/"+acDay;
        System.out.println(acMonth+"/"+acDay);

        endCalendar.click();
        String acEMonth = driver.findElement(By.xpath("//a[@class='bx-calendar-top-month']")).getText();
        String acEDay = driver.findElement(By.xpath("//a[contains(@class,'active')]")).getText();

        acc +=acEMonth+"/"+acEDay;
        System.out.println(acEMonth+"/"+acEDay);

        startTime.click();

        WebElement sat = driver.findElement(By.xpath("//*[@id=\"feed_cal_event_from_timecal_3Jcl_div\"]/div[1]/div"));
        System.out.println(sat.getAttribute("class"));
        acc+=sat.getAttribute("class");
        endTime.click();

        WebElement sat2 = driver.findElement(By.xpath("//*[@id=\"feed_cal_event_to_timecal_3Jcl_div\"]/div[1]/div"));
        System.out.println(sat2.getAttribute("class"));
        acc+=sat2.getAttribute("class");


        String exp = "November/3" +
                "November/3" +
                "bxc-arrows-cont h2 m30" +
                "bxc-arrows-cont h4 m15";

        Assert.assertEquals(acc,exp);

    }

    @Test
    public void addEvent(){
        //event button
        driver.findElement(By.id("feed-add-post-form-tab-calendar")).click();

        WebElement eventNameBox = driver.findElement(By.id("feed-cal-event-namecal_3Jcl"));
        eventNameBox.sendKeys("test");
        WebElement sendButton = driver.findElement(By.id("blog-submit-button-save"));
        sendButton.click();
        WebElement newEvent = driver.findElement(By.linkText("test"));


        Assert.assertEquals(newEvent.getText(),"test");

    }

    @Test
    public void specifyTimeZone(){
        driver.findElement(By.id("feed-add-post-form-tab-calendar")).click();
        util.sleep(5);// replace with "wait - sleep" method from your library or use Thread.sleep(5000);
        driver.findElement(By.xpath("//span[@class='feed-ev-tz-open']")).click();

        Select select = new Select(driver.findElement(By.id("feed-cal-tz-fromcal_3Jcl")));
        select.selectByVisibleText("(UTC -11:00) Pacific/Midway");

        Assert.assertEquals(select.getFirstSelectedOption().getText(),"(UTC -11:00) Pacific/Midway");
    }












}
