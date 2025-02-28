package com.crm.generic.objectrepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.javaUtitlity.JavaUtility;
import com.crm.generic.webdriver.UtilityClassObject;
import com.crm.generic.webdriver.WebDriverUtility;

public class CreatingNewOrganizationPage{

	private WebDriverUtility webdriver = new WebDriverUtility();
	private JavaUtility utitlity = new JavaUtility();

	public CreatingNewOrganizationPage() {
		PageFactory.initElements(UtilityClassObject.getDriver(), this);
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
