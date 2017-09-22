package pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonClasses.CommonMethods;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class HomePage {

	AndroidDriver<WebElement> driver;
	CommonMethods method;
	ExtentTest exTest;
	
	WebElement elCarOfTheDay = null;

/*	@FindBy(xpath = "//android.view.View[contains(@content-desc, 'Машина дня')]")
	@CacheLookup
	private WebElement carOfTheDay;

*/	/*
	 * @FindBy (xpath="//android.widget.TextView[@text = 'DRIVE2.RU']")
	 * 
	 * @CacheLookup private WebElement title;
	 */

	public HomePage(AndroidDriver<WebElement> driver, ExtentTest exTest) {

		this.driver = driver;
		this.exTest = exTest;
		method = new CommonMethods(driver);
		PageFactory.initElements(driver, this);

		// waitForPageToLoad();
	}

	/*
	 * private boolean isSearchElementDisplayed(){ try { return
	 * carOfTheDay.isDisplayed(); } catch (NoSuchElementException e){ return
	 * false; } }
	 */
	/*
	 * private void waitForPageToLoad() { int secondsCount = 0; boolean
	 * isPageOpenedIndicator = isSearchElementDisplayed(); while
	 * (!isPageOpenedIndicator && secondsCount < 10) { try { Thread.sleep(1000);
	 * System.out.println(" wait for secondsCount " + secondsCount); } catch
	 * (InterruptedException e) { e.printStackTrace(); } secondsCount++;
	 * isPageOpenedIndicator = isSearchElementDisplayed(); } if
	 * (!isPageOpenedIndicator) { throw new
	 * AssertionError("Page was not opened"); } }
	 */

	/*
	 * public boolean titleIsPresent() {
	 * 
	 * boolean titleIsPresent = false;
	 * 
	 * By title = new
	 * By.ByXPath("//android.widget.TextView[@text = 'DRIVE2.RU']");
	 * 
	 * try { WebElement elTitle = method.waitForElement(title, 80);
	 * titleIsPresent = elTitle.isDisplayed();
	 * System.out.println("Страница Главная загружена " + elTitle.getText()); }
	 * catch (Exception e) { By currentTitle = new
	 * By.ByXPath("//android.widget.TextView"); System.out.println(
	 * "Страница Главная не загружена. Активна страница - " +
	 * driver.findElement(currentTitle).getText()); } return titleIsPresent; }
	 */

	public WebElement getCarOfTheDay() {

		//WebElement elCarOfTheDay = null;

		Set<String> availableContexts = driver.getContextHandles();
		for (String contextName : availableContexts) {
			System.out.println(contextName);
			
			if (contextName.contains("WEBVIEW")){
				driver.context(contextName);
				
				try {
					By carOfTheDay = new By.ByXPath("//div[@class='c-cotd']//a[1]");
					elCarOfTheDay = method.waitForElement(carOfTheDay, 60);
					
					exTest.log(LogStatus.INFO, "Car of the day is found...");

				} catch (Exception e) {
					exTest.log(LogStatus.ERROR, "Car of the day is NOT found!");
					System.out.println("Элемент Машина дня не найден");
				}
				break;
			}
		}

		return elCarOfTheDay;
	}

	public boolean carOfTheDayIsPresent() {
		boolean carOfTheDayIsPresent = false;

		// USEFUL for logs
		//String className = this.getClass().getName();
		//String currentMethod = Thread.currentThread().getStackTrace()[1].getMethodName();

		try {
			String textCarOfTheDay = getCarOfTheDay().getAttribute("innerHTML");
			
			if (textCarOfTheDay.contains("Машина дня")){
				carOfTheDayIsPresent = true;

				exTest.log(LogStatus.INFO, "Verified text 'Машина Дня'...");
			} else {
				exTest.log(LogStatus.ERROR, "Text 'Машина Дня' is not found...");
			}
		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Element Car Of The Day is not found...");
		}

		return carOfTheDayIsPresent;
	}

	public String getNameCarOfTheDay(WebElement elCar) {

		String nameCarOfTheDay = "";

		try {
		
			//String wholeTextCarOfTheDay = getCarOfTheDay().getAttribute("innerHTML");
			String wholeTextCarOfTheDay = elCar.getAttribute("innerHTML");
			char ch  = '>';
			int beginIndex = wholeTextCarOfTheDay.lastIndexOf(ch)+1;
			nameCarOfTheDay = wholeTextCarOfTheDay.substring(beginIndex).trim();
			
			exTest.log(LogStatus.INFO, "Name of the car is " + nameCarOfTheDay);
			System.out.println(nameCarOfTheDay);

		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Couldn't get name of the car!");
			System.out.println("Couldn't get name of the car");
		}
		return nameCarOfTheDay;
	}

	public CarPage clickOnCarOfTheDay(WebElement elCar) throws InterruptedException {

		CarPage carPage = null;

		try {

			//getCarOfTheDay().click();
			elCar.click();
			carPage = new CarPage(driver, exTest);

			exTest.log(LogStatus.INFO, "Tapped on the car of the day...");

		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Couldn't click on the car of the day!");
			System.out.println("Couldn't click on the car of the day!");
		}
		Thread.sleep(2000);
		return carPage;
	}

	public HomePage swipeDown() {
		Dimension screenSize;
		screenSize = driver.manage().window().getSize();
		int startx = screenSize.width / 2;
		int starty = (int) (screenSize.height * 0.80);
		int endy = (int) (screenSize.height * 0.20);
		driver.swipe(startx, endy, startx, starty, 1000);
		return this;
	}

	public String getTheTitle(){
		
		String className = this.getClass().getName();
		String currentTitle = "";
		
		try{		
			By locatorTitle = new By.ByXPath("//android.widget.TextView[@index='0']");
			currentTitle = method.getScreenTitle(locatorTitle, 90);
			exTest.log(LogStatus.INFO, "Verified title of the page. Title is " + currentTitle);
		
		} catch(Exception e){
			exTest.log(LogStatus.ERROR, "Couldn't get the title of the page " + className);
			
		}
		return currentTitle;
	}

}
