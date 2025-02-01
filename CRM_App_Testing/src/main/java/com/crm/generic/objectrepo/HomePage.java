package com.crm.generic.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.webDriverUtility.WebDriverUtitlity;

public class HomePage {

	private WebDriver driver;
	private WebDriverUtitlity utitlity = new WebDriverUtitlity();
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
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
		utitlity.mouseMoveToElement(driver, moreLink);
		campaignLink.click();
	}
	
	public void goToOrganization() {
		orgLink.click();
	}
	
	public void logout() {
		utitlity.mouseMoveToElement(driver, adminImage);
		signOut.click();
	}
}















