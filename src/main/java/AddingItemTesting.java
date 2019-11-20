

	

	import java.io.File;
	import java.net.MalformedURLException;
	import java.net.URL;
	import java.util.concurrent.TimeUnit;

	import org.junit.Assert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.remote.DesiredCapabilities;

	import io.appium.java_client.MobileBy;

	import io.appium.java_client.android.AndroidDriver;

	import io.appium.java_client.android.AndroidElement;
	import io.appium.java_client.remote.MobileCapabilityType;



	public class AddingItemTesting {

		public static void main(String[] args) throws MalformedURLException, InterruptedException {
			File f = new File("src");
			File fs = new File(f, "General-Store.apk");

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus5_API_23");
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
			capabilities.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
			AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
					new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("FirstName and LastName");

			driver.hideKeyboard();

			driver.findElement(By.xpath("//*[@text='Female']")).click();

			driver.findElement(By.id("android:id/text1")).click();

			driver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));");

			driver.findElement(By.xpath("//*[@text='Australia']")).click();

			driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

			driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Jordan Lift Off\").instance(0))"));

			int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

			for (int i = 0; i < count; i++)

			{

				String text = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();

				if (text.equalsIgnoreCase("Jordan Lift Off"))

				{

					driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();

					break;
				}
			}

			driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

			String lastpageText = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
			

			Assert.assertEquals("Jordan Lift Off", lastpageText);

		}

	}


