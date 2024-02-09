package DriverManagement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseDriver {
    AndroidDriver driver;
    AppiumDriverLocalService appiumService;
    
    public AndroidDriver initAndroidDriver() throws MalformedURLException {
        initAppiumService();
driver=new AndroidDriver(new URL("http://127.0.0.1:4723"),getDesiredCapabilities());

        return driver;
    }



    public static DesiredCapabilities getDesiredCapabilities(){
DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
desiredCapabilities.setCapability("platformName","Android");
desiredCapabilities.setCapability("automationName","UIAutomator2");
desiredCapabilities.setCapability("PlatformVersion","12");
desiredCapabilities.setCapability("app",System.getProperty("user.dir")+"/src/test/resources/app/amazon-shopping-28-3-0-100.apk");
desiredCapabilities.setCapability("newCommandTimeout",120);
desiredCapabilities.setCapability("noRest",false);

        return desiredCapabilities;
    }
    public void initAppiumService() {
        appiumService=AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
                .withAppiumJS(new File("C:/Users/hp/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
                .withLogFile(new File(System.getProperty("user.dir") + "/appiumServiceLog.txt"))
                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE));

        appiumService.start();

    }
    public void quitDriver(){
        driver.quit();
        System.out.println("Driver has quit");
    }
    public void tearDownAppiumService(){

        appiumService.stop();
    }

}

