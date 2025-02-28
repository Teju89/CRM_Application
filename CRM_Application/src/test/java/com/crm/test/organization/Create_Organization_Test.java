package com.crm.test.organization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.crm.generic.baseclass.BaseConfigClass;
import com.crm.generic.entity.Organization;
import com.crm.generic.objectrepo.CreatingNewOrganizationPage;
import com.crm.generic.objectrepo.HomePage;
import com.crm.generic.objectrepo.OrganizationInfoPage;
import com.crm.generic.objectrepo.OrganizationPage;
import com.crm.generic.webdriver.UtilityClassObject;

@Listeners(com.crm.generic.listerner.ListenerImpl.class)
public class Create_Organization_Test extends BaseConfigClass {

	@DataProvider(name = "testData")
	public Iterator<Organization> getData() throws Exception {
		int row = excel.getRowCount("OrgData");
		List<Organization> organizations = new ArrayList<Organization>();
		for (int i = 1; i < row; i++) {
			Organization org = new Organization();
			org.setOrgName(excel.getDataFromExcel("OrgData", i, 0));
			org.setIndustry(excel.getDataFromExcel("OrgData", i, 2));
			org.setType(excel.getDataFromExcel("OrgData", i, 3));
			organizations.add(org);
		}
		return organizations.iterator();
	}

	@Test(dataProvider = "testData", groups = "smokeTesting")
	public void createOrganizationWithName(Organization org) throws Exception {
		HomePage homePage = new HomePage();
		homePage.goToOrganization();
		UtilityClassObject.getTest().log(Status.INFO, "=======> Organization Page <========");

		OrganizationPage organizationPage = new OrganizationPage();
		organizationPage.clickOnCreateOrgBtn();
		UtilityClassObject.getTest().log(Status.INFO, "=======> Clicked on Create button <========");

		CreatingNewOrganizationPage orgPage = new CreatingNewOrganizationPage();
		String name = org.getOrgName();
		orgPage.createOrgWithName(name);
		UtilityClassObject.getTest().log(Status.INFO, "=======> Creating Org with name <========");

		OrganizationInfoPage infoPage = new OrganizationInfoPage();
		String header = infoPage.getHeaderMessage().getText();

		SoftAssert asert = new SoftAssert();
		asert.assertEquals(true, header.contains(name), "Organization Created Successfully...");

		asert.assertAll();
	}
	
	@Test(dataProvider = "testData", groups = "RegressionTesting")
	public void createOrganizationWithIndustryAndType(Organization org) {
		HomePage homePage = new HomePage();
		homePage.goToOrganization();
		UtilityClassObject.getTest().log(Status.INFO, "=======> Organization Page <========");

		OrganizationPage organizationPage = new OrganizationPage();
		organizationPage.clickOnCreateOrgBtn();
		UtilityClassObject.getTest().log(Status.INFO, "=======> Clicked on Create button <========");

		CreatingNewOrganizationPage orgPage = new CreatingNewOrganizationPage();
		String name = org.getOrgName();
		String industry = org.getIndustry();
		String type = org.getType();
		orgPage.createOrgWithNameAndIndustry(name, industry, type);
		UtilityClassObject.getTest().log(Status.INFO, "=======> Creating Org with name, industry and type <========");

		OrganizationInfoPage infoPage = new OrganizationInfoPage();
		String header = infoPage.getHeaderMessage().getText();

		SoftAssert asert = new SoftAssert();
		asert.assertEquals(true, header.contains(name), "Organization Created Successfully...");
		asert.assertEquals(true, industry.equals(infoPage.getSelectedIndustry()), industry + " selected...");
		asert.assertEquals(true, type.equals(infoPage.getSelectedType()), type + " selected...");

		asert.assertAll();
	}
}
