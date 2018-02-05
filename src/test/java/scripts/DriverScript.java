package scripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.xpath.functions.FuncExtFunctionAvailable;
import org.eclipse.jetty.util.PathWatcher.Config;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Wait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class DriverScript{

	
	public static final String WAIT_ERROR = "Error occur while waiting for element";
	
	public static Logger ApplicatioLog = Logger.getLogger("Debugger");
	
	public static AppiumDriver driver = null;
	public static Wait<WebDriver> wait;
	public static WebElement webElement;
	public static DesiredCapabilities cap = null;
	
	public static PageObjectModel pom;
	
	public static Properties CONFIG;
	public static Properties OR;
	public static Properties APPTEXT;
	public static Properties LOG;
	
	public static String resultStatus = "";
	public static String testPass = "testcase passes successfully";
	public static String testFail = "testcase fails";		

	@BeforeClass
	public static void initSetUp() throws MalformedURLException, IOException {
		
		//Load the config.properties file
		CONFIG = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\config\\config.properties");
		CONFIG.load(fs);
		
		//Load the Log4j.properties file
		LOG = new Properties();
		fs=null;
		fs = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\Log4j.properties");
		LOG.load(fs);
		LOG.setProperty("log4j.appender.dest1.File", System.getProperty("user.dir") + "\\src\\test\\java\\config\\application.log");
		LOG.store(new FileOutputStream(System.getProperty("user.dir") + "\\src\\test\\java\\Log4j.properties"), null);
		System.out.println("--BeforeClass--");
		
	}
	
	@Test
	public void addItemToAmazonCart() throws InterruptedException, MalformedURLException {
		
		FunctionLibrary.startNewDriverSession(CONFIG.getProperty("amzaonCap"));
		//pom = new PageObjectModel(driver);
		resultStatus = Keywords.amazonCartItem();
		if(resultStatus.contains("pass")) 
			System.out.println(resultStatus);
		else 
			System.err.println(resultStatus);
	}
	
	@Test
	public void comparePriceOverTwoApp() throws InterruptedException, MalformedURLException {
		
		FunctionLibrary.startNewDriverSession(CONFIG.getProperty("amzaonCap"));
		resultStatus = Keywords.comparePrice();
		displayResult(resultStatus);
	}
	
	@Test
	public void systemSpecificScenario() throws InterruptedException, MalformedURLException {
		
		//Verify Wi-Fi Setting
		FunctionLibrary.startNewDriverSession(CONFIG.getProperty("settingCap"));
		resultStatus = Keywords.wiFiSetting();
		displayResult(resultStatus);
		
		//Verify GPS Location 
		resultStatus = Keywords.verifyGpsSetting();
		displayResult(resultStatus);
		
	}
	
	@Test
	public void searchItemWithFilter() throws MalformedURLException, InterruptedException {
		
		FunctionLibrary.startNewDriverSession(CONFIG.getProperty("flipKartCap"));
		resultStatus = Keywords.applyFlipkartFilter(ConstantName.SEARCH_ITEM_NAME);
		displayResult(resultStatus);
	}
	
	public void displayResult(String resultStatus) {
		if(resultStatus.contains("pass")) 
			System.out.println(resultStatus);
		else 
			System.err.println(resultStatus);
	}
	
	@After
	public void afterEachTest() {
		System.out.println("--Quit driver--");
		driver.quit();
	}
	
	@AfterClass
	public static void endScript() {
		driver.quit();
		System.out.println("Script is ended");
	}
	
}
