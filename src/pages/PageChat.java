package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonClasses.CommonMethods;
import io.appium.java_client.android.AndroidDriver;

public class PageChat {

	private AndroidDriver<WebElement> driver;
	private CommonMethods method;
	
	ExtentTest exTest;
	
	public PageChat(AndroidDriver<WebElement> driver, ExtentTest exTest ){
		
		this.driver = driver;
		this.exTest = exTest;
		PageFactory.initElements(driver, this);
		
		method = new CommonMethods(driver);
	}

	@FindBy (xpath="//android.widget.TextView[contains(@text, 'Сообщения')]")
	WebElement title;
	
	@FindBy (id="com.drive2:id/item_chat_title")
	WebElement userDialog;

	public String getTheTitle(){

		String currentTitle = "";
				
		By locatorTitle = new By.ByXPath("//android.widget.TextView[@index='0']");
		currentTitle = method.getScreenTitle(locatorTitle, 90);
		
		exTest.log(LogStatus.INFO, "Verify title...");

		return currentTitle;
	}

	public PageChat tapOnUser(String serviceUserName) {
		
		try {
			WebElement elUser = driver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + serviceUserName + "\"));");
			elUser.click();

			exTest.log(LogStatus.INFO, "Tap on dialog with service-user...");
			
		} catch (Exception e) {
			String textError = "Could not find service-user " + serviceUserName;
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);
		}
	
		return this;
		
	/*	("new UiScrollable(new UiSelector()"
						+"resourceID(\"com.drive2:id/item_chat_title\")).scrollIntoView("
						+"new UiSelector().text(\"BusinessSupport\"));").click();
		
	*/	
		
/*		WebElement elUser = method.waitForElements(locator, timeout)
				
		TouchAction action = new TouchAction(driver);
		action.moveTo(elUser);
*/		
		
/*		By userLocator  = By.xpath("android.widget.TextView");
		System.out.println(".. PageChat.tapOnUser .... try to find");
		List<WebElement> users = method.waitForElements(userLocator, 90);
		WebElement serviceUser = null;

		System.out.println("length is " + users.size());
		
		for (WebElement user : users){
			String userName = user.getText();
			
			System.out.println("Usr name is " + userName);
			
			if( userName == serviceUserName){
				serviceUser = user;
				break;
			}
			else{
				exTest.log(LogStatus.ERROR, "Service user is not found!");
				System.out.println("Service user is not found!");
			}	
		
		}*/	
		
		
		
//		String stringXpath = "//android.widget.TextView and @text = " + userName;
//		By dialogWithUser = By.xpath(stringXpath);
//		WebElement elDialogWithUser = method.waitForElement(dialogWithUser, 60);
//		elDialogWithUser.click();
		
		//WebElement elUserDialog = method.waitForVisibilityOfElement(userDialog, 60);
		//elUserDialog.click();
		
	}

	public PageChat tapOnButtonSendMessage() {

		try {
			By btnSend = new By.ById("com.drive2:id/chat_send_button");
			method.waitForElement(btnSend, 20).click();

			exTest.log(LogStatus.INFO, "Tap on button Send message...");
			
		} catch (Exception e) {
			String textError = "Could not type a message ";
			System.out.println(textError);
			exTest.log(LogStatus.ERROR, textError);
		}
		return this;
	}

	public PageChat typeTextMessage(String text) {

		try {
			By locatorMessage = new By.ById("com.drive2:id/chat_message");
			method.waitForElement(locatorMessage, 20).sendKeys(text);

			exTest.log(LogStatus.INFO, "Type text-message...");
			
		} catch (Exception e) {
			String textError = "Could not type a message ";
			System.out.println(textError);
			exTest.log(LogStatus.ERROR, textError);
		}

		return this;
		
	}

	public String findSentMessage(String text){
		
		String sentMessage = "";
		try {
			By locatorSentMessage = new By.ByXPath("//android.widget.TextView[@resource-id = 'com.drive2:id/chat_message_text' "
					+ "and @text = '" + text + "']");
			sentMessage = method.waitForElement(locatorSentMessage, 60).getText();

			exTest.log(LogStatus.INFO, "Find text-message in sent messages...");
			
		} catch (Exception e) {
			String textError = "Could not type a message ";
			System.out.println(textError);
			exTest.log(LogStatus.ERROR, textError);
		}
		
	return sentMessage;	

	}

	public PageChat tapOnArrowBack() {

		try {
			By btnSend = new By.ByXPath("//android.widget.ImageButton[@content-desc = 'Navigate up']");
			method.waitForElement(btnSend, 20).click();
System.out.println("Find arrow");
			exTest.log(LogStatus.INFO, "Tap on arrow back...");
			
		} catch (Exception e) {
			String textError = "Could not tap on arrow back!";
			System.out.println(textError);
			exTest.log(LogStatus.ERROR, textError);
		}
		return this;
	}

}
