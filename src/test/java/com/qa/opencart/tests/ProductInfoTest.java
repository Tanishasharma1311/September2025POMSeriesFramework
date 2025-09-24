package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void prodInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}
	@DataProvider
	public Object[][] productTestData() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"macbook", "MacBook Air"},
			{"iMac", "iMac"}
		};
	}
	@Test(dataProvider = "productTestData")
	public void productHeaderTest(String searchKey, String productName) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
	String actProductHeader =	productInfoPage.getProductHeaderValue();
	Assert.assertEquals(actProductHeader, productName);
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"macbook", "MacBook Pro", 4},
			{"macbook", "MacBook Air", 4},
			{"iMac", "iMac", 3}
		};
	}
	@Test(dataProvider = "productData")
	public void productImagesCountTest(String searchKey, String productName, int expPrdouctImagesCount) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		int actProdIagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actProdIagesCount, expPrdouctImagesCount);
	
	}
	@Test
	public void productInfoTest() {
		searchResPage = accPage.doSearch("macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		Map<String,String> productActualData = productInfoPage.getProductData();
		System.out.println(productActualData);
	}
}
