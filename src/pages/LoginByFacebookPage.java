package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonClasses.CommonMethods;
import io.appium.java_client.android.AndroidDriver;

public class LoginByFacebookPage {

	AndroidDriver<WebElement> driver;
	CommonMethods method;
	ExtentTest exTest;
	
	public LoginByFacebookPage(AndroidDriver<WebElement> driver, ExtentTest exTest) {
		this.driver = driver;
		this.exTest = exTest;
		method = new CommonMethods(driver);
	}

	public LoginByFacebookPage typeEmail(String email) {

		try {
			By elementEmail = By.xpath("//android.widget.EditText[contains(@text, 'Email')]");
			method.waitForElement(elementEmail, 30).sendKeys(email);
			driver.hideKeyboard();

			exTest.log(LogStatus.INFO, "Typed email...");

		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Couldn't type email!");
		}

		return this;
	}
	
	public LoginByFacebookPage typePassword(String password){

		try {
			By elementPassword = By.xpath("//android.widget.EditText[contains(@text, 'Password')]");
			method.waitForElement(elementPassword, 30).sendKeys(password);		
			driver.hideKeyboard();
			
			exTest.log(LogStatus.INFO, "Typed password...");

		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Couldn't type password!");
		}

		return this;
	}

	public HomePage clickOnButtonLogin() throws InterruptedException {

		HomePage homePage = null;
		
		try {
			By elButtonLogIn = new By.ByXPath("//android.widget.Button[contains(@content-desc,'Log')]");
			method.waitForElement(elButtonLogIn, 60).click();
			exTest.log(LogStatus.INFO, "Tapped on button Log In on Facebook Page...");
		
			//Sometimes we need addition action - tap on button Continue (depends on FB settings)
			try {
				By elButtonContinue = new By.ByXPath("//android.widget.Button");
				method.waitForElement(elButtonContinue, 60).click();
				exTest.log(LogStatus.INFO, "Tapped on button Continue on Facebook Page...");

				homePage = new HomePage(driver, exTest);
				
			} catch (Exception e){
				homePage = new HomePage(driver, exTest);
			}
		
		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Button log In on Facebook Page is not found!");
		}
		
		return homePage;
		
/*		String currentTitle = "";
		By elButtonLogIn = new By.ByXPath("//android.widget.Button[contains(@content-desc,'Log')]");

		method.waitForElement(elButtonLogIn, 60).click();

		Thread.sleep(1000);

		try {
			By locatorTitle = new By.ByXPath("//android.widget.TextView[@index='0']");
			currentTitle = method.getScreenTitle(locatorTitle, 20);

		} catch (Exception ex1) {
			System.out.println("Couldn't find the Home page.....Try to continue log in by Facebook");

			try {
				System.out.println("Continue try to log in by Facebook");

				By elButtonContinue = new By.ByXPath("//android.widget.Button");
				method.waitForElement(elButtonContinue, 60).click();
				Thread.sleep(3000);

				By locatorTitle = new By.ByXPath("//android.widget.TextView[@index='0']");
				currentTitle = method.getScreenTitle(locatorTitle, 90);

			} catch (Exception ex2) {

				exTest.log(LogStatus.ERROR, "Can't log in by Facebook");

				System.out.println("Can't log in by Facebook");
			}
		}

		return currentTitle;
*/
		}

	public LoginByFacebookPage clickOnForgotPassword() {
		return this;

	}

}
