package com.vytrack;

import com.library.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class VY_Truck_SmokeTest {
    WebDriver driver;

    @BeforeClass
    public void setup(){
        driver = util.getDriver();
        driver.get("https://qa2.vytrack.com/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("user153");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();
        driver.manage().window().maximize();
    }


    @BeforeMethod
    public void startTest(){
        driver.get("https://qa2.vytrack.com/");
        util.sleep(5);
    }

    @AfterClass
    public void setDown(){
        driver.close();
    }

    @Test
    public void resetButtonVehicles(){
        driver.findElement(By.linkText("Fleet")).click();
        util.sleep(2);
        //locating Vehicle and clicking
        driver.findElement(By.linkText("Vehicles")).click();
        util.sleep(10);


        WebElement dropdownNumberPerPage = driver.findElement(By.xpath("//button[@class='btn dropdown-toggle ']"));
        String actNumberPerPage = dropdownNumberPerPage.getText();
        String expNumberPerPage = "25";
        Assert.assertEquals(actNumberPerPage,expNumberPerPage,"View per page is not 25");

        String actPageNumber = driver.findElement(By.xpath("//input[@type='number']")).getAttribute("value");
        String expPageNumber = "1";
        Assert.assertEquals(actPageNumber,expPageNumber,"Page number is not 1");




        driver.findElement(By.xpath("//button[@class='btn dropdown-toggle ']")).click();// view per page
        util.sleep();
        driver.findElement(By.xpath("//a[@data-size='10']")).click();// 10 odometers per page
        util.sleep();
        dropdownNumberPerPage = driver.findElement(By.xpath("//button[@class='btn dropdown-toggle ']"));
        actNumberPerPage = dropdownNumberPerPage.getText();
        expNumberPerPage = "10";

        Assert.assertEquals(actNumberPerPage,expNumberPerPage,"View per page is not 10");
        driver.findElement(By.cssSelector("a[title='Filters']")).click();// click on filter button
        util.sleep();
        driver.findElement(By.xpath("//a[@class='add-filter-button']")).click();// menage filters
        util.sleep();
        driver.findElement(By.xpath("//ul[@class='ui-multiselect-checkboxes ui-helper-reset fixed-li']/li[1]")).click();// filters
        util.sleep();
        driver.findElement(By.xpath("//ul[@class='ui-multiselect-checkboxes ui-helper-reset fixed-li']/li[3]")).click();
        util.sleep();
        driver.findElement(By.xpath("//ul[@class='ui-multiselect-checkboxes ui-helper-reset fixed-li']/li[4]")).click();
        util.sleep();

        List<WebElement> selectedFilters = driver.findElements(By.xpath("//span[@class='filter-items']/div/div[1]"));
        String actSelectedFilters ="";
        String expSelectedFilters = "License Plate: All" +"Driver: All"+ "Location: All";
        for (WebElement eachElement : selectedFilters) {
            actSelectedFilters+=eachElement.getText();
        }

        Assert.assertEquals(actSelectedFilters,expSelectedFilters,"Failed to display filters");



        util.sleep();
        driver.findElement(By.xpath("//a[@data-grid-pagination-direction='next']")).click();// next arrow
        util.sleep(5);
        actPageNumber = driver.findElement(By.xpath("//input[@type='number']")).getAttribute("value");
        expPageNumber = "2";
        Assert.assertEquals(actPageNumber,expPageNumber,"Page number is not 2");
        driver.findElement(By.xpath("//a[@title='Reset']")).click();// reset button
        util.sleep(5);

        selectedFilters = driver.findElements(By.xpath("//span[@class='filter-items']/div/div[1]"));
        actSelectedFilters ="";
        expSelectedFilters = "";
        for (WebElement eachElement : selectedFilters) {
            actSelectedFilters+=eachElement.getText();
        }

        Assert.assertEquals(actSelectedFilters,expSelectedFilters,"Displayed filters");
        dropdownNumberPerPage = driver.findElement(By.xpath("//button[@class='btn dropdown-toggle ']"));
        actNumberPerPage = dropdownNumberPerPage.getText();
        expNumberPerPage = "25";
        Assert.assertEquals(actNumberPerPage,expNumberPerPage,"View per page is not 25");

        actPageNumber = driver.findElement(By.xpath("//input[@type='number']")).getAttribute("value");
        expPageNumber = "1";
        Assert.assertEquals(actPageNumber,expPageNumber,"Page number is not 1");
        /*

        Assert.assertFalse(odometerValueFilter.isDisplayed(), "Odometer value is displayed");
        Assert.assertFalse(driverFilter.isDisplayed(), "Odometer value is displayed");
        Assert.assertFalse(unitFilter.isDisplayed(), "Odometer value is displayed");

         */
    }


    @Test
    public void resetButtonOdometer(){
        driver.findElement(By.xpath("//ul[@class='nav-multilevel main-menu']/li[1]")).click();// fleet
        util.sleep();
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[1]/div/div/ul/li[4]/a/span")).click();// odometers
        util.sleep(8);
        //1st step
        List<WebElement> gridHeader = driver.findElements(By.xpath("//thead[@class='grid-header']"));
        String gridHeaderElements = "";

        for (WebElement each :gridHeader) {
            if (!each.getText().isEmpty())
                gridHeaderElements+=each.getText();
        }

        String expGridHeaderElements = "ODOMETER VALUE\nDATE\nDRIVER\nUNIT\nMODEL";
        Assert.assertEquals(gridHeaderElements,expGridHeaderElements);

        WebElement viewPerPageLabel = driver.findElement(By.xpath("//label[@class='control-label']"));
        Assert.assertTrue(viewPerPageLabel.isDisplayed(),"View per page is missing");

        WebElement filterButton = driver.findElement(By.cssSelector("a[title='Filters']"));
        Assert.assertTrue(filterButton.isDisplayed(),"Filter button is missing");

        WebElement refreshButton = driver.findElement(By.cssSelector("a[title='Refresh']"));
        Assert.assertTrue(refreshButton.isDisplayed(),"Refresh button is missing");

        WebElement resetButton = driver.findElement(By.xpath("//a[@title='Reset']"));
        Assert.assertTrue(resetButton.isDisplayed(),"Reset Button is missing");

        WebElement settingsButton = driver.findElement(By.xpath("//a[@title='Grid Settings']"));
        Assert.assertTrue(settingsButton.isDisplayed(),"Settings button is not displayed");

        WebElement dropdownNumberPerPage = driver.findElement(By.xpath("//button[@class='btn dropdown-toggle ']"));
        String actNumberPerPage = dropdownNumberPerPage.getText();
        String expNumberPerPage = "25";
        Assert.assertEquals(actNumberPerPage,expNumberPerPage,"View per page is not 25");

        String actPageNumber = driver.findElement(By.xpath("//input[@type='number']")).getAttribute("value");
        String expPageNumber = "1";
        Assert.assertEquals(actPageNumber,expPageNumber,"Page number is not 1");

        //2nd step

        driver.findElement(By.xpath("//button[@class='btn dropdown-toggle ']")).click();// view per page
        util.sleep();
        driver.findElement(By.xpath("//a[@data-size='10']")).click();// 10 odometers per page
        util.sleep(5);
        dropdownNumberPerPage = driver.findElement(By.xpath("//button[@class='btn dropdown-toggle ']"));
        actNumberPerPage = dropdownNumberPerPage.getText();
        expNumberPerPage = "10";
        Assert.assertEquals(actNumberPerPage,expNumberPerPage,"View per page is not 10");




        // 3rd step
        filterButton.click();
        WebElement menageFilters = driver.findElement(By.xpath("//a[@class='add-filter-button']"));
        Assert.assertTrue(menageFilters.isDisplayed(),"Menage Filters is missing");

        menageFilters.click();
        List<WebElement> filterElements = driver.findElements(By.xpath("//ul[@class='ui-multiselect-checkboxes ui-helper-reset fixed-li']"));
        String expFilters = "Odometer Value\n" +
                "Date\n" +
                "Driver\n" +
                "Unit\n" +
                "Model";
        String actualFilters = "";
        for (WebElement eachElement : filterElements) {
            actualFilters+=eachElement.getText();
        }
        Assert.assertEquals(actualFilters,expFilters,"Filters are not matching");
        util.sleep();

        //4 step


        driver.findElement(By.xpath("//ul[@class='ui-multiselect-checkboxes ui-helper-reset fixed-li']/li[1]")).click();// filters
        util.sleep();
        driver.findElement(By.xpath("//ul[@class='ui-multiselect-checkboxes ui-helper-reset fixed-li']/li[2]")).click();
        util.sleep();
        driver.findElement(By.xpath("//ul[@class='ui-multiselect-checkboxes ui-helper-reset fixed-li']/li[3]")).click();
        util.sleep();
        driver.findElement(By.xpath("//ul[@class='ui-multiselect-checkboxes ui-helper-reset fixed-li']/li[4]")).click();
        util.sleep();
        driver.findElement(By.xpath("//ul[@class='ui-multiselect-checkboxes ui-helper-reset fixed-li']/li[5]")).click();
        util.sleep();

        List<WebElement> selectedFilters = driver.findElements(By.xpath("//span[@class='filter-items']/div/div[1]"));
        String actSelectedFilters ="";
        String expSelectedFilters = "Odometer Value: All" +"Date: All"+ "Driver: All" + "Unit: All"+"Model: All";
        for (WebElement eachElement : selectedFilters) {
            actSelectedFilters+=eachElement.getText();
        }

        Assert.assertEquals(actSelectedFilters,expSelectedFilters,"Failed to display filters");

        WebElement odometerValueFilter = driver.findElement(By.xpath("//span[@class='filter-items']/div[1]/div[1]"));
        WebElement dateFilter = driver.findElement(By.xpath("//span[@class='filter-items']/div[2]/div[1]"));
        WebElement driverFilter = driver.findElement(By.xpath("//span[@class='filter-items']/div[3]/div[1]"));
        WebElement unitFilter = driver.findElement(By.xpath("//span[@class='filter-items']/div[4]/div[1]"));
        WebElement modelFilter = driver.findElement(By.xpath("//span[@class='filter-items']/div[5]/div[1]"));

        util.sleep();
        WebElement pageNumberRightArrow = driver.findElement(By.xpath("//a[@data-grid-pagination-direction='next']"));// next arrow
        pageNumberRightArrow.click();
        util.sleep(5);
        actPageNumber = driver.findElement(By.xpath("//input[@type='number']")).getAttribute("value");
        expPageNumber = "2";
        Assert.assertEquals(actPageNumber,expPageNumber,"Page number is not 2");




        //5th step


        resetButton=driver.findElement(By.xpath("//a[@title='Reset']"));// reset button
        resetButton.click();
        util.sleep(5);

        actPageNumber = driver.findElement(By.xpath("//input[@type='number']")).getAttribute("value");
        expPageNumber = "1";
        Assert.assertEquals(actPageNumber,expPageNumber,"Page number is not 1");

        Assert.assertFalse(odometerValueFilter.isDisplayed(), "Odometer value is displayed");
        Assert.assertFalse(dateFilter.isDisplayed(), "Date filter is displayed");
        Assert.assertFalse(driverFilter.isDisplayed(), "Driver filter  is displayed");
        Assert.assertFalse(unitFilter.isDisplayed(), "Unit filter is displayed");
        Assert.assertFalse(modelFilter.isDisplayed(), "Model filter is displayed");

        Assert.assertTrue(filterButton.isEnabled(),"Filter Button is disabled");
        Assert.assertTrue(menageFilters.isDisplayed(),"Menage Filters is missing");

    }

    @Test
    public void resetGridSettingsVehicles(){

        driver.findElement(By.linkText("Fleet")).click();
        util.sleep(2);
        //locating Vehicle and clicking
        driver.findElement(By.linkText("Vehicles")).click();
        util.sleep(10);


        String expResult="LICENSE PLATE\nTAGS\nDRIVER\nLOCATION\nCHASSIS NUMBER\nMODEL YEAR\nLAST ODOMETER\n" +
                "IMMATRICULATION DATE\nFIRST CONTRACT DATE\nCVVI\nSEATS NUMBER\nDOORS NUMBER\nCOLOR\nTRANSMISSION" +
                "\nFUEL TYPE\nCO2 EMISSIONS";
        String actualResult=driver.findElement(By.cssSelector("thead[class='grid-header']")).getText();

        Assert.assertEquals(actualResult,expResult,"Grid values do not match");


        //Locating Grid settings
        driver.findElement(By.cssSelector("a[title='Grid Settings']")).click();//goes to Grid settings

        List<WebElement>checkBoxes=driver.findElements(By.xpath("//tbody[@class='ui-sortable']/tr/td/input"));
        for(WebElement eachBox: checkBoxes){
            eachBox.click();
        }

        util.sleep();

        String vehicleDataInfoBeforeReset="ID";
        String actualResultBeforeReset=driver.findElement(By.cssSelector("thead[class='grid-header']")).getText();

        Assert.assertEquals(actualResultBeforeReset,vehicleDataInfoBeforeReset,"Grid values do not match");

        driver.findElement(By.cssSelector("a[title='Grid Settings']")).click();//goes to Grid settings
        util.sleep();
        driver.findElement(By.cssSelector("a[title='Reset']")).click();
        util.sleep(3);
        expResult="LICENSE PLATE\nTAGS\nDRIVER\nLOCATION\nCHASSIS NUMBER\nMODEL YEAR\nLAST ODOMETER\n" +
                "IMMATRICULATION DATE\nFIRST CONTRACT DATE\nCVVI\nSEATS NUMBER\nDOORS NUMBER\nCOLOR\nTRANSMISSION" +
                "\nFUEL TYPE\nCO2 EMISSIONS";
        actualResult=driver.findElement(By.cssSelector("thead[class='grid-header']")).getText();

        Assert.assertEquals(actualResult,expResult,"Grid values do not match");


    }

    @Test
    public void resetGridSettingsOdometer(){

        driver.findElement(By.linkText("Fleet")).click();
        util.sleep(2);
        //locating Vehicle and clicking
        driver.findElement(By.linkText("Vehicle Odometer")).click();
        util.sleep(10);
        //Locating Grid settings
        String actualResult=driver.findElement(By.cssSelector("thead[class='grid-header']")).getText();
        String expResult = "ODOMETER VALUE\n" +
                "DATE\n" +
                "DRIVER\n" +
                "UNIT\n" +
                "MODEL";
        Assert.assertEquals(actualResult,expResult,"Grid values do not match");

        driver.findElement(By.cssSelector("a[title='Grid Settings']")).click();//goes to Grid settings

        List<WebElement>checkBoxes=driver.findElements(By.xpath("//tbody[@class='ui-sortable']/tr/td/input"));
        for(WebElement eachBox: checkBoxes){
            eachBox.click();
        }

        util.sleep();

        String vehicleDataInfoBeforeReset="ID";
        String actualResultBeforeReset=driver.findElement(By.cssSelector("thead[class='grid-header']")).getText();

        Assert.assertEquals(actualResultBeforeReset,vehicleDataInfoBeforeReset,"Grid values do not match");

        driver.findElement(By.cssSelector("a[title='Grid Settings']")).click();//goes to Grid settings
        util.sleep();
        driver.findElement(By.cssSelector("a[title='Reset']")).click();
        util.sleep(3);
        Assert.assertEquals(actualResult,expResult,"Grid values do not match");

    }



    @Test
    public void VehicleCostTest() throws InterruptedException {


        driver.findElement(By.xpath("//ul[@class='nav-multilevel main-menu']/li[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[.='Vehicle Costs']")).click();
        Thread.sleep(10000);
        WebElement createCostButton=driver.findElement(By.xpath("//a[@title='Create Vehicle Costs']"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);
        createCostButton.click();
        Select type=new Select(driver.findElement(By.xpath("//select[@name='custom_entity_type[Type]']")));
        type.selectByValue("calculation_benefit_in_kind");
        WebElement totalPrice=driver.findElement(By.xpath("//input[@data-name='field__total-price']"));
        totalPrice.sendKeys("500");
        driver.findElement(By.xpath("//input[@placeholder='Choose a date']")).click();
        Select month=new Select(driver.findElement(By.xpath("//select[@data-handler='selectMonth']")));
        month.selectByVisibleText("Jan");
        Select year=new Select(driver.findElement(By.xpath("//select[@data-handler='selectYear']")));
        year.selectByValue("2019");
        driver.findElement(By.xpath("//button[@data-handler='today']")).click();
        driver.findElement(By.xpath("//textarea[@data-name='field__cost-descriptions']")).sendKeys("Transmission oil change");
        driver.findElement(By.xpath("//button[@class='btn btn-success action-button']")).click();
        String actualMessage=driver.findElement(By.xpath("//h5[@class='user-fieldset']")).getText();
        String expectedMessage="General Information";
        Assert.assertEquals(actualMessage, expectedMessage, "General information is not displayed. Verification Failed");

    }

    @Test
    public void fleet_verification() throws InterruptedException {
        // The XPath locators will click the vehicle odometer  under fleet module
        driver.findElement(By.xpath("//span[@class='title title-level-1']")).click();
        //The Thread.sleep suspends the current thread for 3000 milliseconds or for 3 seconds
        Thread.sleep(3000);
        // The XPath locators will click the Vehicle Odometer subModule
        driver.findElement(By.xpath("//span[.='Vehicle Odometer']")).click();
        //The Thread.sleep suspends the current thread for 3000 milliseconds or for 3 seconds
        Thread.sleep(10000);
      /* // XPath locator will click the cancel button
        driver.findElement(By.xpath("//a[@data-action='cancel']")).click();
        Thread.sleep(2000);*/
        // The XPath locators will click the create vehicle odometer
        driver.findElement(By.xpath("//div/div/a[@title='Create Vehicle Odometer']")).click();
        //The Thread.sleep suspends the current thread for 3000 milliseconds or for 3 seconds
        Thread.sleep(3000);
        // The XPath locators will get Odometer Value  element and pass the String value "123456"
        driver.findElement(By.xpath("(//div[@class='controls']/input)[1]")).sendKeys("123456");
        //The Thread.sleep suspends the current thread for 3000 milliseconds or for 3 seconds
        Thread.sleep(3000);
        // The XPath locators will find the Date (Choose a date ) and click the drop
        driver.findElement(By.xpath("//input[@placeholder='Choose a date']")).click();
        //The Thread.sleep suspends the current thread for 3000 milliseconds or for 3 seconds
        Thread.sleep(3000);
        // here we will select the date which is the second "2"
        driver.findElement(By.linkText("2")).click();
        //The Thread.sleep suspends the current thread for 3000 milliseconds or for 3 seconds
        Thread.sleep(3000);
        // The locators will find the
        // driver.findElement(By.xpath("//div[@class='control-group control-group-date']"));
        driver.findElement(By.xpath("//div/input[@name='custom_entity_type[Driver]']")).sendKeys("Ashenafi");
        //The Thread.sleep suspends the current thread for 3000 milliseconds or for 3 seconds
        Thread.sleep(3000);
       /*
        driver.findElement(By.xpath("//span [@class='select2-chosen']")).sendKeys();
        //The Thread.sleep suspends the current thread for 3000 milliseconds or for 3 seconds
        Thread.sleep(3000)
        */
        //driver.findElement(By.xpath("//div[@id='s2id_custom_entity_type_Units-uid-5f6691e3477c1']/a/span"));
        // The XPath will find  and click the Save And Close button and it will save the value that we pass
        // from our previous actions
        driver.findElement(By.xpath("//button[@class='btn btn-success action-button']")).click();
        // The Thread.sleep suspends the current thread for 3000 milliseconds or for 3 seconds
        Thread.sleep(3000);
        // The XPath will find the Cancel Button and will the action click then it will cancel
        driver.findElement(By.xpath("//div[@class='pull-left btn-group icons-holder']")).click();
        Thread.sleep(2000);
    }




}
