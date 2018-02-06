package testclass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.ui.context.support.UiApplicationContextUtils;

import com.sun.jna.platform.win32.Wdm.KEY_BASIC_INFORMATION;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PressesKeyCode;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.remote.AutomationName;
import scripts.AmazonLibrary;
import scripts.ConstantName;
import scripts.DriverScript;
import scripts.FunctionLibrary;
import scripts.MyLocator;

public class AppiumTest extends DriverScript{
	

	static List<MobileElement> headPhoneList;	
	
	public static String amazonCartItem() throws InterruptedException {
		
		System.out.println("Before click");	
		
		driver.findElement(MyLocator.searchBarId).sendKeys("headphone");
		
		//Enter button is clicked
		((AndroidDriver) driver).pressKeyCode(66); 
		
		//Get the list of headphone
		headPhoneList = driver.findElements(MyLocator.resourceId);
		
		//CLick on first headphone
		headPhoneList.get(0).click();
		//Thread.sleep(3000);
		
		AmazonLibrary.scrollToElement("Add to Cart");
		
		headPhoneList.get(1).click();
		AmazonLibrary.scrollToElement("Add to Cart");
		
		AmazonLibrary.displayCartItems();
		Thread.sleep(5000);
		
		return "Pass: amazonCartItem test case passes";
	}
	
	public static String comparePrice() throws InterruptedException, MalformedURLException{
		
		resultStatus = FunctionLibrary.sendKeysToElement(MyLocator.searchBarId,ConstantName.SEARCH_ITEM_NAME, "Amazon Search Bar");
		if(resultStatus.contains("fail"))
			return resultStatus;
		//Enter button is clicked
		System.out.println("Before Click enter button");
		((AndroidDriver) driver).pressKeyCode(66); 
		
		headPhoneList = driver.findElements(MyLocator.resourceId);
		//CLick on first headphone
		headPhoneList.get(0).click();

		System.out.println("After Click enter button");
		FunctionLibrary.scrollToElement(MyLocator.itemPrice, "Item Price","readText");
		
		String amazonItemPrice = driver.findElement(MyLocator.itemPrice).findElement(MyLocator.priceValue).getText();
		
		String amzPrice = "";                              
		int i=0;
		while(i<amazonItemPrice.length()) {
			char c = amazonItemPrice.charAt(i);
			if(Character.isDigit(c))
				amzPrice = amzPrice+c;
			i++;
		}
			 
		System.out.println("AmzPrice "+amzPrice);
		//System.out.println("AmazonItemPrice "+amazonItemPrice);
		
		FunctionLibrary.startNewDriverSession(CONFIG.getProperty("flipKartCap"));
		
		//Click on flipkart search bar
		resultStatus =  FunctionLibrary.clickOnElement(MyLocator.flipkartSearchBar,"Flip Kart Search Bar");
		if(resultStatus.contains("fail"))
			return resultStatus;
		
		resultStatus = FunctionLibrary.sendKeysToElement(MyLocator.flipkartSearchEditBox, ConstantName.SEARCH_ITEM_NAME, "Flip Kart Search Bar");
		if(resultStatus.contains("fail"))
			return resultStatus;
		
		//Hit on enter button
		((AndroidDriver) driver).pressKeyCode(66); 
		
		headPhoneList = driver.findElementsByAccessibilityId(MyLocator.flipKartAccessId);
		headPhoneList.get(0).click();
		
		FunctionLibrary.scrollToElement(MyLocator.priceLayoutId, "Item Price","readText");
		List<MobileElement> priceList = driver.findElement(MyLocator.priceLayoutId).findElements(MyLocator.priceAttribute);
		
		String flipkartItemPrice = priceList.get(1).getText();
		System.out.println("flipkartItemPrice "+flipkartItemPrice);
		
		if(Integer.parseInt(flipkartItemPrice) < Integer.parseInt(amzPrice))
			System.out.println("Buy on Flipkart");
		else
			System.out.println("Buy on Amazon");
		
		return "Pass: "+testPass;
	}
	
	//Search item(searching headphone) on flipkart app using different filters
	public static String applyFlipkartFilter(String itemName) throws InterruptedException {
		
		resultStatus =  FunctionLibrary.clickOnElement(MyLocator.flipkartSearchBar,"Flip Kart Search Bar");
		if(resultStatus.contains("fail"))
			return resultStatus;
		
		resultStatus = FunctionLibrary.sendKeysToElement(MyLocator.flipkartSearchEditBox, itemName, "Flip Kart Search Bar");
		if(resultStatus.contains("fail"))
			return resultStatus;
		
		//Hit on enter button
		((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.ENTER); 
		
		resultStatus = FunctionLibrary.clickOnElement(MyLocator.filterIcon, "Filter Icon");
		if(resultStatus.contains("fail"))
			return resultStatus;
		
		//Select category
		FunctionLibrary.scrollToElement(pom.categoryElement, ConstantName.CATEGORY, "click");
		resultStatus = FunctionLibrary.clickOnElement(MyLocator.categoryType, "Headphones");
		if(resultStatus.contains("fail"))
			return resultStatus;
		
		
		//Select Brand
		FunctionLibrary.scrollToElement(pom.brand, ConstantName.BRAND,"click");
		FunctionLibrary.scrollToElement(pom.brandName, ConstantName.BRAND_NAME,"click");
		resultStatus = FunctionLibrary.clickOnElement(MyLocator.doneButton, ConstantName.DONE_BUTTON);
		if(resultStatus.contains("fail"))
			return resultStatus;
		
		//Select Connectivity
		FunctionLibrary.scrollToElement(pom.connectivity, ConstantName.CONNECTIVITY,"click");
		resultStatus = FunctionLibrary.clickOnElement(MyLocator.connectivityType, ConstantName.CONNECTIVITY_TYPE);
		if(resultStatus.contains("fail"))
			return resultStatus;
		
		resultStatus = FunctionLibrary.clickOnElement(MyLocator.doneButton, ConstantName.DONE_BUTTON);
		if(resultStatus.contains("fail"))
			return resultStatus;
		
		//CLik on moreFilters
		FunctionLibrary.scrollToElement(pom.moreFilters, ConstantName.MORE_FILTERS,"click");
		
		//Select color
		FunctionLibrary.scrollToElement(pom.color, ConstantName.COLOR,"click");
		FunctionLibrary.scrollToElement(pom.colorType, ConstantName.COLOR_TYPE,"click");
		
		if(resultStatus.contains("fail"))
			return resultStatus;
		resultStatus = FunctionLibrary.clickOnElement(MyLocator.doneButton, ConstantName.DONE_BUTTON);
		if(resultStatus.contains("fail"))
			return resultStatus;
		
		//Finally Apply filter
		resultStatus = FunctionLibrary.clickOnElement(MyLocator.applyFilter, ConstantName.APPLY_FILTER);
		if(resultStatus.contains("fail"))
			return resultStatus;
		
		return "Pass: "+testPass;
	}
	
}
