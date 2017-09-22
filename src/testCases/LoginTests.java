package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonClasses.ExtentFactory;
import commonClasses.SetupConnection;
import pages.HomePage;
import pages.LoginByEmailPage;
import pages.LoginByFacebookPage;
import pages.LoginByVKontaktePage;
import pages.LoginPage;

//@Listeners(MethodListeners.class)
public class LoginTests extends SetupConnection{

	//private AndroidDriver<WebElement> driver;
	ExtentTest exTestParent;
	ExtentReports exReport;
	
	@BeforeClass(alwaysRun = true)
    public void beforeClass(){
		exReport = ExtentFactory.getInstance();
		exTestParent = exReport.startTest("Login/Reject login/Logout");
	}	

	@AfterClass(alwaysRun = true)
    public void afterClass(){
		exReport.endTest(exTestParent);
		exReport.flush();
	}	
	
	@Test(priority = 1)
	public void verifyRejectLoginByEmailIsPerformed(){

		exTest = exReport.startTest("Reject Login");
		exTestParent.appendChild(exTest);

		boolean btnEmailIsPresent = false;
		
		LoginPage loginByEmail = new LoginPage(driver, exTest)
				.clickOnBtnEmail()
				.clickOnReturn();
		
		btnEmailIsPresent = loginByEmail.btnEmailIsPresent();
		Assert.assertEquals(btnEmailIsPresent, true);
		exTest.log(LogStatus.PASS, "Verifed reject Login");
	}
	
	@Test (groups = {"RestartApp"}, priority = 2, enabled = true)
	public void verifyThatSystemRejectsWrongCredentials() throws InterruptedException {

		exTest = exReport.startTest("Reject Wrong Credentials");
		exTestParent.appendChild(exTest);

		boolean wrongCredentials = true;
		
		LoginByEmailPage loginByEmail = new LoginPage(driver, exTest).clickOnBtnEmail();

		loginByEmail.typeEmail("dforsp@gmail.com")
				.typePassword("wrongPassword")
				.clickOnButtonEnter(wrongCredentials); //homePage will be null because of wrong credentials
		
		String currentTitle = loginByEmail.getTheTitle();

		Assert.assertEquals("Вход", currentTitle);
		exTest.log(LogStatus.PASS, "Verified System Rejected Wrong Credentials");

		loginByEmail.clickOnReturn();
		
/*		String errorMessage = loginByEmail.getErrorMessage();
		
		System.out.println(errorMessage);

		Assert.assertEquals("Неверные данные для входа", errorMessage);
		exTest.log(LogStatus.PASS, "Verify Error message");
*/
	}
	
	@Test (groups = {"RestartApp", "NeededLogout"}, priority = 3)//, enabled = false)
	public void verifyLoginByVKontakteIsPerformed() throws InterruptedException {
		
		exTest = exReport.startTest("Login By VKontakte");
		exTestParent.appendChild(exTest);
		
		LoginByVKontaktePage loginByVK = new LoginPage(driver, exTest).clickOnBtnVK();
		
		HomePage homePage = loginByVK
		.typeEmail("dforshop@gmail.com")
		.typePassword("qAlityAssurance17")
		.clickOnButtonLogin();

		String currentTitle = homePage.getTheTitle();
		
		Assert.assertEquals(currentTitle, "DRIVE2.RU");
		exTest.log(LogStatus.PASS, "Verify Login By VKontakte Is Performed");
			
	}	
	
	@Test(groups = {"RestartApp", "NeededLogout"}, priority = 4, enabled = true)
	public void verifyLoginByFacebookIsPerformed() throws InterruptedException {

		exTest = exReport.startTest("Login By Facebook");
		exTestParent.appendChild(exTest);

		LoginByFacebookPage loginByFB = new LoginPage(driver, exTest).clickOnBtnFB();
		
		HomePage homePage = loginByFB
		.typeEmail("dforshop@gmail.com")
		.typePassword("qAlityAssurance17")
		.clickOnButtonLogin();

		String currentTitle = homePage.getTheTitle();
		
		Assert.assertEquals(currentTitle, "DRIVE2.RU");
		exTest.log(LogStatus.PASS, "Verified Login By Facebook Is Performed");
		
	}

	@Test(groups = {"Critical"}, priority = 5)//, enabled = false)
	public void verifyLoginByEmailIsPerformed() throws InterruptedException{
	
		boolean wrongCredentials = false;
		exTest = exReport.startTest("Login By Email");
		exTestParent.appendChild(exTest);
		
		LoginByEmailPage loginByEmail = new LoginPage(driver, exTest).clickOnBtnEmail();
		
		HomePage homePage = loginByEmail
		.typeEmail("dforsp@gmail.com")
		.typePassword("qAlityAssurance")
		.clickOnButtonEnter(wrongCredentials);

		Thread.sleep(3000);
		
		String currentTitle = homePage.getTheTitle();

		Assert.assertEquals(currentTitle, "DRIVE2.RU");
		exTest.log(LogStatus.PASS, "Verified Login By Email Is Performed");
	}

/*	@AfterGroups({"Reject", "LoginByVK", "LoginByFB"})
	//to make tests independent from these groups	
	//public void logout(ITestResult testResult) {
	public void logout() throws Exception {
		driver.quit();
		CreateCapabilities();
	}
*/	
	
/*	@AfterMethod(groups = { "NeededLogout" }, alwaysRun = false)
	// Несмотря на принадлежность к группе запускается для всех групп, указанных
	// в xml. Поэтому проверяем группу внутри
	public void logout(ITestResult testResult) {
		// Need to do logout only for success logins
		System.out.println("Logout - After method");
		if (testResult.getStatus() ==  ITestResult.SUCCESS) {
			String[] groups = testResult.getMethod().getGroups();
			
			for (int i = 0; i < groups.length; i++) {
				if (groups[i].equals("NeededLogout")) {
					
					PageMore pageMore = new MainMenu(driver, exTest).clickOnBtnSandwich();
					pageMore.exit();

				}
			}
		}
	}

	@AfterMethod(alwaysRun = true)
	public void startApp(ITestResult testResult) throws Exception {

		if (!(testResult.getStatus() ==  ITestResult.SUCCESS)){
			
			driver.quit();
			CreateCapabilities();
			
			//driver  = new AndroidDriver<>(url, cap);
			System.out.println("Start app again...");
		}
	
	}	
*/}
