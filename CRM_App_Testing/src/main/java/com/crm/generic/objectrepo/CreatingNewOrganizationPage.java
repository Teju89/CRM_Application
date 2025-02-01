package com.crm.generic.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.webDriverUtility.JavaUtitlity;
import com.crm.generic.webDriverUtility.WebDriverUtitlity;

public class CreatingNewOrganizationPage {

	private WebDriver driver;
	private WebDriverUtitlity webdriver = new WebDriverUtitlity();
	JavaUtitlity utitlity = new JavaUtitlity();

	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name = "industry")
	private WebElement industrySelect;
	
	@FindBy(name = "accounttype")
	private WebElement typeSelect;
	
	public void createOrgWithName(String orgName) {
		orgNameEdt.sendKeys(orgName+utitlity.getRandomNumber(100));
		saveBtn.click();
	}
	
	public void createOrgWithNameAndIndustry(String orgName, String industry, String type) {
		
		orgNameEdt.sendKeys(orgName+utitlity.getRandomNumber(100));
		webdriver.selectFromDropDown(industrySelect, industry);
		webdriver.selectFromDropDown(typeSelect, type);
		saveBtn.click();
	}
}










