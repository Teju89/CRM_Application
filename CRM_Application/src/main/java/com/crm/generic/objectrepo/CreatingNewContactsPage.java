package com.crm.generic.objectrepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.webdriver.UtilityClassObject;

public class CreatingNewContactsPage {

	public CreatingNewContactsPage() {
		PageFactory.initElements(UtilityClassObject.getDriver(), this);
	}

	@FindBy(name = "lastname")
	private WebElement lastNameEdt;

	@FindBy(name = "mobile")
	private WebElement mobileEdt;
	
	@FindBy(name = "support_start_date")
	private WebElement startDateEdt;
	
	@FindBy(name = "support_end_date")
	private WebElement endDateEdt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public void createContactWithLastName(String lastName, long mobile) {
		lastNameEdt.sendKeys(lastName);
		mobileEdt.sendKeys(Long.toString(mobile));
		saveBtn.click();
	}
	
	public void createContactWithStartAndEnd(String start, String end, String lastName, long mobile) {
		lastNameEdt.sendKeys(lastName);
		mobileEdt.sendKeys(Long.toString(mobile));
		startDateEdt.clear();
		startDateEdt.sendKeys(start);
		endDateEdt.clear();
		endDateEdt.sendKeys(end);
		saveBtn.click();
	}

}
