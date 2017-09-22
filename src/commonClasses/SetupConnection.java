package commonClasses;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class SetupConnection {

	static File appDir;
	static File app;
	static protected DesiredCapabilities cap;
	static protected URL url;
	
	public static AndroidDriver<WebElement> driver;
	public static ExtentReports exReport;
	public static ExtentTest exTest;
	
	@BeforeSuite(alwaysRun = true)
	//public  AndroidDriver<WebElement> CreateCapabilities() throws MalformedURLException {
	public static void  CreateCapabilities() throws MalformedURLException {

		appDir  = new File("src");

		cap = new DesiredCapabilities();
		
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "200");

		//!!!
		//for installation of .apk
		app 	= new File(appDir, "3.37.595-app-common-debug.apk");
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		//!!!
		//only for Android to start already installed app
		//cap.setCapability("appPackage", "com.drive2");
		//cap.setCapability("appActivity", "com.drive2.activities.StartupActivity");
		
		//Do not stop app, do not clear app data, and do not uninstall apk.
		//cap.setCapability("noReset", "true");
		
		url	= new URL("http://127.0.0.1:4723/wd/hub");
		driver  = new AndroidDriver<>(url, cap);
				
		//exReport = ExtentFactory.getInstance();	
		//exTest = exReport.startTest("Chat");
		//exTest.log(LogStatus.INFO, "App opened...");
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite(){
	//	exReport.endTest(exTest);
	//	exReport.flush();
		driver.quit();
		
	}

	public AndroidDriver<WebElement> getDriver (){
		return driver;
	}
}



