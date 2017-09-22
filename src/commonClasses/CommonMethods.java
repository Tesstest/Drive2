package commonClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;

public class CommonMethods {

	AndroidDriver<WebElement> driver;

	public CommonMethods(AndroidDriver<WebElement> driver) {

		this.driver = driver;
	}

	public WebElement waitForElement(By locator, int timeout) {

		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		return element;
	}

	public WebElement waitForVisibilityOfElement(WebElement element, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		element = wait.until(ExpectedConditions.visibilityOf(element));

		return element;

	}

	public List<WebElement> waitForElements(By locator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		List<WebElement> listOfElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

		return listOfElements;
	}

	public String getScreenTitle(By locator, int timeout) {

		String theTitle = "";
		
		try {
			List<WebElement> ls = waitForElements(locator, timeout);
			WebElement elTitle = ls.get(0);
			theTitle = elTitle.getText();
		
		} catch (Exception e) {
			System.out.println("Couldn't get the title!");
			throw e; //if delete throw program completed and couldn't continue execute other tests 
		}
		
		return theTitle;
	}
}
