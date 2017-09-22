package commonClasses;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import pages.LoginPage;

public class LoginInto {
	
	private AndroidDriver<WebElement> driver;
	ExtentTest exTest;
	
	public LoginInto(AndroidDriver<WebElement> driver){
		this.driver = driver;
	}
	
	LoginPage loginByEmail = new LoginPage(driver, exTest)
			.clickOnBtnEmail()
			.clickOnReturn();


}
