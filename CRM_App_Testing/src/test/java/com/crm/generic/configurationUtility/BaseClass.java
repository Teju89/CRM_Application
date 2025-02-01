package com.crm.generic.configurationUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.crm.generic.fileUtitlity.DatabaseUtility;
import com.crm.generic.fileUtitlity.PropertyFileUtility;
import com.crm.generic.objectrepo.HomePage;
import com.crm.generic.objectrepo.LoginPage;
import com.crm.generic.webDriverUtility.WebDriverUtitlity;

public class BaseClass {
	public static WebDriver driver;
//	DatabaseUtility databaseUtility = new DatabaseUtility();
	PropertyFileUtility propertyFileUtility = new PropertyFileUtility();
	WebDriverUtitlity webDriverUtitlity = new WebDriverUtitlity();

	@BeforeSuite
	public void configBeforeSuite() {
		System.out.println("BS");
//		databaseUtility.getConnection();
	}

	@BeforeClass
	public void configBeforeClass() {
		System.out.println("BC");
		String browser;
		try {
			browser = propertyFileUtility.getDataFromPropertyFile("browser");
			System.out.println(browser);
			switch (browser) {
			case "chrome":
				driver = new ChromeDriver();
				System.out.println("browser launched");
				break;

			case "firefox":
				driver = new FirefoxDriver();
				break;

			default:
				break;
			}
			driver.get(propertyFileUtility.getDataFromPropertyFile("url"));
			driver.manage().window().maximize();
			webDriverUtitlity.waitForPagetoLoad(driver, Integer.parseInt(propertyFileUtility.getDataFromPropertyFile("timeout")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@BeforeMethod
	public void configBeforeMethod() throws Exception {
		System.out.println("BM");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.performLoginOperation(propertyFileUtility.getDataFromPropertyFile("username"),
										propertyFileUtility.getDataFromPropertyFile("password"));
	}
	
	@AfterMethod
	public void configAfterMethod() {
		HomePage homePage = new HomePage(driver);
		homePage.logout();
	}

	@AfterClass
	public void configAfterClass() {
		driver.quit();
	}

	@AfterSuite
	public void configAfterSuite()  {
//		databaseUtility.closeConnection();
	}

}
