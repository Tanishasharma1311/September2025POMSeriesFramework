package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void regSetup() {
		regPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmail() {
		return "openauto"+System.currentTimeMillis()+"@open.com";
	}
	@DataProvider
	public Object[][] getUserRegData() {
		return new Object[][] {
			{"pooja", "aggarwal", "9987554434", "poojaagg@gmail.com", "yes"},
			{"kooja", "aggarwal", "9907554434", "poojaafgg@gmail.com", "yes"},
			{"nooja", "aggarwal", "9937554434", "poojaadgg@gmail.com", "yes"},
			{"fooja", "aggarwal", "9982554434", "poojaagsg@gmail.com", "yes"}
		};
	}
	
	@DataProvider
	public Object[][] getUserRegSheetData() {
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}
	@Test(dataProvider = "getUserRegData")
	public void userRegisterTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue(regPage.registerUser(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
	}

}
