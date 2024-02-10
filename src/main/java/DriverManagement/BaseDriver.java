package DriverManagement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class BaseDriver {
    AppiumDriver driver;
    AppiumDriverLocalService appiumService;
    static String APPIUM_SERVER = "http://127.0.0.1:4723"; // Updated Appium server URL
    static String ANDROID = "android";
    static String IOS = "ios";
    protected static Properties prop = new Properties();

    public AppiumDriver initDriver() throws Exception {
        loadPropertyFile();

        if (prop.getProperty("platform").equals(ANDROID)) {
            driver = new AndroidDriver(new URL(APPIUM_SERVER), getDesiredCapabilities(ANDROID));
        } else if (prop.getProperty("platform").equals(IOS)) {
            driver = new IOSDriver(new URL(APPIUM_SERVER), getDesiredCapabilities(IOS));
        } else {
            throw new IllegalArgumentException("Platform name " + prop.getProperty("platform") + " is not valid");
        }
        return driver;
    }

    public void loadPropertyFile() throws IOException {
        InputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/application.properties");
        //Load properties file
        prop.load(input);
    }

    public Properties getApplicationProperties() {
        return prop;
    }

    public static DesiredCapabilities getDesiredCapabilities(String platform) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformVersion", prop.getProperty("platformVersion"));
        if (platform.equals(ANDROID)) {
            desiredCapabilities.setCapability("automationName", "UIAutomator2");
            desiredCapabilities.setCapability("platformName", "Android");
            desiredCapabilities.setCapability("app", System.getProperty("user.dir") + "/src/test/resources/app/amazon-shopping-28-3-0-100.apk");
        } else if (platform.equals(IOS)) {
            desiredCapabilities.setCapability("automationName", "XCUITest"); // Corrected automationName
            desiredCapabilities.setCapability("platformName", "iOS");
            desiredCapabilities.setCapability("udid", "00008030-000128680A04C02E");
            desiredCapabilities.setCapability("bundleId", "com.amazon.Amazon");
        } else {
            throw new IllegalArgumentException("Platform " + platform + " is not a valid value");
        }

        desiredCapabilities.setCapability("newCommandTimeout", 120);
        desiredCapabilities.setCapability("noReset", false);
        return desiredCapabilities;
    }

    public void initAppiumService() {
        appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
                .withAppiumJS(new File("C:/Users/hp/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
                .withLogFile(new File(System.getProperty("user.dir") + "/src/main/resources/appiumServiceLog.txt"))
                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE));

        appiumService.start();
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver has quit");
        }
    }

    public void tearDownAppiumService() {
        if (appiumService != null) {
            appiumService.stop();
        }
    }
}
