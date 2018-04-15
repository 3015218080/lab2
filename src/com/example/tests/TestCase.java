package com.example.tests;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


import org.apache.poi.ss.usermodel.*;  
import java.io.*;


public class TestCase {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    

  }

  @Test
  public void test() throws Exception {
	  
	  File file = new File("input1.xlsx");  
      InputStream inputStream = null;  
      Workbook workbook = null;  
      try {  
          inputStream = new FileInputStream(file);  
          workbook = WorkbookFactory.create(inputStream);  
          inputStream.close();  
          //工作表对象  
          Sheet sheet = workbook.getSheetAt(0);  
          //总行数  
          int rowLength = sheet.getLastRowNum()+1;  
          //工作表的列  
          Row row = sheet.getRow(0);  
          //总列数  
          int colLength = row.getLastCellNum();  
          //得到指定的单元格  
          Cell cell1 = row.getCell(0);  
          Cell cell2 = row.getCell(1);  

          String a="",b="",c="";
          for (int i = 0; i < rowLength; i++) {  
              row = sheet.getRow(i);    
              cell1 = row.getCell(0);  
              cell2 = row.getCell(1); 
              
              if (cell2 != null) {
            	  if (cell1.getCellType()==Cell.CELL_TYPE_NUMERIC) {  
	            	  BigDecimal bd = new BigDecimal(cell1.getNumericCellValue());
	            	  a=bd.toPlainString();
	            	  b=a.substring(4);
	            	  c=cell2.getStringCellValue();
	            	  if(c.length()>0)
	                	  read(a,b,c);
            	  }else if(cell1.getCellType()==Cell.CELL_TYPE_STRING) {
            		  a=cell1.getStringCellValue();
            		  b=a.substring(4);
            		  c=cell2.getStringCellValue().trim();
            		  if(c.length()>0)
                    	  read(a,b,c);
            	  }
            	  //System.out.println(c);
            	  
              }
          } 
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
	  

  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  public void read(String username,String pwd,String addr) {  
	    driver.get("https://psych.liebes.top/st");
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys(username);
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys(pwd);
	    driver.findElement(By.id("submitButton")).click();
	    String address = driver.findElement(By.xpath("//p")).getText().trim();
	    if(address.charAt(address.length()-1)=='/') {
	    	address = address.substring(0, address.length()-1);
	    }
	    if(addr.charAt(addr.length()-1)=='/') {
	    	addr = addr.substring(0, addr.length()-1);
	    }
	    if(!address.equals(addr))
	    System.out.println("sid: "+username+" pwd: "+pwd);
  }
  
}
