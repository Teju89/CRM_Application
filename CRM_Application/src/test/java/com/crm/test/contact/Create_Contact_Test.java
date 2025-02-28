package com.crm.test.contact;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.crm.generic.baseclass.BaseConfigClass;
import com.crm.generic.entity.Contact;
import com.crm.generic.objectrepo.ContactsInfoPage;
import com.crm.generic.objectrepo.ContactsPage;
import com.crm.generic.objectrepo.CreatingNewContactsPage;
import com.crm.generic.objectrepo.HomePage;
import com.crm.generic.webdriver.UtilityClassObject;

@Listeners(com.crm.generic.listerner.ListenerImpl.class)
public class Create_Contact_Test extends BaseConfigClass {

	@DataProvider(name = "testData")
	public Iterator<Contact> getContactsData() throws Throwable {
		int row = excel.getRowCount("ContactData");
		List<Contact> contacts = new ArrayList<Contact>();
		System.out.println(row);
		for (int i = 1; i < row; i++) {
			Contact contact = new Contact();
			contact.setFirstName(excel.getDataFromExcel("ContactData", i, 0));
			contact.setLastName(excel.getDataFromExcel("ContactData", i, 1));
			contact.setMobile(Long.parseLong(excel.getDataFromExcel("ContactData", i, 2)));
			contact.setOrgName(excel.getDataFromExcel("ContactData", i, 3));
			contacts.add(contact);
		}
		return contacts.iterator();
	}

	@Test(dataProvider = "testData", groups = "smokeTesting")
	public void createContactWithMandatoryFields(Contact contact) {
		System.out.println("Create Contact");
		HomePage homePage = new HomePage();
		homePage.goToContacts();
		UtilityClassObject.getTest().log(Status.INFO, "======> contact page appeard <======");

		ContactsPage contactsPage = new ContactsPage();
		contactsPage.clickOnCreateContactBtn();
		UtilityClassObject.getTest().log(Status.INFO, "======> clicked on create button <======");

		CreatingNewContactsPage newContactsPage = new CreatingNewContactsPage();
		newContactsPage.createContactWithLastName(contact.getLastName(), contact.getMobile());
		UtilityClassObject.getTest().log(Status.INFO, "======> Creating contact <======");

		ContactsInfoPage contactsInfoPage = new ContactsInfoPage();
		String header = contactsInfoPage.getHeaderMessage();
		UtilityClassObject.getTest().log(Status.INFO, "======>" + header + "<======");

		SoftAssert ast = new SoftAssert();
		ast.assertEquals(true, header.contains(contact.getLastName()), "Contact Created...");
		ast.assertAll();
		UtilityClassObject.getTest().log(Status.INFO,"assert");

	}

	@Test(dataProvider = "testData", groups = "RegressionTesting")
	public void createContactWithStartAndEndDate(Contact contact) {
		HomePage homePage = new HomePage();
		homePage.goToContacts();
		UtilityClassObject.getTest().log(Status.INFO, "======> contact page appeard <======");

		ContactsPage contactsPage = new ContactsPage();
		contactsPage.clickOnCreateContactBtn();
		UtilityClassObject.getTest().log(Status.INFO, "======> clicked on create button <======");

		CreatingNewContactsPage newContactsPage = new CreatingNewContactsPage();
		String startDate = javaUtility.getFormattedDate(LocalDate.now().toString());
		String endDate = javaUtility.getRequiredDate(30);

		newContactsPage.createContactWithStartAndEnd(startDate, endDate, contact.getLastName(), contact.getMobile());
		UtilityClassObject.getTest().log(Status.INFO, "======> Creating contact <====== " + endDate);

		ContactsInfoPage contactsInfoPage = new ContactsInfoPage();
		String header = contactsInfoPage.getHeaderMessage();
		UtilityClassObject.getTest().log(Status.INFO, "======>" + header + "<======");

		SoftAssert ast = new SoftAssert();
		ast.assertEquals(true, header.contains(contact.getLastName()), "Contact Created...");
		ast.assertEquals(true, contactsInfoPage.getStartDate().equals(startDate), "Start...");
		ast.assertEquals(true, contactsInfoPage.getEndDate().equals(endDate), "End...");
		ast.assertAll();
	}
}
