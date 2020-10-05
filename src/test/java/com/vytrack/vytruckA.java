package com.vytrack;

import com.library.util;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class vytruckA {

    WebDriver driver;

    @BeforeMethod
    public void setup(){



        driver = util.getDriver();
        driver.get("https://qa2.vytrack.com/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("user153");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();
        driver.manage().window().maximize();



    }


    @Test

    public void homePage(){

        String actual = driver.getTitle();
        String exp = "Dashboard";
        Assert.assertEquals(actual,exp,"It is not on homepage");
        WebElement fleetManagement = driver.findElement(By.xpath("//h1[@class='logo logo-text']"));
        Assert.assertTrue(fleetManagement.isDisplayed(),"Fleet Management is missing");

        WebElement quickLaunchpad = driver.findElement(By.cssSelector("h1[class='oro-subtitle']"));
        Assert.assertTrue(quickLaunchpad.isDisplayed(),"Quick Launchpad is missing");

        WebElement accounts = driver.findElement(By.xpath("//a[@class='fa-suitcase widget-image']"));
        Assert.assertTrue(accounts.isDisplayed(),"Acounts is missing");

        WebElement contacts = driver.findElement(By.xpath("//a[@class='fa-users widget-image']"));
        Assert.assertTrue(contacts.isDisplayed(),"Contacts is missing");

        WebElement rightBarMenu = driver.findElement(By.xpath("//ul[@class='nav pull-right user-menu']"));
        Assert.assertTrue(rightBarMenu.isDisplayed(),"User menu is missing");

        WebElement navBar = driver.findElement(By.xpath("//div[@id='main-menu']"));
        Assert.assertTrue(navBar.isDisplayed(),"Nav bar is missing");

    }

    @Test

    public void mainTopMenu(){

        WebElement navBar = driver.findElement(By.xpath("//div[@id='main-menu']"));
        Assert.assertTrue(navBar.isDisplayed(),"Nav bar is missing");

        WebElement fleet = driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[1]/a/span"));
        Assert.assertTrue(fleet.isDisplayed(),"Fleet is missing");

        WebElement customers = driver.findElement(By.xpath("//div[@id='main-menu']/ul/li[2]"));
        Assert.assertTrue(customers.isDisplayed(),"Customers is missing");

        WebElement activities = driver.findElement(By.xpath("//div[@id='main-menu']/ul/li[3]"));
        Assert.assertTrue(activities.isDisplayed(),"Activities is missing");

        WebElement system = driver.findElement(By.xpath("//div[@id='main-menu']/ul/li[4]"));
        Assert.assertTrue(system.isDisplayed(),"Activities is missing");
    }

    @Test
    public void test(){
        driver.findElement(By.xpath("//ul[@class='nav-multilevel main-menu']/li[1]")).click();// fleet
        util.sleep();
        driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[1]/div/div/ul/li[4]/a/span")).click();// odometers
        util.sleep(15);
        List<WebElement> gridHeader = driver.findElements(By.xpath("//thead[@class='grid-header']"));
        String gridHeaderElements = "";
        System.out.println(gridHeader.size());
        for (WebElement each :gridHeader) {
            if (!each.getText().isEmpty())
                gridHeaderElements+=each.getText();
        }
        System.out.println(gridHeaderElements);
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




    }

    @Test
    public void resetButton(){
        driver.findElement(By.xpath("//ul[@class='nav-multilevel main-menu']/li[1]")).click();// fleet
        util.sleep();
        driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[1]/div/div/ul/li[4]/a/span")).click();// odometers
        util.sleep(15);

        driver.findElement(By.xpath("//button[@class='btn dropdown-toggle ']")).click();// view per page
        util.sleep();
        driver.findElement(By.xpath("//a[@data-size='10']")).click();// 10 odometers per page
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
        WebElement odometerValueFilter = driver.findElement(By.xpath("//span[@class='filter-items']/div[1]/div[1]"));
        WebElement driverFilter = driver.findElement(By.xpath("//span[@class='filter-items']/div[3]/div[1]"));
        WebElement unitFilter = driver.findElement(By.xpath("//span[@class='filter-items']/div[4]/div[1]"));
        List<WebElement> selectedFilters = driver.findElements(By.xpath("//span[@class='filter-items']/div/div[1]"));
        String actSelectedFilters ="";
        String expSelectedFilters = "Odometer Value: All" + "Driver: All" + "Unit: All";
        for (WebElement eachElement : selectedFilters) {
            System.out.println(eachElement.getText());
            actSelectedFilters+=eachElement.getText();
        }

        Assert.assertEquals(actSelectedFilters,expSelectedFilters,"Failed to display filters");

        util.sleep();
        driver.findElement(By.xpath("//a[@data-grid-pagination-direction='next']")).click();// next arrow
        util.sleep(5);
        driver.findElement(By.xpath("//a[@title='Reset']")).click();// reset button
        util.sleep(5);

        Assert.assertFalse(odometerValueFilter.isDisplayed(), "Odometer value is displayed");
        Assert.assertFalse(driverFilter.isDisplayed(), "Odometer value is displayed");
        Assert.assertFalse(unitFilter.isDisplayed(), "Odometer value is displayed");
    }
    @Test

    public void userMenu(){

        WebElement helpButton = driver.findElement(By.className("fa-question-circle"));
        Assert.assertTrue(helpButton.isDisplayed(),"Help button is missing");

        WebElement dotMenu = driver.findElement(By.xpath("//i[@class='fa-bars']"));
        Assert.assertTrue(dotMenu.isDisplayed(),"Dot menu is missing");

        WebElement mail = driver.findElement(By.xpath("//i[@class='mail-icon']"));
        Assert.assertTrue(mail.isDisplayed(),"Mail button is missing");

        WebElement userMenuDropdown = driver.findElement(By.xpath("//li[@id='user-menu']/a"));
        Assert.assertTrue(userMenuDropdown.isDisplayed(),"User menu dropdown is missing");


    }

    @AfterMethod

    public void setDown(){
        driver.close();

    }

    @Test
    public void odometerTest_1() throws InterruptedException {

        driver = util.getDriver();
        util.sleep();
        driver.get("https://qa2.vytrack.com/user/login");
        // using the input user and password verify that store manager is not authorized to see the odometer page
        driver.findElement(By.xpath("//input[@name='_username']")).sendKeys("storemanager203");
        driver.findElement(By.xpath("//input[@name='_password']")).sendKeys("UserUser123" + Keys.ENTER);
        // search and click to fleet page
        WebElement fleet = driver.findElement(By.xpath("/html/body/div[2]/div[2]/header/div[2]/ul/li[2]/a/span"));
        fleet.click();
        //search and click Odometer page
        WebElement Odometer = driver.findElement(By.xpath("//span[.='Vehicle Odometer']"));
        Thread.sleep(3000);
        Odometer.click();
        WebElement notAllowed = driver.findElement(By.xpath("//div[.='You do not have permission to perform this action.']"));
        // Assert.assertTrue(notAllowed.isDisplayed(),"You do not have permission to perform this action"); // this is one way of doing it
        String actualText = notAllowed.getText();
        String expectedTitle = "You do not have permission to perform this action.";
        Assert.assertEquals(actualText, expectedTitle, "The text expected is not the sme with the same as actual text.");

    }
/*
    @Test
    public void odometerTest_1() throws InterruptedException {

        driver = util.getDriver();
        util.sleep();
        driver.get("https://qa2.vytrack.com/user/login");
        driver.manage().window().maximize();
        // using the input user and password verify that store manager is not authorized to see the odometer page
        driver.findElement(By.xpath("//input[@name='_username']")).sendKeys("storemanager203");
        driver.findElement(By.xpath("//input[@name='_password']")).sendKeys("UserUser123" + Keys.ENTER);
        // search and click to fleet page
        WebElement fleet = driver.findElement(By.xpath("/html/body/div[2]/div[2]/header/div[2]/ul/li[2]/a/span"));
        fleet.click();
        //search and click Odometer page
        WebElement Odometer = driver.findElement(By.xpath("//span[.='Vehicle Odometer']"));
        Thread.sleep(8000);
        Odometer.click();
        WebElement notAllowed = driver.findElement(By.xpath("//div[.='You do not have permission to perform this action.']"));
        // Assert.assertTrue(notAllowed.isDisplayed(),"You do not have permission to perform this action"); // this is one way of doing it
        String actualText = notAllowed.getText();
        String expectedTitle = "You do not have permission to perform this action.";
        Assert.assertEquals(actualText, expectedTitle, "The text expected is not the sme with the same as actual text.");
        driver.close();
    }
*/


    @Test
    public void fleetModule(){
        driver.findElement(By.xpath("//ul[@class='nav-multilevel main-menu']/li[1]")).click();
        util.sleep();
        driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[1]/div/div/ul/li[3]/a/span")).click();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        util.sleep(15);
        String actualTitle = driver.getTitle();
        String expTitle = "Car - Entities - System - Car - Entities - System";
        //driver.navigate().back();
       // util.sleep(5);
        driver.findElement(By.xpath("//ul[@class='nav-multilevel main-menu']/li[1]")).click();
        util.sleep();
        driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[1]/div/div/ul/li[4]/a/span")).click();
        util.sleep(15);
        String actualOdometerTitle = driver.getTitle();
        String expOdometerTitle = "Vehicle Odometer - Entities - System - Car - Entities - System";
        //driver.navigate().back();
        //util.sleep(5);
        driver.findElement(By.xpath("//ul[@class='nav-multilevel main-menu']/li[1]")).click();
        util.sleep();
        driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[1]/div/div/ul/li[5]/a/span")).click();
        util.sleep(15);
        String actualVehicleCostsTitle = driver.getTitle();
        String expVehicleCostsTitle = "Vehicle Costs - Entities - System - Car - Entities - System";
        //driver.navigate().back();
        //util.sleep(5);
        driver.findElement(By.xpath("//ul[@class='nav-multilevel main-menu']/li[1]")).click();
        util.sleep();
        driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[1]/div/div/ul/li[6]/a/span")).click();
        util.sleep();
        WebElement rejectingMessage = driver.findElement(By.xpath("//*[@id=\"flash-messages\"]/div/div/div/div"));
        Assert.assertTrue(rejectingMessage.isDisplayed(),"Truck driver isn't allowed to access vehicle contracts");
        driver.findElement(By.xpath("//ul[@class='nav-multilevel main-menu']/li[1]")).click();
        util.sleep();
        driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[1]/div/div/ul/li[7]/a/span")).click();
        util.sleep(10);
        String actualFuelLogs = driver.getTitle();
        String expFuelLogs = "Vehicle Fuel Logs - Entities - System - Car - Entities - System";
        driver.findElement(By.xpath("//ul[@class='nav-multilevel main-menu']/li[1]")).click();
        util.sleep();
        driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[1]/div/div/ul/li[8]/a/span")).click();
        util.sleep(10);
        String actualServiceLog = driver.getTitle();
        String expServiceLog = "Vehicle Services Logs - Entities - System - Car - Entities - System";
        driver.findElement(By.xpath("//ul[@class='nav-multilevel main-menu']/li[1]")).click();
        util.sleep();
        driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[1]/div/div/ul/li[9]/a/span")).click();
        util.sleep(10);
        String actualModelPage = driver.getTitle();
        String expModelPage = "Vehicles Model - Entities - System - Car - Entities - System";


        Assert.assertEquals(actualTitle,expTitle,"Vehicle page fail to open");
        Assert.assertEquals(actualOdometerTitle,expOdometerTitle,"Odometer page fail to open");
        Assert.assertEquals(actualVehicleCostsTitle,expVehicleCostsTitle,"Vehicle costs page fail to open");

        Assert.assertEquals(actualFuelLogs,expFuelLogs,"Fuel logs page failed to open");
        Assert.assertEquals(actualServiceLog,expServiceLog,"Service Log page, failed to open");
        Assert.assertEquals(actualModelPage,expModelPage,"Model page, failed to open");


    }
/*
    @Test
    public void VehicleCostTest() throws InterruptedException {


        driver.findElement(By.xpath("//span[@class='title title-level-1']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[.='Vehicle Costs']")).click();
        Thread.sleep(3000);
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
        driver.close();
    }

*/
    @Test
    public void fleet2(){
/*
        List<String> expResults = new ArrayList<String>(9);
        expResults.set(2,"Car - Entities - System - Car - Entities - System");
        expResults.set(3,"Vehicle Odometer - Entities - System - Car - Entities - System");
        expResults.set(4,"Vehicle Costs - Entities - System - Car - Entities - System");
        expResults.set(5,"Vehicle Costs - Entities - System - Car - Entities - System");
        expResults.set(6,"Vehicle Fuel Logs - Entities - System - Car - Entities - System");
        expResults.set(7,"Vehicle Services Logs - Entities - System - Car - Entities - System");
        expResults.set(8,"Vehicles Model - Entities - System - Car - Entities - System");
*/

        driver.findElement(By.xpath("//ul[@class='nav-multilevel main-menu']/li[1]")).click();
        //util.sleep(5);
        List<WebElement> fleetElements = driver.findElements(By.xpath("//*[@id=\"main-menu\"]/ul/li[1]/div/div/ul/li"));
        System.out.println(fleetElements.size());
        String expFleetOptions = "Vehicles" +
                "Vehicle Odometer" +
                "Vehicle Costs" +
                "Vehicle Contracts" +
                "Vehicles Fuel Logs" +
                "Vehicle Services Logs" +
                "Vehicles Model";
        String actFleetOptions = "";
      for (WebElement eachElement : fleetElements) {

          System.out.println(eachElement.getText());
          actFleetOptions+=eachElement.getText();

      }

      Assert.assertEquals(actFleetOptions,expFleetOptions,"Fleet options are not matching");
      /*String actSelectedFilters ="";
        String expSelectedFilters = "Odometer Value: All" + "Driver: All" + "Unit: All";
        for (WebElement eachElement : selectedFilters) {
            System.out.println(eachElement.getText());
            actSelectedFilters+=eachElement.getText();
        }*/
/*
        for (int i = 2; i < fleetElements.size() ; i++) {
            driver.findElement(By.xpath("//ul[@class='nav-multilevel main-menu']/li[1]")).click();
            fleetElements.get(i).click();
            util.sleep(7);
            System.out.println(driver.getTitle());
            //Assert.assertEquals(driver.getTitle(),expResults.get(i));
        }*/
    }

    @Test
    public void C_FleetResetButton() throws InterruptedException{
        //locating fleet menu
        driver.findElement(By.linkText("Fleet")).click();
        Thread.sleep(2000);
        //locating Vehicle and clicking
        driver.findElement(By.linkText("Vehicles")).click();
        Thread.sleep(15000);
        //Locating Grid settings
        driver.findElement(By.cssSelector("a[title='Grid Settings']")).click();//goes to Grid settings

        List<WebElement>checkBoxes=driver.findElements(By.cssSelector("td [type='checkbox']"));
        for(WebElement eachBox: checkBoxes){
            eachBox.click();
        }
        driver.findElement(By.cssSelector("span[class='close']")).click();
        String vehicleDataInfoBeforeReset="ID";
        String actualResultBeforeReset=driver.findElement(By.cssSelector("thead[class='grid-header']")).getText();
        if(vehicleDataInfoBeforeReset.equals(actualResultBeforeReset)){
            System.out.println("Verification test Passed");
        }else {
            System.out.println("Verification test Failed");
        }
        driver.findElement(By.cssSelector("a[title='Reset']")).click();
        String vehicleDataInfo="LICENSE PLATE\nTAGS\nDRIVER\nLOCATION\nCHASSIS NUMBER\nMODEL YEAR\nLAST ODOMETER\n" +
                "IMMATRICULATION DATE\nFIRST CONTRACT DATE\nCVVI\nSEATS NUMBER\nDOORS NUMBER\nCOLOR\nTRANSMISSION" +
                "\nFUEL TYPE\nCO2 EMISSIONS\nHORSEPOWER\nHORSEPOWER TAXATION\nPOWER (KW)";
        String actualResult=driver.findElement(By.cssSelector("thead[class='grid-header']")).getText();
        if(vehicleDataInfo.equals(actualResult)){
            System.out.println("Verification test Passed");
        }else {
            System.out.println("Verification test Failed");
        }
    }

    @Test
    public void resetGridSettings(){

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
        Assert.assertEquals(actualResult,expResult,"Grid values do not match");

    }

    @Test
    public void resetButtonVehicles(){
        driver.findElement(By.linkText("Fleet")).click();
        util.sleep(2);
        //locating Vehicle and clicking
        driver.findElement(By.linkText("Vehicles")).click();
        util.sleep(10);

        driver.findElement(By.xpath("//button[@class='btn dropdown-toggle ']")).click();// view per page
        util.sleep();
        driver.findElement(By.xpath("//a[@data-size='10']")).click();// 10 odometers per page
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
        driver.findElement(By.xpath("//a[@title='Reset']")).click();// reset button
        util.sleep(5);
        /*

        Assert.assertFalse(odometerValueFilter.isDisplayed(), "Odometer value is displayed");
        Assert.assertFalse(driverFilter.isDisplayed(), "Odometer value is displayed");
        Assert.assertFalse(unitFilter.isDisplayed(), "Odometer value is displayed");

         */
    }


    @Test
    public void customersModule(){
        
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
        Thread.sleep(8);
    }









}
