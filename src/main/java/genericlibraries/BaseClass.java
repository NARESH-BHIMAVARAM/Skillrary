package genericlibraries;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pageobjectmodel_POM.ContactUsPage;
import pageobjectmodel_POM.HomePage;
import pageobjectmodel_POM.SeleniumTrainingPage;
import pageobjectmodel_POM.SkillraryDemoAppPage;
import pageobjectmodel_POM.TestingPage;

public class BaseClass {
	protected WebDriverUtility web;
	protected PropertyFileUtility property;
	protected ExcelUtility excel;
	protected JavaUtility jutil;
	protected WebDriver driver;
	protected HomePage home;
	protected SkillraryDemoAppPage demoapp;
	protected SeleniumTrainingPage selenium;
	protected TestingPage testing;
	protected ContactUsPage contact;
//	@BeforeSuite
//	@BeforeTest
	@BeforeClass
	public void classConfiguration() {
		
		web=new WebDriverUtility();
		property=new PropertyFileUtility();
		excel=new ExcelUtility();
		jutil=new JavaUtility();
		
		property.propertyConfig(IConstentPath.PROPERTIES_PATH);
		String browser=property.fetchProperty("browser");
		String url=property.fetchProperty("url");
		long time=Long.parseLong(property.fetchProperty("time"));
		driver=web.openApplication(browser, url, time);
	}
	@BeforeMethod
	public void methodConfiguration() {
		excel.excelInitialization(IConstentPath.EXCEL_PATH);
		
		home=new HomePage(driver);
		demoapp=new SkillraryDemoAppPage(driver);
		selenium=new SeleniumTrainingPage(driver);
		testing=new TestingPage(driver);
		contact=new ContactUsPage(driver);
		
		Assert.assertTrue(home.getLogo().isDisplayed());
	}
	@AfterMethod
	public void methodTearDown() {
		excel.closeWorkbook();
	}
	
	@AfterClass
	public void classTearDown() {
		web.quitBrowser();
	}
//	@AfterTest
//	@AfterSuite
	

}
