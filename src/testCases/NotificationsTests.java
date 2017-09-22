package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonClasses.ExtentFactory;
import commonClasses.SetupConnection;
import pages.LoginByEmailPage;
import pages.LoginPage;
import pages.MainMenu;
import pages.PageNotifications;

public class NotificationsTests  extends SetupConnection {

	ExtentTest exTestParent;
	ExtentReports exReport;
	
	@BeforeClass(alwaysRun = true)
    public void beforeClass() throws InterruptedException{
		exReport = ExtentFactory.getInstance();
		exTestParent = exReport.startTest("Notifications");
		
		//For debugging
		exTest = exReport.startTest("Login by Email");
		exTestParent.appendChild(exTest);

		LoginByEmailPage loginByEmail = new LoginPage(driver, exTest).clickOnBtnEmail();

		loginByEmail.typeEmail("dforsp@gmail.com")
				.typePassword("qAlityAssurance")
				.clickOnButtonEnter(false); //homePage will be null because of wrong credentials
	
		//Thread.sleep(2000);
	}	

	@AfterClass(alwaysRun = true)
    public void afterClass(){
		exReport.endTest(exTestParent);
		exReport.flush();
	}	

	@Test(groups = { "Notifications" }, priority = 30)
	public void verifyThatNotificationsIsOpened() throws InterruptedException {

		String ExpectedTitle = "Уведомления";

		MainMenu mainMenu = new MainMenu(driver, exTest);

		PageNotifications pageNotifications = mainMenu.clickOnBtnBell();

		String currentTitle = pageNotifications.getTheTitle();

		Assert.assertEquals(ExpectedTitle, currentTitle);
		exTest.log(LogStatus.PASS, "Verifed That Notifications is opened");
	}

}
