package listeners;

import java.net.MalformedURLException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import commonClasses.SetupConnection;
import pages.MainMenu;
import pages.PageMore;

public class TestListeners extends SetupConnection implements ITestListener {

@Override
public void onTestSuccess(ITestResult result) {

	String[] groups = result.getMethod().getGroups();
	
	if (Arrays.asList(groups).contains("NeededLogout")){
		try {
			PageMore pageMore = new MainMenu(driver, exTest).clickOnBtnSandwich();
			pageMore.exit();

			exTest.log(LogStatus.INFO, "Logout...");
		} catch (Exception e) {
			//if couldn't do logout - restart app 
			driver.quit();
			try {
				CreateCapabilities();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
	
@Override
public void onTestFailure(ITestResult result) {

	String[] groups = result.getMethod().getGroups();

	if (Arrays.asList(groups).contains("RestartApp")){
		driver.quit();
		try {
			CreateCapabilities();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
@Override
public void onTestSkipped(ITestResult result) {
	// TODO Auto-generated method stub
	
}

@Override
public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	// TODO Auto-generated method stub
	
}

@Override
public void onStart(ITestContext context) {
	// TODO Auto-generated method stub
	
}

@Override
public void onFinish(ITestContext context) {
	// TODO Auto-generated method stub
	
}


@Override
public void onTestStart(ITestResult result) {
	// TODO Auto-generated method stub
	
}
}
