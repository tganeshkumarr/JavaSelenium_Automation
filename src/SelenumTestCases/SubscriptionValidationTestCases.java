package SelenumTestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SubscriptionValidationTestCases 
{

	private WebDriver driver;

	@BeforeMethod
	public void setUp() {
		
		// Set the path to the ChromeDriver executable
		System.setProperty("webdriver.gecko.driver\\","./Resources/geckodriver.exe");

		// Initialize the WebDriver
		driver = new FirefoxDriver();

		// Navigate to the subscription page
		driver.get(Constants.url);
		driver.findElement(By.linkText("English")).click();
	}

	@Test
	public void SubscriptionPackages(){

		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		try {
			// 2. Find and validate subscription packages for SA, Kuwait, and Bahrain
			String[] countries = {"kw", "sa", "bh"};

			for (String country : countries) {

				//WebElement countryDropdown = driver.findElement(By.xpath("//span[@id='arrow']"));
				WebElement countryDropdown = driver.findElement(By.xpath(Constants.arrow));

				countryDropdown.click();

				// Wait for a moment to load the packages for the selected country
				Thread.sleep(2000);


				WebElement countryOption = driver.findElement(By.xpath("//span[@id='"+country+"-contry-lable']"));
				countryOption.click();

				for (int i=1;i<4;i++) {

					String str = "(//*[@class='plan-names']/div/strong)[" + i + "]";
					String planType = driver.findElement(By.xpath(str)).getText();
					String price = driver.findElement(By.xpath("(//*[@class='plan-names']//b[1])[" + i +"]")).getText();
					String currency = driver.findElement(By.xpath("(//*[@class='plan-names']//i)[" + i +"]")).getText();

					System.out.println("Country: " + country);
					System.out.println("Type: " + planType);  
					System.out.println("Price: " + price);
					System.out.println("Currency: " + currency);
					System.out.println("---------------");
				}

			}

		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	//Close the WebDriver
	@AfterMethod

	public void tearDown() {
		driver.quit();
	}

}
