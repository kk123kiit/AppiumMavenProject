package scripts;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;


public class FunctionLibrary extends DriverScript{
	
	public static void startNewDriverSession(String capibilityType) throws MalformedURLException {
		
		if(driver!=null)
			driver.quit();
		
		switch(capibilityType) {
			case "amazonAppCapability":
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),Capabilities.amazonAppCapibility());
				break;
			case "flipKartAppCapability":
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),Capabilities.flipKartAppCapibility());
				break;
			case "settingCapability":
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),Capabilities.settingCapability());
				break;
			default: 
				return;
		}
		
		pom = new PageObjectModel(driver);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		System.out.println("Session is created for: "+capibilityType);
	}

	//Wait for element to be visible
	public static void waitForElementToLoad(final By locator, String eltName) {
		
		ApplicatioLog.debug("Waiting for "+eltName+" to load on the page");
		System.out.println("Waiting for "+eltName+" to load on the page");
		
		try {
			wait = new WebDriverWait(driver, 60);
			webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			ApplicatioLog.debug("Waiting for "+eltName+" is over");
			System.out.println("Waiting for "+eltName+" is over");
			
		}catch(Throwable waitForelementException) {
			
			ApplicatioLog.debug("Error on wait for element "+waitForelementException);
			System.err.println("Error on wait for element "+waitForelementException);
		}
	}
	
	//Wait for an element to click
	public static void waitForElemntClick(final By locator) {
		
		try {
			
			System.out.println("Started waiting for element");	
			wait = new WebDriverWait(driver, 60);	
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			System.out.println("Completed waiting for element");
			
		}catch(Throwable waitForElementException) {
			System.err.println(WAIT_ERROR+" "+waitForElementException);
		}
		
	}
	
	//Scroll the page vertically
	public static void scrollToElement(By Locator, String elementName, String oprName) throws InterruptedException{
		
		int lastY, startY,endY,miX;
		
		//Find the dimension of the current device
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
		
		MobileElement mobileElement =  (MobileElement)driver.findElement(Locator);
		
		//Find element location
		int leftX = mobileElement.getLocation().getX();
		int rightX = leftX + mobileElement.getSize().getWidth();
		int middleX = (rightX + leftX) / 2;
		int upperY = mobileElement.getLocation().getY();
		int lowerY = upperY + mobileElement.getSize().getHeight();
		int middleY = (upperY + lowerY) / 2;
		
		
		Boolean isFoundElement = mobileElement.getSize().getHeight() > 0;
		//System.out.println("isHight "+isFoundElement);
		
		while(isFoundElement==false) {
			scroll(midX,startY,endY);	
			isFoundElement = mobileElement.getSize().getHeight() > 0;
		}
		
		if(isFoundElement==true) {
			
			if(oprName=="readText")
				return;
			waitForElemntClick(MyLocator.addToCartButton);
			Thread.sleep(5000);
			mobileElement.click();
			Thread.sleep(5000);
			System.out.println("Click the element");
			
		}
			
	}
	
	public static void scrollToElement(By locator ,String elementName) throws InterruptedException{
		
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
		
		System.out.println("locator="+locator);
		
		boolean isFoundElement = isElementPresent(locator);
		
		if(isFoundElement==false)
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		System.out.println("isFoundElement "+isFoundElement);
		
		while(isFoundElement==false) {
			scroll(midX,startY,endY);	
			isFoundElement = isElementPresent(locator);
		}
		
		if(isFoundElement==true) {
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(locator).click();
			Thread.sleep(5000);
			System.out.println("Click the "+elementName);
		}
	}
	
	public static void scrollToElement(MobileElement mobileElement ,String elementName, String operation) throws InterruptedException{
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
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
		
		//Check element is present on the current activity
		boolean isFoundElement = isElementPresent(mobileElement);
		
		System.out.println("isFoundElement "+isFoundElement);
		
		while(isFoundElement==false) {
			scroll(midX,startY,endY);	
			isFoundElement = isElementPresent(mobileElement);
		}
		
		System.out.println("-------4----------");
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		
		if(isFoundElement==true) {
			if(operation=="click")
				mobileElement.click();
		}

	}
	
	public static void scroll(int midX,int startY,int endY) {
		System.out.println("---y1="+midX+"y2="+endY);
		TouchAction action = new TouchAction(driver);
		action.press(midX,startY).waitAction().moveTo(midX,endY).release().perform();
	}
	
	//Check whether element is present on the mobile
	public static boolean isElementPresent(By locator) {
		
		try {
			driver.findElement(locator);
			System.out.println("--element is present--");
			return true;
		}catch(NoSuchElementException e) {
			System.err.println("--element is not present--");
			return false;
		}
	}
	
	//Check element(android element) is present on the current activity
	public static boolean isElementPresent(MobileElement mobileElement) {
		
		try{
			mobileElement.isDisplayed();
			ApplicatioLog.debug("Element is visible");
			System.out.println("Element is visible");
			return true;
			
		}catch(NoSuchElementException e) {
			ApplicatioLog.debug("Element is not visible");
			System.err.println("Element is not visible");
			return false;
		}
	}

	public static String sendKeysToElement(By locator, String itemName, String eltName) {
		ApplicatioLog.debug("Sending Values in : " + eltName);
		System.out.println("Sending Values in : " + eltName);
		try {
			
			waitForElementToLoad(locator, eltName);
			driver.findElement(locator).sendKeys(itemName);
			ApplicatioLog.debug("Sending Values in :" + eltName);
			System.out.println("Sending Values in :" + eltName);
			return "Pass: "+eltName+" "+testPass;
			
		}catch(Exception inputException) {
			ApplicatioLog.debug("Error: While Sending Values in :" + eltName);
			System.err.println("Error: While Sending Values in :" + eltName);
			return "Fail: "+eltName+" "+testFail;
		}
	}

	//Click on the element
	public static String clickOnElement(By locator, String elementName) {
		ApplicatioLog.debug("Going to click on : " + elementName);
		System.out.println("Going to click on : " + elementName);
		try {
			
			waitForElemntClick(locator);
			driver.findElement(locator).click();
			ApplicatioLog.debug("Clicked on :" + elementName);
			System.out.println("Clicked on :" + elementName);
			return "Pass: "+elementName+" "+testPass;
			
		}catch(Throwable clickElementException) {
			ApplicatioLog.debug("Error: While clicking on:" + elementName);
			System.err.println("Error: While clicking on:" + elementName);
			return "Fail: "+elementName+" "+testFail;
		}
	}
	
	//Click on the element
	public static String clickOnElement(MobileElement mobElt, String elementName) {
		ApplicatioLog.debug("Going to click on : " + elementName);
		System.out.println("Going to click on : " + elementName);
		try {
			if(!isElementPresent(mobElt))
				return "Fail: "+elementName+" "+testFail;
			
			mobElt.click();
			ApplicatioLog.debug("Clicked on :" + elementName);
			System.out.println("Clicked on :" + elementName);
			return "Pass: "+elementName+" "+testPass;
			
		}catch(Throwable clickElementException) {
			ApplicatioLog.debug("Error: While clicking on:" + elementName);
			System.err.println("Error: While clicking on:" + elementName);
			return "Fail: "+elementName+" "+testFail;
		}
	}

	public static String sendKeysToElement(String flipkartSearchEditBox, String searchItemName, String eltName) {
		ApplicatioLog.debug("Sending Values in : " + eltName);
		System.out.println("Sending Values in : " + eltName);
		try {
			//waitForElementToLoad(locator, eltName);
			driver.findElementByAccessibilityId(flipkartSearchEditBox).sendKeys(searchItemName);
			ApplicatioLog.debug("Sending Values in :" + eltName);
			System.out.println("Sending Values in :" + eltName);
			return "Pass: "+eltName+" "+testPass;
			
		}catch(Exception inputException) {
			ApplicatioLog.debug("Error: While Sending Values in :" + eltName);
			System.err.println("Error: While Sending Values in :" + eltName);
			return "Fail: "+eltName+" "+testFail;
		}
	}
	
	public static String getElementText(By locator, String elementName) {
		
		ApplicatioLog.debug("Try to get text from : " + elementName);
		System.out.println("Try to get text from : "+elementName);
		
		try {
			String eltText = driver.findElement(locator).getText();
			ApplicatioLog.debug("Got text from : " + elementName);
			System.out.println("Got text from : "+elementName);
			return eltText;
		}catch(Exception e) {
			
			ApplicatioLog.debug("Error in getting text from : " + elementName);
			System.err.println("Error in getting text from : "+elementName);
			return "Fail: "+elementName+" "+testFail;
		}
		
	}
	
}
