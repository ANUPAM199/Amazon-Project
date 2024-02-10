package tests;


import DriverManagement.BaseDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class BaseTest extends BaseDriver {
    protected AppiumDriver driver;
    @BeforeSuite
public void initSuite(){
        initAppiumService();
}
    @BeforeMethod
public void initialize() throws Exception {
        driver=initDriver();
}
    @AfterMethod
    public void tearDown(){
       quitDriver();
    }
    @AfterSuite
    public void tearDownSuite(){
        tearDownAppiumService();
    }

}
