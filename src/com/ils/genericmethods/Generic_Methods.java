package com.ils.genericmethods;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class Generic_Methods  {

	public static WebDriver driver;


	public static WebElement getelement(String elementname) throws IOException {

				String[] arr = elementname.split("#");
		WebElement we = null;
		if (arr[0].equalsIgnoreCase("name") == true) {
			we = driver.findElement(By.name(arr[1]));

		} else if (arr[0].equalsIgnoreCase("linklist")) {

			we = driver.findElement(By.linkText(arr[1]));
		} else if (arr[0].equalsIgnoreCase("xpath")) {
			System.out.println("Xpath Matched...");
			we = driver.findElement(By.xpath(arr[1]));
		}

		return we;
	}


	public static void click(String elementname) throws IOException {
		WebElement we = getelement(elementname);
		we.click();
	}



	public static void openapp(String brname, String url) {

		if (brname.equalsIgnoreCase("FF") == true) {
			driver = new FirefoxDriver();

		} else if (brname.equalsIgnoreCase("CH") == true) {

			System.setProperty("webdriver.chrome.driver",
					"C:\\ProgramData\\Chrome_driver_76.0.3809.68\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (brname.equalsIgnoreCase("IE") == true) {
			System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public static WebDriver driver(String brname) {

		if (brname.equalsIgnoreCase("firefox") == true) {
			driver = new FirefoxDriver();

		} else if (brname.equalsIgnoreCase("Chrome") == true) {

			System.setProperty("webdriver.chrome.driver",
					"C:\\ProgramData\\Chrome_driver_76.0.3809.68\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (brname.equalsIgnoreCase("IE") == true) {
			System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		return driver;		
	}

	public static void url(String url) {
		
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
		
	}
	

}
