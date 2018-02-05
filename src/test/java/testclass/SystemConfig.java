package testclass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import scripts.ConstantName;
import scripts.DriverScript;
import scripts.FunctionLibrary;
import scripts.MyLocator;

public class SystemConfig extends DriverScript{

	
	public static String wiFiSetting() {

		resultStatus = FunctionLibrary.clickOnElement(MyLocator.wiFiSetting, ConstantName.WI_FI);
		if(resultStatus.contains("fail"))
			return resultStatus;
		
		resultStatus = FunctionLibrary.clickOnElement(MyLocator.wiFiSwitch, "Wi-Fi Switch");
		if(resultStatus.contains("fail"))
			return resultStatus;
		
		resultStatus = FunctionLibrary.getElementText(MyLocator.switchText, "Wi-Fi status");
		if(resultStatus.contains("fail"))
			return resultStatus;
		Assert.assertEquals("Off", resultStatus);
		
		resultStatus = FunctionLibrary.clickOnElement(MyLocator.wiFiSwitch, "Wi-Fi Switch");
		if(resultStatus.contains("fail"))
			return resultStatus;
		
		resultStatus = FunctionLibrary.getElementText(MyLocator.switchText, "Wi-Fi status");
		if(resultStatus.contains("fail"))
			return resultStatus;
		//wiFiStatus = driver.findElement(By.id("com.android.settings:id/switch_text")).getText();
		Assert.assertEquals("On", resultStatus);
		
		((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
		
		return "Pass: "+testPass;
	}
	
	public static String verifyGpsSetting() throws InterruptedException {
		
		FunctionLibrary.scrollToElement(MyLocator.privacyAndSafety, ConstantName.PRIVACY_AND_SAFETY);
		
		resultStatus = FunctionLibrary.clickOnElement(pom.location, ConstantName.LOCATION); 
		if(resultStatus.contains("fail"))
			return resultStatus;
		
		resultStatus = FunctionLibrary.clickOnElement(pom.locationgMethod, ConstantName.LOCATING_METHOD); 
		if(resultStatus.contains("fail"))
			return resultStatus;
		
		resultStatus = FunctionLibrary.clickOnElement(pom.gpsOnly, ConstantName.GPS_ONLY); 
		if(resultStatus.contains("fail"))
			return resultStatus;
		return "Pass "+testPass;
	}
	
}
