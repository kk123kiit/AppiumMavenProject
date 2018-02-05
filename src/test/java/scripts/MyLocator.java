package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MyLocator {
	
	//------------Locators for Amazon android App
	public static By skipSignInId = By.id("in.amazon.mShop.android.shopping:id/skip_sign_in_button");
	public static By searchBarId = By.id("in.amazon.mShop.android.shopping:id/rs_search_src_text");
	public static By resourceId = By.id("in.amazon.mShop.android.shopping:id/item_title");
	public static By addToCartButton = By.id("add-to-cart-button");
	public static By pinCodeButton = By.id("in.amazon.mShop.android.shopping:id/loc_ux_gps_enter_pincode");
	public static By pinCodeTextField = By.id("in.amazon.mShop.android.shopping:id/loc_ux_pin_code_text_pt1");
	public static By pinCodeUpdateButton =  By.id("in.amazon.mShop.android.shopping:id/loc_ux_update_pin_code");
	public static By addressPopUpMenu = By.id("in.amazon.mShop.android.shopping:id/loc_ux_gps_auto_detect_segment");
	public static By itemPrice = By.id("atfRedesign_priceblock_priceToPay");
	public static By priceValue = By.className("android.widget.EditText");
	
	//Cart locators
	public static By cartLogo = By.id("Cart");
	public static By cartItemCount = By.id("in.amazon.mShop.android.shopping:id/action_bar_cart_count");
	
	//Warning pop up
	public static By warningPopUp = By.id("android:id/button1");
	
	//UiAutomator Locators
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Sony MDR-ZX110A On-Ear Stereo\")")
	public static MobileElement secondHeadPhone;
	public static String firstHeadPhone = "new UiSelector().textContains(\"JBL C100SI In-Ear Headphones\")";
	
	@AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/rs_search_src_text")
	public static AndroidElement searchBar;
	
	//GPS Locator
	public static By privacyAndSafety = By.xpath("//android.widget.TextView[@text='"+ConstantName.PRIVACY_AND_SAFETY+"']");
	
	@AndroidFindBy(uiAutomator ="new UiSelector().textContains(\""+ConstantName.LOCATION+"\")")
	public static MobileElement location;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\""+ConstantName.LOCATING_METHOD+"\")")
	public static MobileElement locationgMethod;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\""+ConstantName.GPS_ONLY+"\")")
	public static MobileElement gpsOnly;
	
	
	//Flipkart Locator
	public static By flipkartSearchBar = By.id("com.flipkart.android:id/search_widget_textbox");
	public static String flipkartSearchEditBox = "Search grocery products in Supermart";
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\""+ConstantName.SEARCH_ITEM_NAME+"\")")
	public static MobileElement searchedItem;
	public static String flipKartAccessId = "product title";
	public static By priceLayoutId = By.id("com.flipkart.android:id/price_layout");
	public static By priceAttribute = By.className("android.widget.TextView");
	
	public static By filterIcon = By.xpath("//android.widget.TextView[@text='"+ConstantName.FILTER_ICON+"']");
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\""+ConstantName.CATEGORY+"\")")
	public static MobileElement categoryElement;
	
	public static By categoryType = By.xpath("//android.widget.TextView[@text='"+ConstantName.CATEGORY_TYPE+"']");
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\""+ConstantName.BRAND+"\")")
	public static MobileElement brand;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\""+ConstantName.BRAND_NAME+"\")")
	public static MobileElement brandName;

  	public static By doneButton = By.xpath("//android.widget.TextView[@text='"+ConstantName.DONE_BUTTON+"']");
  	
  	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\""+ConstantName.CONNECTIVITY+"\")")
  	public static MobileElement connectivity;
  
  	public static By connectivityType = By.xpath("//android.widget.TextView[@text='"+ConstantName.CONNECTIVITY_TYPE+"']");
  	
  	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\""+ConstantName.MORE_FILTERS+"\")")
  	public static MobileElement moreFilters;

  	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\""+ConstantName.COLOR+"\")")
  	public static MobileElement color;
  
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\""+ConstantName.COLOR_TYPE+"\")")
  	public static MobileElement colorType;
	
	public static By applyFilter = By.xpath("//android.widget.TextView[@text='"+ConstantName.APPLY_FILTER+"']");
	
	//System settings
	public static By wiFiSetting = By.xpath("//android.widget.TextView[@text='"+ConstantName.WI_FI+"']");
	public static By wiFiSwitch = By.id("com.android.settings:id/switch_widget");
	public static By switchText= By.id("com.android.settings:id/switch_text");
	
	
}