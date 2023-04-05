package testscripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericlibraries.BaseClass;

public class Test3 extends BaseClass{
	@Test
	public void test3() {

SoftAssert soft= new SoftAssert();
	
	home.clickGearsTab(); 
	home.clickSkillraryDemoApp();
	web.handleChildBrowser();
	
	soft.assertEquals(demoapp.getPageHeader(),"SkillRary-ECommerce");
	web.scrollTillElement(demoapp.getContactUsLink());
	
	demoapp.clickContactUs();
	soft.assertTrue(contact.getContactUsIcon().isDisplayed());
	
	
	Map<String,String> map=excel.readData("Sheet1");
	contact.sendContactInfo(map.get("Full Name"), map.get("Email"),map.get("Subject"),map.get("Message"));
	
	
	soft.assertAll();
   }
} 

