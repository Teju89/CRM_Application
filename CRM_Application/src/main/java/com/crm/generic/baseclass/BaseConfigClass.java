package com.crm.generic.baseclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;
import com.crm.generic.fileUtility.ExcelFileUtility;
import com.crm.generic.fileUtility.PropertiesFileUtitlity;
import com.crm.generic.javaUtitlity.JavaUtility;
import com.crm.generic.objectrepo.HomePage;
import com.crm.generic.objectrepo.LoginPage;
import com.crm.generic.webdriver.UtilityClassObject;
import com.crm.generic.webdriver.WebDriverUtility;

public class BaseConfigClass {

	public WebDriver driver;
//	DatabaseUtility databaseUtility = new DatabaseUtility();
	public PropertiesFileUtitlity propertyFileUtility = new PropertiesFileUtitlity();
	public WebDriverUtility webDriverUtitlity = new WebDriverUtility();
	public JavaUtility javaUtility = new JavaUtility();
	public ExcelFileUtility excel = new ExcelFileUtility();

	@BeforeSuite()
	public void configBeforeSuite() {
		System.out.println("BS");
//		databaseUtility.getConnection();
	}

	@BeforeClass()
	@Parameters("browser")
	public void configBeforeClass(String browser) throws Throwable {
		System.out.println("BC");
//		String browser;

//		browser = propertyFileUtility.getDataFromPropertyFile("browser");
		System.out.println(browser);
		switch (browser) {
		case "chrome":
			driver = new ChromeDriver();
			System.out.println("Chrome browser launched");
			break;

		case "firefox":
			driver = new FirefoxDriver();
			System.out.println("firefox browser launched");
			break;
			
		case "edge":
			driver = new EdgeDriver();
			System.out.println("Edge browser launched");
			break;

		default:
			break;
		}
		driver.get(propertyFileUtility.getDataFromPropertyFile("url"));
		driver.manage().window().maximize();
		webDriverUtitlity.waitForPagetoLoad(driver,
				Integer.parseInt(propertyFileUtility.getDataFromPropertyFile("timeout")));

		UtilityClassObject.setDriver(driver);
		System.out.println("BC Completed");
	}

	@BeforeMethod()
	public void configBeforeMethod() throws Exception {
		System.out.println("BM");
		LoginPage loginPage = new LoginPage();
		loginPage.performLoginOperation(propertyFileUtility.getDataFromPropertyFile("username"),
				propertyFileUtility.getDataFromPropertyFile("password"));
	}

	@AfterMethod()
	public void configAfterMethod() {
		HomePage homePage = new HomePage();
		homePage.logout();
		UtilityClassObject.getTest().log(Status.INFO,"after method");
	}

	@AfterClass()
	public void configAfterClass() {
		driver.quit();
		UtilityClassObject.getTest().log(Status.INFO,"after class");
	}

	@AfterSuite()
	public void configAfterSuite() {
//		databaseUtility.closeConnection();
	}

}
