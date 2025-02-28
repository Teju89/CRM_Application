package com.crm.generic.objectrepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.webdriver.UtilityClassObject;

public class OrganizationPage {

	public OrganizationPage() {
		PageFactory.initElements(UtilityClassObject.getDriver(), this);
	}
	
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createNewOrgBtn;
	
	public void clickOnCreateOrgBtn(){
		createNewOrgBtn.click();
	}
}
