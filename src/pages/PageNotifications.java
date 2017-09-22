package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonClasses.CommonMethods;
import io.appium.java_client.android.AndroidDriver;

public class PageNotifications {
	
	private AndroidDriver<WebElement> driver;
	private CommonMethods method;
	
	ExtentTest exTest;
	
	public PageNotifications(AndroidDriver<WebElement> driver, ExtentTest exTest ){
		
		this.driver = driver;
		this.exTest = exTest;
		PageFactory.initElements(driver, this);
		
		method = new CommonMethods(driver);
	}

	public String getTheTitle() {

		String currentTitle = "";
		
		By locatorTitle = new By.ByXPath("//android.widget.TextView[@index='0']");
		currentTitle = method.getScreenTitle(locatorTitle, 90);
		
		exTest.log(LogStatus.INFO, "Verify title...");

		return currentTitle;
	}

}
