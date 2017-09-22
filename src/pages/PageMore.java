package pages;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import commonClasses.CommonMethods;
import io.appium.java_client.android.AndroidDriver;

public class PageMore {
	AndroidDriver<WebElement> driver;
	CommonMethods method;
	ExtentTest exTest;
	
	public PageMore(AndroidDriver<WebElement> driver, ExtentTest exTest){

		this.driver = driver;
		this.exTest = exTest;
		method = new CommonMethods(driver);
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//android.widget.TextView[@text='Выход']")
	private WebElement exit;
	
	public LoginPage exit(){
		
		LoginPage loginPage = null;
		Dimension screenSize; 
		screenSize = driver.manage().window().getSize();
		
		int startx = screenSize.width/2;
		int starty = (int) (screenSize.height *0.80);
		int endy = (int) (screenSize.height *0.20);
		
		driver.swipe(startx, starty, startx,  endy, 5000);
		driver.swipe(startx, starty, startx,  endy, 2000);
		driver.swipe(startx, starty, startx,  endy, 2000);
		driver.swipe(startx, starty, startx,  endy, 2000);
		driver.swipe(startx, starty, startx,  endy, 2000);
		driver.swipe(startx, starty, startx,  endy, 2000);
		driver.swipe(startx, starty, startx,  endy, 2000);

		
		WebElement elExit = method.waitForVisibilityOfElement(exit, 60);
		
		try{
			elExit.click();
			loginPage = new LoginPage(driver, exTest);
								
		} catch (Exception e){
			
			System.out.println("Button -Выход- is not found!");
			
		}
		
		return loginPage;
		
	}

}
