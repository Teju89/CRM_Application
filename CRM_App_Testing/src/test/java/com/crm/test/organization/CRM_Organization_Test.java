package com.crm.test.organization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.generic.configurationUtility.BaseClass;
import com.crm.generic.entity.Organization;
import com.crm.generic.fileUtitlity.ExcelFileUtitlity;
import com.crm.generic.fileUtitlity.PropertyFileUtility;
import com.crm.generic.objectrepo.CreatingNewOrganizationPage;
import com.crm.generic.objectrepo.HomePage;
import com.crm.generic.objectrepo.LoginPage;
import com.crm.generic.objectrepo.OrganizationInfoPage;
import com.crm.generic.objectrepo.OrganizationPage;

@Listeners(com.crm.generic.listnerUtitlity.ListnerImpl.class)
public class CRM_Organization_Test extends BaseClass {
	
	
	PropertyFileUtility property = new PropertyFileUtility();
	ExcelFileUtitlity excel = new ExcelFileUtitlity();
	
	
	@DataProvider(name = "testData")
	public Iterator<Organization>  getData() throws Exception{
		int row = excel.getRowCount("OrgData");
		List<Organization> organizations = new ArrayList<Organization>();
		for(int i = 1; i < row; i++) {
			Organization org = new Organization();
			org.setOrgName(excel.getDataFromExcel("OrgData", i, 0));
			organizations.add(org);
		}
		return organizations.iterator();
	}

	@Test(dataProvider = "testData")
	public void createOrganizationWithName(Organization org) throws Exception {
		HomePage homePage = new HomePage(driver);
		homePage.goToOrganization();
		
		OrganizationPage organizationPage = new OrganizationPage(driver);
		organizationPage.clickOnCreateOrgBtn();
		
		CreatingNewOrganizationPage orgPage = new CreatingNewOrganizationPage(driver);
		String name = org.getOrgName();
		orgPage.createOrgWithName(name);
		
		OrganizationInfoPage infoPage = new OrganizationInfoPage(driver);
		String header = infoPage.getHeaderMessage().getText();
		
		SoftAssert asert = new SoftAssert();
		asert.assertEquals(true, header.contains(name), "Org Created Successfully...");
		
		asert.assertAll();
	}
}












