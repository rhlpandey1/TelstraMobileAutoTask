package com.pageObjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class EbayPageObject {
	   private AndroidDriver<MobileElement> driver;
	    public EbayPageObject() {
	    }
	    public EbayPageObject(AndroidDriver<MobileElement> driver) {
	        this.driver = driver;
	        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	    }
	    public static Logger log = Logger.getLogger(EbayPageObject.class);
	    @AndroidFindBy(id = "com.ebay.mobile:id/button_sign_in")
	    private MobileElement buttonSignIn;
	    @AndroidFindBy(id = "com.ebay.mobile:id/button_classic")
	    private MobileElement buttonUserName;
	    @AndroidFindBy(id = "com.ebay.mobile:id/button_classic")
	    private MobileElement textboxUserName;
	    @AndroidFindBy(id = "com.ebay.mobile:id/button_classic")
	    private MobileElement textboxPassword;
	    @AndroidFindBy(id = "com.ebay.mobile:id/button_classic")
	    private MobileElement buttonSignInSubmit;
	    @AndroidFindBy(id = "com.ebay.mobile:id/search_box")
	    private MobileElement textboxSearch;
	    @AndroidFindBy(id = "com.ebay.mobile:id/search_src_text")
	    private MobileElement textboxSearchInside;
	    
	    /*@AndroidFindBy(xpath = "/*[text()='65 inch tv TV, Video & Home Audio Electronics']")
	    private MobileElement textTvSearch;*/
	    @AndroidFindBy(id = "com.ebay.mobile:id/textview_item_title")
	    private List<MobileElement> listOfTvs;
	    
	    public void clickOnSignIn()
	    {
	    	WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(buttonSignIn));
			buttonSignIn.click();
			log.info("Clicked on sign in button");
	    }
	    public void clickOnUserName()
	    {
	    	WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(buttonUserName));
			buttonUserName.click();
	    }
	    public void enterUserNameAndPassword(String userName,String password)
	    {
	    	WebDriverWait wait = new WebDriverWait(driver, 30);
	    	wait.until(ExpectedConditions.elementToBeClickable(textboxUserName));
	    	textboxUserName.sendKeys(userName);
	    	textboxPassword.sendKeys(password);
	    	buttonSignInSubmit.click();
	    }
	    public void SignIn(String userName,String password)
	    {
	    	clickOnSignIn();
	    	clickOnUserName();
	    	enterUserNameAndPassword(userName,password);
	    }
	    public void searchItem(String item)
	    {
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(textboxSearch));
				textboxSearch.sendKeys(item);
				textboxSearchInside.sendKeys(item);
				driver.pressKeyCode(AndroidKeyCode.ENTER);
	    	
	    }
	    
	    
}
