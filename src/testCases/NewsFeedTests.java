package testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonClasses.ExtentFactory;
import commonClasses.SetupConnection;
import pages.LoginByEmailPage;
import pages.LoginPage;
import pages.MainMenu;
import pages.PageNewsFeed;

public class NewsFeedTests extends SetupConnection{
	ExtentTest exTestParent;
	ExtentReports exReport;
	
	@BeforeClass(alwaysRun = true)
    public void beforeClass() throws InterruptedException{
		exReport = ExtentFactory.getInstance();
		exTestParent = exReport.startTest("News Feed");
		
		//For debugging
		exTest = exReport.startTest("Login by Email");
		exTestParent.appendChild(exTest);

		LoginByEmailPage loginByEmail = new LoginPage(driver, exTest).clickOnBtnEmail();

		loginByEmail.typeEmail("dforsp@gmail.com")
				.typePassword("qAlityAssurance")
				.clickOnButtonEnter(false); //homePage will be null because of wrong credentials
	
		//Thread.sleep(2000);
	}	

	@AfterClass(alwaysRun = true)
    public void afterClass(){
		exReport.endTest(exTestParent);
		exReport.flush();
	}	

	@Test(groups = { "News feed" }, priority = 40)
	public void verifyThatNewsFeedIsOpened() throws InterruptedException {

		String ExpectedTitle = "Лента";

		exTest = exReport.startTest("News Feed Is Opened");
		exTestParent.appendChild(exTest);

		MainMenu mainMenu = new MainMenu(driver, exTest);

		PageNewsFeed pageNewsFeed = mainMenu.clickOnBtnNewsFeed();

		String currentTitle = pageNewsFeed.getTheTitle();

		Assert.assertEquals(ExpectedTitle, currentTitle);
		exTest.log(LogStatus.PASS, "Verifed That News Feed is opened");
	}

	@Test(groups = { "News feed" }, priority = 41, dependsOnMethods = {"verifyThatNewsFeedIsOpened"}, enabled = false)
	public void verifyThatPostIsOpened() throws InterruptedException {

		String nameOfPostInNewsFeed = "Empty post name in News Feed";
		String nameOfPost  = "Empty post name";
		
		exTest = exReport.startTest("Post From News Feed Is Opened");
		exTestParent.appendChild(exTest);

		PageNewsFeed pageNewsFeed = new PageNewsFeed(driver, exTest);
		WebElement car  = pageNewsFeed.getPost();
		
/*		nameOfTheCarOfTheDay = homePage.getNameCarOfTheDay(car);
		CarPage carPage = homePage.clickOnCarOfTheDay(car);
		
		nameOfTheCar = carPage.getNameOfTheCar();
				
		System.out.println("nameCarOfTheDay " + nameOfTheCarOfTheDay);
		System.out.println("nameOfTheCar " + nameOfTheCar);
		
		Assert.assertEquals(nameOfTheCarOfTheDay, nameOfTheCar);
		exTest.log(LogStatus.PASS, "Verified That page of the car of the day is opened");
*/
		
	}

}
