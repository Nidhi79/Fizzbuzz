
package Fizzbuzz;

import java.io.File;
import java.io.IOException;

import org.apache.poi.hssf.record.formula.functions.Files;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


@Test
public class Fizzbuzz_APP 
{
	
	
public void f1() throws Exception, IOException
{

	
	String currentUser = System.getProperty("user.name");
	System.out.println("Current user is " + currentUser);
	
	
	
	//Opening the chrome browser and navigating to the url
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\"+currentUser+"\\Downloads\\Fizzbuzz\\drivers\\chromedriver.exe"); 
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://fizzbuzz-exercise.herokuapp.com/fizzbuzz-calculator");
	Thread.sleep(4000);
	
	
	
	
	//Stored all the inputs and outputs in the Excel below
	Workbook workbook1 = Workbook.getWorkbook(new File("C:\\Users\\"+currentUser+"\\Downloads\\Fizzbuzz\\input_output_file.xls"));
	Sheet Data = workbook1.getSheet(0);
	int rows= Data.getRows();
	
	for(int i =1; i<+rows ; i++)
	{
		//iterating through the rows and reading the inputs
		jxl.Cell input=Data.getCell(0,i);
		String String_input = input.getContents().toString();
		System.out.println("The input for the calculator is "+String_input+"");
		
		//iterating through the rows and reading the outputs
		jxl.Cell output2=Data.getCell(1,i);
		String String_output = output2.getContents().toString();
		System.out.println("The output for the calculator is "+String_output+"");
		
		//sending the input to the application online
		driver.findElement(By.id("values")).sendKeys(String_input);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"CalcualteForm\"]/div[2]/button")).click();
		Thread.sleep(3000);
		
		//checking the output and storing in rowtext 
		WebElement baseTable = driver.findElement(By.tagName("table"));
		WebElement tableRow = baseTable.findElement(By.xpath("/html/body/div/main/div/table/tbody/tr/td[2]"));
		String rowtext=tableRow.getText();
		
	
			
		//Validating that the expected output matches with the actual one
		Assert.assertEquals(String_output, rowtext);
		
		//clicking on the back button
		driver.findElement(By.xpath("html/body/div/main/div/div/a")).click();
		Thread.sleep(3000);
		
		
	}
	//closing the browser
	     driver.close();
}


}