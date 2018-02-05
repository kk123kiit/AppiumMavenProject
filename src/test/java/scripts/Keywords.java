package scripts;

import java.net.MalformedURLException;

import testclass.AppiumTest;
import testclass.SystemConfig;

public class Keywords extends DriverScript{
	
	public static String amazonCartItem() throws InterruptedException {
		return AppiumTest.amazonCartItem();
	}
	
	public static String wiFiSetting() {
		return SystemConfig.wiFiSetting();
	}
	
	public static String verifyGpsSetting() throws InterruptedException {
		return SystemConfig.verifyGpsSetting();
	}
	
	public static String comparePrice() throws InterruptedException, MalformedURLException{
		return AppiumTest.comparePrice();
	}

	public static String applyFlipkartFilter(String itemName) throws InterruptedException {
		return AppiumTest.applyFlipkartFilter(itemName);
	}
}
