package scripts;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.swing.text.MutableAttributeSet;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.ui.context.support.UiApplicationContextUtils;

import com.google.common.base.Verify;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PressesKeyCode;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;



public class AmazonLibrary extends DriverScript{
	

	public static void displayCartItems() {
		
		driver.findElement(MyLocator.cartLogo).click();
		String itemCount =  driver.findElement(MyLocator.cartItemCount).getText();
		Assert.assertTrue(Integer.parseInt(itemCount) >= 2);
		String headPhoneOne = ((AndroidDriver)driver).findElementByAndroidUIAutomator(MyLocator.firstHeadPhone).getText();
		String headPhoneTwo = pom.secondHeadPhone.getText();
		System.out.println(headPhoneOne);
		System.out.println(headPhoneTwo);
		
	}
	
	public static void scrollToElement(String elementName) throws InterruptedException{
		
		int lastY, startY,endY,miX;
		
		Dimension dimensions = driver.manage().window().getSize();
		System.out.println("Dimenseion="+dimensions);
		System.out.println("Total width="+dimensions.getWidth());
		System.out.println("Total Height="+dimensions.getHeight());
		
		Double screenHeight = dimensions.getHeight() * 0.5;
		startY = screenHeight.intValue();
		Double screenHeightEnd = dimensions.getHeight() * 0.2;
		endY = screenHeightEnd.intValue();
		Double width = dimensions.getWidth() * 0.5;
		int midX = width.intValue();
		
		MobileElement cartButton =  (MobileElement)driver.findElement(MyLocator.addToCartButton);
		
		int leftX = cartButton.getLocation().getX();
		int rightX = leftX + cartButton.getSize().getWidth();
		int middleX = (rightX + leftX) / 2;
		int upperY = cartButton.getLocation().getY();
		int lowerY = upperY + cartButton.getSize().getHeight();
		int middleY = (upperY + lowerY) / 2;
		
		
		Boolean isFoundElement = cartButton.getSize().getHeight() > 0;
		System.out.println("isHight "+isFoundElement);
		
		while(isFoundElement==false) {
			scroll(midX,startY,endY);	
			isFoundElement = cartButton.getSize().getHeight() > 0;
		}
		
		if(isFoundElement==true) {
			waitForElemntClick(MyLocator.addToCartButton);

			Thread.sleep(5000);
			cartButton.click();
			Thread.sleep(10000);
			((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			System.out.println("Click the element");
		}
			
	}
	
	
	public static void scroll(int midX,int startY,int endY) {
		System.out.println("---y1="+midX+"y2="+endY);
		TouchAction action = new TouchAction(driver);
		action.press(midX,startY).waitAction().moveTo(midX,endY).release().perform();
	}
	
	public static void waitForElemntClick(final By locator) {
		
		try {
			
			System.out.println("Started waiting for eleement");	
			Wait<WebDriver> wait = new WebDriverWait(driver, 60);	
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			System.out.println("Completed waiting for eleement");
			
		}catch(Throwable waitForElementException) {
			System.out.println(WAIT_ERROR+" "+waitForElementException);
		}
		
	}
	
	
}
