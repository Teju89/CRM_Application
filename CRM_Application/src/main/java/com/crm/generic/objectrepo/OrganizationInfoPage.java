package com.crm.generic.objectrepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.webdriver.UtilityClassObject;

public class OrganizationInfoPage {

	public OrganizationInfoPage() {
		PageFactory.initElements(UtilityClassObject.getDriver(), this);
	}
	
	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(id = "mouseArea_Industry")
	private WebElement industry;
	
	@FindBy(id = "dtlview_Type")
	private WebElement type;
	
	public WebElement getHeaderMessage() {
		return headerMsg;
	}
	
	public String getSelectedIndustry() {
		return industry.getText();
	}
	
	public String getSelectedType() {
		return type.getText();
	}
}
