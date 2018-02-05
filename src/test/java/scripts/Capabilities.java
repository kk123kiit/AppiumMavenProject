package scripts;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Capabilities extends DriverScript{
	
	//public static DesiredCapabilities cap = null;
	
	public static DesiredCapabilities amazonAppCapibility() {
		
		cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Samsung On5");
		cap.setCapability("platformVersion", "6.0.1");
		cap.setCapability("platformName", "Android");
		//cap.setCapability("platformVersion", "7.0");
		cap.setCapability("platformName", "Android");
		cap.setCapability("appPackage", "in.amazon.mShop.android.shopping");
		cap.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
		cap.setCapability("newCommandTimeout", 60 * 10);
		cap.setCapability("noReset", "true");
		return cap;
		
	}
	
	public static DesiredCapabilities flipKartAppCapibility() {
		
		cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Samsung On5");
		cap.setCapability("platformVersion", "6.0.1");
		cap.setCapability("platformName", "Android");
		//cap.setCapability("platformVersion", "7.0");
		cap.setCapability("platformName", "Android");
		cap.setCapability("appPackage", "com.flipkart.android");
		cap.setCapability("appActivity", "com.flipkart.android.activity.HomeFragmentHolderActivity");
		cap.setCapability("newCommandTimeout", 60 * 10);
		cap.setCapability("noReset", "true");
		return cap;
		
	}
	
	public static DesiredCapabilities settingCapability() {
		
		cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Android");
		cap.setCapability("platformVersion", "6.0.1");
		//cap.setCapability("platformVersion", "7.0");
		cap.setCapability("platformName", "Android");
		cap.setCapability("appPackage", "com.android.settings");
		cap.setCapability("appActivity", "com.android.settings.Settings");
		cap.setCapability("newCommandTimeout", 60 * 10);
		cap.setCapability("noReset", "true");
		return cap;
	}

}
