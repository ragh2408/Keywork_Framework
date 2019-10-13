package com.ils.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;

import com.ils.genericmethods.Generic_Methods;

public class Driver extends Generic_Methods {

	static String FCA = "https://cie-dev.my.af.mil/CIEDEV-ESS/ess/";
	static String FCB = "https://www.geeksforgeeks.org";
	static String FCD = "https://www.programiz.com";
	static String fn_env = null;
	static String Browsername = "CH";

	public Driver() throws IOException {
		super();

	}
	public static void main(String[] args) throws InvalidFormatException, IOException, InterruptedException {

		String current_path = System.getProperty("user.dir");
		File Excel_File = new File(current_path + "\\ILS.xlsx");
		FileInputStream fis = new FileInputStream(Excel_File);
		Workbook excelbook = WorkbookFactory.create(fis);
		Sheet sheet = excelbook.getSheet("Sheet1");
		int lst_row = sheet.getLastRowNum();
		int lst_Cell = sheet.getRow(0).getLastCellNum();
		for (int rw = 0; rw <= lst_row; rw++) {
			for (int cl = 0; cl < lst_Cell; cl++) {
				if (sheet.getRow(rw).getCell(cl) == null) {
					sheet.getRow(rw).createCell(cl);
				}
				sheet.getRow(rw).getCell(cl).setCellType(1);
			}
		}

		for (int i = 0; i <= lst_row; i++) {
			String flag = sheet.getRow(i).getCell(1).getStringCellValue();
			if (flag.equalsIgnoreCase("Y")) {

				String Action = sheet.getRow(i).getCell(2).getStringCellValue();
				String Locator = sheet.getRow(i).getCell(3).getStringCellValue();
				String Value = sheet.getRow(i).getCell(4).getStringCellValue();
				

				System.out.println("Action---" + Action);

				switch (Action) {

				case "Browser_Name":
					driver(Value);
					break;
				case "url":
					url(env(Value));
					break;
				case "inputvalue":

					getelement(Locator).sendKeys(Value);
					break;
				case "click":
					getelement(Locator).click();
					Thread.sleep(1000);
					break;
				case "submit":
					getelement(Locator).submit();
					Thread.sleep(1000);
					break;
				case "close":
					driver.quit();
					break;
				case "Alert":
					Thread.sleep(1000);
					Alert AR = driver.switchTo().alert();
					AR.accept();
					break;
				case "get":
					String getstr = getelement(Locator).getText();
					sheet.getRow(i).getCell(3).setCellValue(getstr);
					System.out.println("Get Text value is -" + getstr);
					break;
				default:
					System.out.println("Invalid Action!");
					break;

				}

				System.out.println(">>>>>>>>>>>>>>>>>>>>> Line number -> " + i + " is completed");

			} else {
				//System.out.println("Line number not executed - " + i);
			}
		}
		
		System.out.println("................................. TEST CASE END .......................................");

	}

	public static String env(String val) {
		if (val.equalsIgnoreCase("FCA")) {
			fn_env = FCA;
		} else if (val.equalsIgnoreCase("FCB")) {
			fn_env = FCB;
		} else if (val.equalsIgnoreCase("FCD")) {
			fn_env = FCD;
		}
		return fn_env;
	}

}
