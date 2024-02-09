package tests.testcases;

import Pages.LaunchPage;
import org.testng.annotations.Test;
import tests.BaseTest;

public class HomePageTest extends BaseTest {
    LaunchPage launchPage;
    @Test
    public void validateHomePageTitle() throws InterruptedException {
        launchPage=new LaunchPage(this.driver);
        launchPage.clickOnSkipSingInBtn();
        System.out.println("First message");
        Thread.sleep(3000);
    }
}
