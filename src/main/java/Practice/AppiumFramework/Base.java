package Practice.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {

	public AppiumDriverLocalService service;

	static AndroidDriver<AndroidElement> driver;
	
	public AppiumDriverLocalService startServer() {

		boolean flag = checkIfServerIsRunnning(4723);

		if (!flag) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();

		}
		return service;
	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public void startEmulator() throws IOException, InterruptedException {

		Runtime.getRuntime().exec(System.getProperty("user dir") + "\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(6000);
	}

	public AndroidDriver<AndroidElement> Capabilities(String AppName) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream(System.getProperty("user dir") + "\\src\\General-Store.apk");
		Properties prop = new Properties();
		prop.load(fis);

		File f = new File("src");
		File fs = new File(f, (String) prop.get(AppName));
		//String device = (String) prop.get("device");
		String device=System.getProperty("deviceName");
		DesiredCapabilities cap = new DesiredCapabilities();
		if (device.contains("Emulator")) {

			startEmulator();
		}
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Google Chrome");
		driver = new AndroidDriver<AndroidElement>(
				new URL("http://127.0.0.1:4357/wd/hub"), cap);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}
	
	public static void sreenshot(String s) throws IOException {
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("C:\\users\\owner\\defectscreen.png"));
;	}

}
