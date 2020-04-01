package ebaydemo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pageObjects.EbayPageObject;

import ebaydemo.EbayMobileAutomation.utils.Utilities;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import ebaydemo.EbayMobileAutomation.utils.*;

public class AddToCart {
	AndroidDriver<MobileElement> driver;
	private static Logger log=LogManager.getLogger(AddToCart.class.getName());
	@BeforeTest
	
	//Using apk file from Project
	public void launchApp() throws IOException
	{
		log.info(this.getClass());
		File f=new File("Amazon Shopping Search Find Ship and Save_v20.6.0.100_apkpure.com.apk");
		
		DesiredCapabilities capability = new DesiredCapabilities();
		//OS Name
		String device=Utilities.getProperty("device");
		capability.setCapability(Constants.CAPS_DEVICE,device);
		//Mobile OS version.
		String version=Utilities.getProperty("version");
		capability.setCapability(MobileCapabilityType.VERSION, version);
		//set up device name
		String deviceName=Utilities.getProperty("deviceName");
		capability.setCapability(MobileCapabilityType.DEVICE_NAME,deviceName);
		capability.setCapability(MobileCapabilityType.APP,f.getAbsolutePath());
		String url=Utilities.getProperty("URL");
		driver = new AndroidDriver<MobileElement>(new URL(url), capability);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	//using pre-installed app
	/*public void launchApp() throws IOException
	{
		log.info(this.getClass());
		DesiredCapabilities capability = new DesiredCapabilities();
		//OS Name
		String device=Utilities.getProperty("device");
		capability.setCapability(Constants.CAPS_DEVICE,device);
		//Mobile OS version.
		String version=Utilities.getProperty("version");
		capability.setCapability(MobileCapabilityType.VERSION, version);
		//set up device name
		String deviceName=Utilities.getProperty("deviceName");
		capability.setCapability(MobileCapabilityType.DEVICE_NAME,deviceName);
		String platformName=Utilities.getProperty("platformName");
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME,platformName);
		//set the package name of the app
		String appPackage=Utilities.getProperty("appPackage");
		capability.setCapability(Constants.CAPS_APP_PACKAGE, appPackage);
		//set the Launcher activity name of the app
		String appActivity=Utilities.getProperty("appActivity");
		capability.setCapability(Constants.CAPS_APP_ACTIVITY, appActivity);
		String url=Utilities.getProperty("URL");
		driver = new AndroidDriver<MobileElement>(new URL(url), capability);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}*/
	//@Parameters({"tvSize","tvName","price","description","modelNo"})
	@Test
	//public void addToCartOperation(String tvSize,String tvName,String price,String description,String modelNo) throws InterruptedException
	public void addToCartOperation() throws InterruptedException
	{
		EbayPageObject ebPage=new EbayPageObject(driver);
		log.info("Inside add to cart");
		Utilities.staticWait(10000);
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		/*MobileElement buttonSignIn=driver.findElement(By.id("com.ebay.mobile:id/button_sign_in"));
		wait.until(ExpectedConditions.elementToBeClickable(buttonSignIn));
		//driver.findElement(By.id("com.ebay.mobile:id/button_sign_in")).click();
		buttonSignIn.click();*/
		
		//ebPage.clickOnSignIn();
		
		/*MobileElement buttonUserName=driver.findElement(By.id("com.ebay.mobile:id/button_classic"));
		wait.until(ExpectedConditions.elementToBeClickable(buttonUserName));
		//driver.findElement(By.id("com.ebay.mobile:id/button_classic")).click();
		buttonUserName.click();*/
		
		//ebPage.clickOnUserName();
		
	//	wait.until(ExpectedConditions.elementToBeClickable(By.id("com.ebay.mobile:id/button_classic")));
	//	AndroidElement textboxUserName=driver.findElement(By.id("com.ebay.mobile:id/button_classic"));
		
		try {
			List<String> list=Utilities.getData("tesData.xlsx", "ebay", "TC_01");
			String item=list.get(1);
			ebPage.searchItem(item);
			ebPage.selectItemAndAddToCart(list.get(2),list.get(5));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@AfterTest
	public void closeBrowser()
	{
		driver.closeApp();
		driver.quit();
	}

}
