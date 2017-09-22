package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonClasses.CommonMethods;
import io.appium.java_client.android.AndroidDriver;

public class CarPage {
	
	private AndroidDriver<WebElement> driver;
	private CommonMethods method;
	
	ExtentTest exTest;
	
	public CarPage (AndroidDriver<WebElement> driver, ExtentTest exTest ){
		
		this.driver = driver;
		this.exTest = exTest;
		PageFactory.initElements(driver, this);
		
		method = new CommonMethods(driver);
	}

	public String getTheTitle(){

		String currentTitle = "";
				
		By locatorTitle = new By.ByXPath("//android.widget.TextView[@index='0']");
		currentTitle = method.getScreenTitle(locatorTitle, 90);
		
		exTest.log(LogStatus.INFO, "Verify title...");

		return currentTitle;
	}

	public String getNameOfTheCar() {

		String nameOfTheCar = "";

		try {
			By car = new By.ByXPath("//h1[@class = 'c-car-info__caption']");
			nameOfTheCar = method.waitForElement(car, 60).getText().trim();

			exTest.log(LogStatus.INFO, "Got name of the car...");

		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Couldn't get name of the car!");
			System.out.println("Couldn't get name of the car!");
		}
	
		return nameOfTheCar;

	}
}