package com.crm.generic.objectrepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.webdriver.UtilityClassObject;

public class ContactsInfoPage {

	public ContactsInfoPage() {
		PageFactory.initElements(UtilityClassObject.getDriver(), this);
	}

	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;

	@FindBy(xpath  = "//span[@id='dtlview_Support Start Date']")
	private WebElement startDate;
	
	@FindBy(xpath  = "//span[@id='dtlview_Support End Date']")
	private WebElement endDate;

	public String getHeaderMessage() {
		return headerMsg.getText();
	}
	
	public String getStartDate() {
		return startDate.getText();
	}
	
	public String getEndDate() {
		return endDate.getText();
	}
}
