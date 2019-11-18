
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import java.io.File;

public class ShoppingAppTest {

	private static String pixel = "Pixel_API_28";
	private static String nexus = "NEXUS";

	private static String EMULATOR_NAME = "NEXUS";
	private static String UI_AUTO_NAME = "uiautomator2";
	private static boolean isRealDevice = false;
	private static boolean isApk = true;
	private static String PACKAGE_NAME = "com.bfa.android";
	private static String SHOPPING_APK = "General-Store.apk";
	private static String API_DEMOS = "ApiDemos-debug.apk";
	private static String Browser = "Chrome";
	private static AndroidDriver<AndroidElement> driver = null;

	public static void main(String[] args) {
		DriverFactory driverFactory = new DriverFactory();

		try {
			driver = driverFactory.create(EMULATOR_NAME, UI_AUTO_NAME, isRealDevice, isApk, SHOPPING_APK);
			runApkTests(driver);

		} catch (MalformedURLException k) {
			System.out.println(" hey this path is wrong with error :" + k.getLocalizedMessage());
			// } catch (InterruptedException e) {
			// System.out.println(e.getLocalizedMessage());

		}

	}

	private static void runApkTests(AndroidDriver<AndroidElement> driver) {
		// test login success and failure (toastMessage)

		testLogIn(driver);
		// testAddingItems(driver);

	}

	private static void testLogIn(AndroidDriver<AndroidElement> driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementByXPath("//android.widget.TextView [@ text = 'Afghanistan']").click();
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Antarctica\"));");
		driver.findElementByXPath("//android.widget.TextView [@text = 'Antarctica']").click();
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("FirstName and LastName");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//*[@text='Female']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		AndroidElement backButton = driver.findElementById("com.androidsample.generalstore:id/appbar_btn_back");
		if (backButton != null) {
			backButton.click();
		}

		driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/"
						+ "android.widget.FrameLayout/android.widget.LinearLayout/android.widget.EditText")
				.clear();

		// Log in failure

		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");

		System.out.println(toastMessage);

		Assert.assertEquals("Please enter your name", toastMessage);

		try {
			Thread.sleep(5000);

		} catch (InterruptedException e) {

			e.printStackTrace();

		}
		// driver.quit();
//	}
//
//
//	private static void testAddingItems(AndroidDriver<AndroidElement> driver) {
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.findElementByXPath("//android.widget.TextView [@ text = 'Afghanistan']").click();
//		driver.findElementByAndroidUIAutomator(
//				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Antarctica\"));");
//		driver.findElementByXPath("//android.widget.TextView [@text = 'Antarctica']").click();
//		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("FirstName and LastName");
//		driver.findElement(By.xpath("//*[@text='Female']")).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("FirstName and LastName");
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));

		int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

		for (int i = 0; i < count; i++)

		{

			String text = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();

			if (text.equalsIgnoreCase("Jordan 6 Rings"))

			{

				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();

				break;

			}

		}

		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

		String lastpageText = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();

		Assert.assertEquals("Jordan 6 Rings", lastpageText);

	}

}
