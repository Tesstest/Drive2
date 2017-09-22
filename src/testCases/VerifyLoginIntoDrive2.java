package testCases;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonClasses.ExtentFactory;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import pages.LoginByEmailPage;
import pages.LoginByVKontaktePage;
import pages.LoginPage;
import pages.MainMenu;
import pages.HomePage;
import pages.PageMore;

public class VerifyLoginIntoDrive2 {
	private File appDir;
	private File app;
	private DesiredCapabilities cap;
	private AndroidDriver<WebElement> driver;
	ExtentReports exReport;
	ExtentTest exTest;
	
	public VerifyLoginIntoDrive2 (AndroidDriver<WebElement> driver){
		
		this.driver = driver;
		
	}
	
	@BeforeClass(alwaysRun = true)
	public void CreateCapabilities() throws MalformedURLException{
	
		appDir  = new File("src");
		app 	= new File(appDir, "3.37.595-app-common-debug.apk");

		cap = new DesiredCapabilities();
		
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "200");
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		URL url	= new URL("http://127.0.0.1:4723/wd/hub");
		driver  = new AndroidDriver<>(url, cap);

		exReport = ExtentFactory.getInstance();	
		exTest = exReport.startTest("Verify chat");
		
		exTest.log(LogStatus.INFO, "App opened...");
	
	}

/* it doesn't work here
  	@BeforeGroups
	public void open() throws MalformedURLException{
		URL url	= new URL("http://127.0.0.1:4723/wd/hub");
		driver  = new AndroidDriver<>(url, cap);

	}
*/	
	@AfterTest
	public void AfterTestQuit() {
		System.out.println("driver quit");
		driver.quit();
	}
	
/*	@Test//(groups = {"email"}, priority = 1)
	public void VerifyLoginByEmailIsPerformed(){

		boolean carOfTheDayIsPresent = false;
		
		LoginByEmailPage loginByEmail = new LoginPage(driver, exTest).clickOnBtnEmail();
		
		HomePage mainPage = loginByEmail
		.typeEmail("dforsp@gmail.com")
		.typePassword("qAlityAssurance")
		.clickOnButtonEnter();
		
		carOfTheDayIsPresent = mainPage.carOfTheDayIsPresent();
		Assert.assertEquals(carOfTheDayIsPresent, true);
		
	}*/
	
	@Test //(groups = {"email"}, priority = 0 )
	public void VerifyRejectLoginByEmailIsPerformed(){

		boolean btnEmailIsPresent = false;
		
		LoginPage loginByEmail = new LoginPage(driver, exTest)
				.clickOnBtnEmail()
				.clickOnReturn();
		
		btnEmailIsPresent = loginByEmail.btnEmailIsPresent();
		Assert.assertEquals(btnEmailIsPresent, true);
		
	}
	
	@Test//(groups = {"email", "vKontakte", "facebook"}, priority = 2)
	public void VerifyLogoutFromAppIsPerformed(){
		
		boolean btnEmailIsPresent = false;
		
		PageMore pageMore= new MainMenu(driver, exTest).clickOnBtnSandwich();
		
		LoginPage loginPage = pageMore.exit();
		
		btnEmailIsPresent = loginPage.btnEmailIsPresent();
		Assert.assertEquals(btnEmailIsPresent, true);
	}	

	@Test (enabled = false)//(groups = {"vKontakte"}, priority = 3)
	public void VerifyRejectLoginByVKontakteIsPerformed(){

		boolean btnEmailIsPresent = false;
		
		LoginPage loginPage = new LoginPage(driver, exTest);
		LoginByVKontaktePage loginVKPage = loginPage.clickOnBtnVK();
		
		loginPage = loginVKPage.clickOnClose();
		
		btnEmailIsPresent = loginPage.btnEmailIsPresent();
		
		Assert.assertEquals(btnEmailIsPresent, true);
		
	}

/*	@Test (groups = {"facebook"}, priority = 4)
	public void VerifyRejectLoginByFacebookIsPerformed(){

		boolean btnEmailIsPresent = false;
		
		btnEmailIsPresent = new LoginPage(driver)
				.clickOnBtnFB()
				.closePageFB()
				.btnEmailIsPresent();
		
		Assert.assertEquals(btnEmailIsPresent, true);
		
	}
*/
	
/*	@Test//(groups = {"vKontakte"}) 
	public void VerifyLoginByVKontakteIsPerformed() throws InterruptedException {
	
		boolean carOfTheDayIsPresent = false;
				
		LoginByVKontaktePage loginByVK = new LoginPage(driver, exTest).clickOnBtnVK();
		//
		//System.out.println(driver.getContext());
		//Set<String> set1 = driver.getContextHandles();
		
		//for (String i : set1){
		//	System.out.println("Login VK " + i);
		//}
		
		HomePage mainPage = loginByVK
		.typeEmail("dforshop@gmail.com")
		.typePassword("qAlityAssurance17")
		.clickOnBottonLogIn();

		Thread.sleep(10000);

		//Set<String> set2 = driver.getContextHandles();
		
		//for (String i : set2){
		//	System.out.println("Main page " + i);
		//}

		
		if (mainPage.TitleIsPresent() == true){
			//
			System.out.println("Current activity: " + driver.currentActivity());
			System.out.println("Get context: " + driver.getContext());
			
			
			carOfTheDayIsPresent = mainPage.CarOfTheDayIsPresent();
		}
		

		//CarOfTheDayIsPresent
		
		driver.pressKeyCode(AndroidKeyCode.BACK);
		Assert.assertEquals(carOfTheDayIsPresent, true);
		
	}
*/
	//disabled tests-----------------

	@Test//(enabled = false, groups = {"facebook"})
	public void VerifyLoginByFacebookIsPerformed(){

	}

	
}
