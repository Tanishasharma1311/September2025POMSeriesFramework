package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;


public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("browser name is: " + browserName);
		optionsManager = new OptionsManager(prop);
		switch(browserName.toLowerCase()) {
		case "chrome":
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "Edge":
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));

			break;
			default:
				System.out.println("Please pass the right browser name");
				break;
			
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}
	
	public static WebDriver getDriver() {
	return	tlDriver.get();
	}
	
	/**
	 * This method is used to init the properties
	 * @return
	 */
	public Properties initProp() {
		FileInputStream ip = null;
		 prop = new Properties();
	String envName = System.getProperty("env");
	System.out.println("env name is: " + envName);
	try {
	if(envName == null) {
		System.out.println("no env is given....hence running it on QA env.....by default");
		ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
	}else {
		switch(envName.toLowerCase().trim()) {
		case "qa":
			ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			break;
		case "stage":
			ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
			break;
		case "dev":
			ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
			break;
		case "uat":
			ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
			break;
		case "prod":
			ip = new FileInputStream("./src/test/resources/config/config.properties");
			break;
			default:
				System.out.println("please pass the right env name: " + envName);
				break;
		}
	}
	}catch(FileNotFoundException e) {
		e.printStackTrace();
	}
	try {
		prop.load(ip);
	} catch (IOException e) {
		e.printStackTrace();
	}
		
		 return prop;
		
	}
	
	/**
	 * take screenshot
	 */
	
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			//FileUtil.copyFile(srcFile, destination);
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return path;
	}
}
