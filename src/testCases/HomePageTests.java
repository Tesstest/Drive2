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
import pages.CarPage;
import pages.HomePage;

public class HomePageTests extends SetupConnection {

	ExtentTest exTestParent;
	ExtentReports exReport;
	
	@BeforeClass(alwaysRun = true)
    public void beforeClass() throws InterruptedException{
		exReport = ExtentFactory.getInstance();
		exTestParent = exReport.startTest("Home Page");
		
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

	@Test(groups = { "HomePage" }, priority = 20)
	public void verifyThatHomeScreenIsOpened() throws InterruptedException {
		boolean carOfTheDayIsPresent = false;	
		
		Thread.sleep(2000);
		
		exTest = exReport.startTest("Home Page Is Opened");
		exTestParent.appendChild(exTest);

		MainMenu mainMenu = new MainMenu(driver, exTest);
		HomePage homePage = mainMenu.clickOnBtnHome();
		carOfTheDayIsPresent = homePage.carOfTheDayIsPresent();

		Assert.assertTrue(carOfTheDayIsPresent);
		exTest.log(LogStatus.PASS, "Verified That Home Page is opened");
	}

	@Test(groups = { "HomePage" }, dependsOnMethods = { "verifyThatHomeScreenIsOpened" }, priority = 21)
	public void verifyThatCarOfTheDayIsOpened() throws InterruptedException {

		String nameOfTheCarOfTheDay = "Empty car name on Home Page";
		String nameOfTheCar  = "Empty car name";
		
		exTest = exReport.startTest("Page Of The Car Of The Day Is Opened");
		exTestParent.appendChild(exTest);

		HomePage homePage = new HomePage(driver, exTest);
		WebElement car  = homePage.getCarOfTheDay();
		
		nameOfTheCarOfTheDay = homePage.getNameCarOfTheDay(car);
		CarPage carPage = homePage.clickOnCarOfTheDay(car);
		
		nameOfTheCar = carPage.getNameOfTheCar();
				
		System.out.println("nameCarOfTheDay " + nameOfTheCarOfTheDay);
		System.out.println("nameOfTheCar " + nameOfTheCar);
		
		Assert.assertEquals(nameOfTheCarOfTheDay, nameOfTheCar);
		exTest.log(LogStatus.PASS, "Verified That page of the car of the day is opened");
	}
}
