package Practice.AppiumFramework;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class eCommerce extends Base {

	AndroidDriver<AndroidElement> driver;
	@Test(dataProvider="inputData", dataProviderClass=TestData.class)
	public void totalValidation(String Input) throws IOException, InterruptedException {
		
		startServer();

		driver = Capabilities("App");

		driver.findElement(By.id("spinnerCountry")).click();
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"India\"));").click();
		driver.findElement(By.id("nameField")).sendKeys("Pankaj");
		driver.findElement(By.cssSelector("#radioFemale")).click();
		driver.findElement(By.xpath("android.widget.Button[@id='btnLetsShop']")).click();
		Utilities u = new Utilities(driver);
		u.scrollToText("India");
		service.stop();
		
	}
	
	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException {
		
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
	
	
}
