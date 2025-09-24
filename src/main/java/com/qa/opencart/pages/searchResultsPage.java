package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class searchResultsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productResults = By.xpath("//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']");
	
	public searchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	public int getSearchProductResultsCount() {
		return eleUtil.waitForElementsVisible(productResults, AppConstants.MEDIUM_TIME_OUT).size();
	}
	
	public ProductInfoPage selectProduct(String productName) {
		eleUtil.clickElementWhenReady(By.linkText(productName), AppConstants.MEDIUM_TIME_OUT);
		return new ProductInfoPage(driver);
	}

}
