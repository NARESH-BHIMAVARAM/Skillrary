package genericlibraries;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * This class contains all resuable methods of WebDriver
 * @author 91770
 *
 */

public class WebDriverUtility {
	
	private WebDriver driver;
	private Actions a;
	private Select s;
	private JavascriptExecutor js;
	/**
	 * This method is used to launch the browser and navigate
	 * @param browser
	 * @param url
	 * @param time
	 * @return
	 */
	public WebDriver openApplication(String browser,String url, long time) {
		switch(browser) {
		case "chrome":WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;
			default:
				System.out.println("Invalid browser data");
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		return driver;
	}
	/**
	 * This method is used to wait until element is visiable
	 * @param element
	 * @param time
	 * @return
	 */
	public WebElement explicitWait( WebElement element, long time) {
		WebDriverWait wait=new WebDriverWait(driver, time);
		WebElement e=wait.until(ExpectedConditions.visibilityOf(element));
		return e;
	}
	/**
	 * This method is used to mouseHover the element
	 * @param element
	 */
	public void mouseHover(WebElement element) {
		a=new Actions(driver);
		a.moveToElement(element).perform();;
	}
	/**
	 * This method is used to doubleclick on an element
	 * @param element
	 */
	public void doubleClickOnElement(WebElement element) {
		a=new Actions(driver);
		a.doubleClick(element).perform();
	}
	/**
	 * This method i used to rightclick on an element
	 * @param element
	 */
	public void rightClick(WebElement element) {
		a=new Actions(driver);
		a.contextClick().perform();
	}
	/**
	 * This method is used to drag and drop an element in specified element to target
	 * @param element
	 * @param target
	 */
	public void dragAndDropElement(WebElement element,WebElement target) {
		a=new Actions(driver);
		a.dragAndDrop(element, target).perform();
	}
	/**
	 * This method is used to select an element from dropdown based on index
	 * @param element
	 * @param index
	 */
	public void dropDown(WebElement element, int index) {
		s=new Select(element);
		s.selectByIndex(index);
	}
	/**
	 * This method is used to select an element from dropdown based on value
	 * @param element
	 * @param value
	 */
	public void dropDown(WebElement element, String value) {
		s=new Select(element);
		s.selectByValue(value);
	}
	/**
	 * This method is used to select an element from dropdown based on visibleText
	 * @param text
	 * @param element
	 */
	public void dropDown(String text,WebElement element) {
		s=new Select(element);
		s.selectByVisibleText(text);;
	}
	/**
	 * This method is used to switch to frame based on index 
	 * @param index
	 */
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
	/**
	 * This method is used to switch to frame based on nameOrId
	 * @param nameOrId
	 */
	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}
	/**
	 * This method is used to switch to frame based on element
	 * @param element
	 */
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}
	/**
	 * This method is used to switch back from frame
	 */
	public void switchBackFromFrame() {
		driver.switchTo().defaultContent();
	}
	/**
	 * This method is used to scroll till element using element reference
	 * @param element
	 */
	public void scrollTillElement(WebElement element) {
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	/**
	 * This method is used to scroll using hardcode by giving x&y directions
	 */
	public void scrollUsinghardcode() {
		js=(JavascriptExecutor)driver;
		js.executeScript("Window.scrollBY(0,1000)");	
	}
	/**
	 * This method is used to takesscreenshot of webpage by using Takesscreenshot interface
	 * @param javaUtil
	 */
	public void takesScreenshot(JavaUtility javaUtil) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./Screenshot/ss"+javaUtil.getCurrentTime()+".png");
		try {
			FileUtils.copyDirectory(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * This method is used to handle alert popup by using Alert class 
	 * @param status
	 */
	public void handleAlert(String status) {
		Alert al=driver.switchTo().alert();
		if(status.equalsIgnoreCase("ok")) {
			al.accept();
		}
		else {
			al.dismiss();
		}
	}
	/**
	 * This method is used to switch to parent window by using getwindowhandle
	 */
	public void switchToParentWinddow() {
		driver.switchTo().window(driver.getWindowHandle());
	}
	/**
	 * This method is used to handle chilibrowser window by using getwindowhandles
	 */
	public void handleChildBrowser() {
		Set<String> set=driver.getWindowHandles();
		for(String wId:set) {
			driver.switchTo().window(wId);
		}
	}
	/**
	 * This method is used to close the current browser by using close method
	 */
	public void closeCurrentTab() {
		driver.close();
	}
	/**
	 * This method is used to quit the browser by using quit method
	 */
	public void quitBrowser() {
		driver.quit();
	}

}
