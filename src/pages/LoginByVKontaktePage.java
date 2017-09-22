package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonClasses.CommonMethods;
import io.appium.java_client.android.AndroidDriver;

public class LoginByVKontaktePage {

	AndroidDriver<WebElement> driver;
	CommonMethods method;
	ExtentTest exTest;
	
	public LoginByVKontaktePage(AndroidDriver<WebElement> driver, ExtentTest exTest) {
		
		this.driver = driver;
		this.exTest = exTest;
		method = new CommonMethods(driver);
		
	}
	public LoginByVKontaktePage typeEmail(String email){
		
		try{
			By elementEmail = By.className("android.widget.EditText");////////////////////
			method.waitForElements(elementEmail, 30).get(0).sendKeys(email);		
			exTest.log(LogStatus.INFO, "Typed email...");
	
		} catch (Exception e){
			exTest.log(LogStatus.ERROR, "Couldn't type email!");
		}

		return this;
	}
	
	public LoginByVKontaktePage typePassword(String password) {

		try {
			By elementPassword = By.className("android.widget.EditText");
			exTest.log(LogStatus.INFO, "Typed password...");

			method.waitForElements(elementPassword, 30).get(1).sendKeys(password);

		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Couldn't type password!");
		}

		return this;
	}

	public HomePage clickOnButtonLogin() throws InterruptedException{
		
		HomePage homePage = null;
		
		try{
			By btnLogIn = new By.ByXPath("//android.widget.Button[@content-desc='Log in']");
			method.waitForElement(btnLogIn, 30).click();
			homePage = new HomePage(driver, exTest);
			
		}catch(Exception e){
			exTest.log(LogStatus.ERROR, "Button LogIn on page VKontacte is not found!");		
		}

		//Thread.sleep(2000);

		return homePage;
	}

	public LoginByVKontaktePage clickOnForgotPassword(){
		return this;
		
	}

	public LoginPage clickOnClose(){

		WebElement btnClose = driver.findElement(By.id("com.drive2:id/startup_web_close_button"));
		btnClose.click();
		
		LoginPage loginPage = new LoginPage(driver, exTest);

		return loginPage;
		
	}

}
