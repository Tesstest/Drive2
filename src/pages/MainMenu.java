package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonClasses.CommonMethods;
import io.appium.java_client.android.AndroidDriver;

public class MainMenu {

	AndroidDriver<WebElement> driver;
	CommonMethods method;
	ExtentTest exTest;
	
	@FindBy (xpath="(//android.widget.ImageView)[1]")
	@CacheLookup
	private WebElement btnHome;
	
	@FindBy (xpath="(//android.widget.ImageView)[2]")
	@CacheLookup
	private WebElement btnNewsFeed;

	@FindBy (xpath="(//android.widget.ImageView)[3]")
	@CacheLookup
	private WebElement btnChat;

	@FindBy (xpath="(//android.widget.ImageView)[4]")
	@CacheLookup
	private WebElement btnBell;

	@FindBy (xpath="(//android.widget.ImageView)[5]")
	@CacheLookup
	private WebElement btnSandwich;

	public MainMenu(AndroidDriver<WebElement> driver, ExtentTest exTest) {
		this.driver = driver;
		this.exTest = exTest;
		method = new CommonMethods(driver);

		PageFactory.initElements(driver, this);
	}

	public HomePage clickOnBtnHome() {
		//Have to tap twice to make sure that upload an initial page (cleaning from previous action)  
		
		HomePage homePage = null;
		try {
			WebElement elBtnHome = method.waitForVisibilityOfElement(btnHome, 60);
			elBtnHome.click();

			//Have to tap twice to make sure that upload an initial page (cleaning from previous action)  
			elBtnHome.click();
			
			homePage = new HomePage(driver, exTest);

			exTest.log(LogStatus.INFO, "Tapped on button Home");
	
			
		} catch (Exception e) {
			System.out.println(" нопка Home не найдена");
			exTest.log(LogStatus.ERROR, "Button Home is not found!");
		}
		return homePage;
	}	
	
	public PageChat clickOnBtnChat() {
		
		PageChat pageChat = null;
		
		try {
			WebElement elBtnChat = method.waitForVisibilityOfElement(btnChat, 60);
			elBtnChat.click();
			pageChat = new PageChat(driver, exTest);
		
			exTest.log(LogStatus.INFO, "Tapped on button Chat");
	
		} catch (Exception e) {
			System.out.println(" нопка Chat не найдена");
			
			exTest.log(LogStatus.ERROR, "Button Chat is not found!");
		}
		return pageChat;
	}	

	public PageNewsFeed clickOnBtnNewsFeed() {

		PageNewsFeed pageNewsFeed = null;

		try {
			WebElement elBtnNewsFeed = method.waitForVisibilityOfElement(btnNewsFeed, 60);
			elBtnNewsFeed.click();
			pageNewsFeed = new PageNewsFeed(driver, exTest);

			exTest.log(LogStatus.INFO, "Tapped on button News Feed...");

		} catch (Exception e) {
			System.out.println(" нопка News Feed не найдена!");

			exTest.log(LogStatus.ERROR, "Button News Feed is not found!");
		}
	
		return pageNewsFeed;

	}

	public PageNotifications clickOnBtnBell() {
		
		PageNotifications pageNotifications = null;
		
		try {
			WebElement elBtnBell = method.waitForVisibilityOfElement(btnBell, 60);
			elBtnBell.click();
			pageNotifications = new PageNotifications(driver, exTest);
		
			exTest.log(LogStatus.INFO, "Tapped on button Bell...");
	
		} catch (Exception e) {
			System.out.println(" нопка Bell не найдена!");
			
			exTest.log(LogStatus.ERROR, "Button Bell is not found!");
		}
		return pageNotifications;
	}	

	public PageMore clickOnBtnSandwich() {
		PageMore pageMore = null;
		try {
			WebElement elBtnSandwich = method.waitForVisibilityOfElement(btnSandwich, 90);
			elBtnSandwich.click();
			pageMore = new PageMore(driver, exTest);

			exTest.log(LogStatus.INFO, "Tapped on button Sandwich");
			
		} catch (Exception e) {
		
			System.out.println(" нопка Sandwich не найдена");
			exTest.log(LogStatus.ERROR, "Button Sandwich is not found!");
		}
		return pageMore;
	}	
}
