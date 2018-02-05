package scripts;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PageObjectModel extends MyLocator{

	@AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/rs_search_src_text")
	public static AndroidElement searchBar;
	
	//public MyLocator pomLocator;
	public PageObjectModel(AppiumDriver<MobileElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver, 60,TimeUnit.SECONDS), this);
	}
	
	public static void initializePageFactory(AppiumDriver<MobileElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver, 60,TimeUnit.SECONDS), PageObjectModel.class);
	}
	
}
