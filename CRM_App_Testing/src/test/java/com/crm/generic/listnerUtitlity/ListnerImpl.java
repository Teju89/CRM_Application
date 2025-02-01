package com.crm.generic.listnerUtitlity;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.generic.configurationUtility.BaseClass;
import com.crm.generic.fileUtitlity.PropertyFileUtility;
import com.crm.generic.webDriverUtility.JavaUtitlity;

public class ListnerImpl implements ITestListener, ISuiteListener {

	ExtentReports report;
	ExtentTest test;
	JavaUtitlity utitlity = new JavaUtitlity();
	PropertyFileUtility fileUtility = new PropertyFileUtility();

	@Override
	public void onStart(ISuite suite) {
		// Report Configuration
		try {
			ExtentSparkReporter spark = new ExtentSparkReporter(
					"./Reports/report" + utitlity.getCurrentDateTime() + ".html");
			spark.config().setDocumentTitle("CRM Test Suite Results");
			spark.config().setReportName("CRM Report");
			spark.config().setTheme(Theme.DARK);

			report = new ExtentReports();
			report.attachReporter(spark);
			report.setSystemInfo("browser", fileUtility.getDataFromPropertyFile("browser"));
			report.setSystemInfo("Windows", System.getProperty("os.name"));
		} catch (Exception e) {
		}
	}
	

	@Override
	public void onFinish(ISuite suite) {
		// Report backup
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO, result.getMethod().getMethodName() + "*****Started******");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// take screenshot
		toTakeScreenShot(result.getMethod().getMethodName());
		test.log(Status.INFO, result.getMethod().getMethodName() + "*****Started******");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// take screenshot
		toTakeScreenShot(result.getMethod().getMethodName());
		test.log(Status.INFO, result.getMethod().getMethodName() + "*****Failure******");
	}

	public void toTakeScreenShot(String testName) {
		TakesScreenshot ts = (TakesScreenshot) BaseClass.driver;
		String source = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(source, testName + "_" + utitlity.getCurrentDateTime());
	}

}
