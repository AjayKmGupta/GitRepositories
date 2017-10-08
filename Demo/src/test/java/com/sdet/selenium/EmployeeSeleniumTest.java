package com.sdet.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class EmployeeSeleniumTest {
	
	private static WebDriver driver;
	
	@BeforeClass
	public static void setupDriver() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void clickOnViewAllButton() {
		driver.get("http://localhost:8085/Demo/");
		WebElement el = driver.findElement(By.xpath("//*[contains(text(),'View All')]"));
		el.click();
		Assert.assertTrue(driver.findElement(By.cssSelector("[class*='text-success']")).isDisplayed());
	}

	@AfterClass
	public static void destroyDriver() {
		driver.close();
	}
	
}
