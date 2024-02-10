package Pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LaunchPage extends BasePage{
    public LaunchPage(AppiumDriver driver) {
        super(driver);
    }
    By skipSignInBtnID=By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button");

    public void clickOnSkipSingInBtn(){
        waitAndClick(skipSignInBtnID);
    }
}
