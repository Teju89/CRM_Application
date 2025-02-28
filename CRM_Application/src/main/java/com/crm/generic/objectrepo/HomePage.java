package com.crm.generic.objectrepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.webdriver.UtilityClassObject;
import com.crm.generic.webdriver.WebDriverUtility;


public class HomePage {

	private WebDriverUtility utitlity = new WebDriverUtility();
	
	public HomePage() {
		PageFactory.initElements(UtilityClassObject.getDriver(), this);
	}

	@FindBy(xpath = "//td[@align='center']/a[text()='Organizations']")
	private WebElement orgLink;
	
	@FindBy(xpath = "//td[@align='center']/a[text()='Contacts']")
	private WebElement contactLink;
	
	@FindBy(xpath = "//td/a[text()='Campaigns']")
	private WebElement campaignLink;
	
	@FindBy(xpath = "//a[text()='More']")
	private WebElement moreLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImage;
	
	@FindBy(linkText = "Sign Out" )
	private WebElement signOut;
	
	public void navigateToCampaignPage() {
		utitlity.mouseMoveToElement(UtilityClassObject.getDriver(), moreLink);
		campaignLink.click();
	}
	
	public void goToOrganization() {
		orgLink.click();
	}
	
	public void goToContacts() {
		contactLink.click();
	}
	
	public void logout() {
		utitlity.mouseMoveToElement(UtilityClassObject.getDriver(), adminImage);
		signOut.click();
	}
}
