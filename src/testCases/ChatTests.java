package testCases;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.PriorityBlockingQueue;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonClasses.ExtentFactory;
import commonClasses.SetupConnection;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import pages.LoginByEmailPage;
import pages.LoginPage;
import pages.MainMenu;
import pages.HomePage;
import pages.PageChat;

public class ChatTests extends SetupConnection {

	ExtentTest	exTestParent;
	PageChat pageChat = new PageChat(driver, exTest);
	
	@BeforeClass(alwaysRun = true)
    public void beforeClass(){
		exReport = ExtentFactory.getInstance();
		exTestParent = exReport.startTest("Chat");
	}	

	@AfterClass(alwaysRun = true)
    public void afterClass(){
		exReport.endTest(exTestParent);
		exReport.flush();
	}	

/*	@BeforeTest
	public void loginIntoDive2() throws InterruptedException {
		Thread.sleep(2000);
		
		LoginByEmailPage loginByEmail = new LoginPage(driver, exTest).clickOnBtnEmail();
		loginByEmail.typeEmail("dforsp@gmail.com").typePassword("qAlityAssurance").clickOnButtonEnter();
	}
*/
	@Test(groups = { "Chat" }, priority = 10)
	public void verifyThatChatScreenIsOpened() {

		exTest = exReport.startTest("Chat Screen Is Opened");
		exTestParent.appendChild(exTest);
		
		String ExpectedTitle = "Сообщения";

		MainMenu mainMenu = new MainMenu(driver, exTest);

		pageChat = mainMenu.clickOnBtnChat();

		String currentTitle = pageChat.getTheTitle();

		Assert.assertEquals(ExpectedTitle, currentTitle);
		exTest.log(LogStatus.PASS, "Verifed That Chat is opened");
	
	}

	@Test(groups = { "Chat" }, dependsOnMethods = { "verifyThatChatScreenIsOpened" }, priority = 11)
	public void verifyThatDialogWithUserIsOpened() {

		exTest = exReport.startTest("Dialog With User Is Opened");
		exTestParent.appendChild(exTest);

		String serviceUserName = "Dina";
		
		String currentTitle  = pageChat.tapOnUser(serviceUserName).getTheTitle();

		Assert.assertEquals(serviceUserName, currentTitle);
		exTest.log(LogStatus.PASS, "Verify That Dialog With User is opened");

	}

	@Test(groups = { "Chat" }, dependsOnMethods = { "verifyThatDialogWithUserIsOpened" }, priority = 12)
	public void verifyThatTextMessageIsSent() {

		exTest = exReport.startTest("Text Message Is Sent");
		exTestParent.appendChild(exTest);

		DateFormat dateFormat = new SimpleDateFormat();
		Date currentDate = new Date();
		String textMessage = "Test message: " + dateFormat.format(currentDate);

		//PageChat pageChat = new PageChat(driver, exTest);
		String sentMessage = pageChat.typeTextMessage(textMessage)
				.tapOnButtonSendMessage()
				.findSentMessage(textMessage); 

		Assert.assertEquals(textMessage, sentMessage);		
		exTest.log(LogStatus.PASS, "Verify sending message");
	}

	@Test(groups = { "Chat" }, dependsOnMethods = { "verifyThatTextMessageIsSent" }, priority = 13)
	public void verifyThatDialogIsClosed() {
		
		String ExpectedTitle = "Сообщения";

		exTest = exReport.startTest("DialogIsClosed");
		exTestParent.appendChild(exTest);

		//PageChat pageChat = new PageChat(driver, exTest);
		String currentTitle = pageChat.tapOnArrowBack().getTheTitle();

		Assert.assertEquals(ExpectedTitle, currentTitle);
		exTest.log(LogStatus.PASS, "Verifed That Dialog is closed");
	}

}
