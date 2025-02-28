package com.crm.generic.objectrepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.webdriver.UtilityClassObject;


public class ContactsPage {
	
	public ContactsPage() {
		PageFactory.initElements(UtilityClassObject.getDriver(), this);
	}

	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createNewContactBtn;
	
	public void clickOnCreateContactBtn(){
		createNewContactBtn.click();
	}
}
