package com.crm.generic.listerner;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.generic.baseclass.BaseConfigClass;
import com.crm.generic.webdriver.UtilityClassObject;

public class ListenerImpl extends BaseConfigClass implements ITestListener, ISuiteListener{

	public ExtentReports report;
	public ExtentTest test;

	@Override
	public void onStart(ITestContext context) {
		System.out.println("On Start");
		// Report Configuration
		try {
			ExtentSparkReporter spark = new ExtentSparkReporter(
					"./Reports/report" + javaUtility.getCurrentDateTime() + ".html");
			spark.config().setDocumentTitle("CRM Test Suite Results");
			spark.config().setReportName("CRM Report");
			spark.config().setTheme(Theme.DARK);
			report = new ExtentReports();
			report.attachReporter(spark);
			report.setSystemInfo("browser",  propertyFileUtility.getDataFromPropertyFile("browser"));
			report.setSystemInfo("Windows", System.getProperty("os.name"));
		} catch (Exception e) {
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("On Finish");
		// Report backup
		report.flush();
		test.log(Status.INFO, "*****Finish******");
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("fhtxtyqwegdyqetgdfjew");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName() + "*****Started******");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// take screenshot
		toTakeScreenShot(result.getMethod().getMethodName());
		test.log(Status.PASS, result.getMethod().getMethodName() + "*****Passed******");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// take screenshot
		toTakeScreenShot(result.getMethod().getMethodName());
		test.log(Status.FAIL, result.getMethod().getMethodName() + "*****Failure******");
	}

	public void toTakeScreenShot(String testName) {
		TakesScreenshot ts = (TakesScreenshot) UtilityClassObject.getDriver();
		String source = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(source, testName + "_" + javaUtility.getCurrentDateTime());
		test.log(Status.INFO,"*****SS Has captured******");
	}
}
