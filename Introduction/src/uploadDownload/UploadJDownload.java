package uploadDownload;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UploadJDownload {

	// Declaring common variables
	static int colCount, rowCount, fruit_name_col, price_col;
	static XSSFWorkbook wb;
	static String filePath = "C:\\Users\\dell\\Downloads\\download.xlsx";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Driver Initialization
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		driver.manage().window().maximize();

		// Value to be searched and updated initialized
		String fruit = "Banana";
		int price = 200;

		// download file
		WebElement download = driver.findElement(By.id("downloadButton"));
		download.click();

		// Get the sheet read access to be used
		XSSFSheet sheet = getExcelAccess();

		// get the row no of the row to be updated
		int updateRowNo = updateRowNo(sheet, fruit);

		// Edit Excel
		Assert.assertTrue(updateExcel(sheet, updateRowNo, price_col, price));

		// upload
		WebElement upload = driver.findElement(By.id("fileinput"));
		upload.sendKeys(filePath);

		// wait for success message to appear and disappear
		WebElement alertPopup = driver.findElement(By.xpath("//div[@role='alert'] //div[2]"));

		// Wait for message visibility
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Alert Text :: " + w.until(ExpectedConditions.visibilityOf(alertPopup)).getText());

		// Wait for message to disappear
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert'] //div[2]")));

		// verify updated excel data showing in the web table

		// Fetch data value from web table
		String fruitName = driver.findElement(By.xpath("//div[contains(text(),'" + fruit + "')]")).getText();
		String priceColumnNo = driver.findElement(By.xpath("//div[contains(text(),'Price')]"))
				.getAttribute("data-column-id");
		String fruitPrice = driver.findElement(By.xpath(
				"//div[contains(text(),'" + fruit + "')]/parent::div/parent::div/div[" + priceColumnNo + "]/div"))
				.getText();

		// Compare with excel data
		for (int i = 1; i < rowCount; i++) {
			while (sheet.getRow(i).getCell(fruit_name_col).getStringCellValue().equalsIgnoreCase(fruitName)) {
				System.out.println("Fruit Name :: " + fruitName + " Fruit Price :: " + fruitPrice);
				Assert.assertTrue(NumberToTextConverter.toText(sheet.getRow(i).getCell(price_col).getNumericCellValue())
						.equalsIgnoreCase(fruitPrice));
				driver.quit();
				break;
			}

		}

	}

	private static XSSFSheet getExcelAccess() throws IOException {
		FileInputStream fis = new FileInputStream(filePath);

		wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		rowCount = sheet.getPhysicalNumberOfRows();

		// Read header and find price column and fruit name column
		XSSFRow rowHeader = sheet.getRow(0);
		colCount = rowHeader.getLastCellNum();
		fruit_name_col = 0;
		price_col = 0;
		Iterator<Cell> cellIterator = rowHeader.iterator();
		int currentCellNo = 0;

		// Iterate through each cell of row header to find the column nos of the req
		// columns.
		while (cellIterator.hasNext()) {
			Cell cellValue = cellIterator.next();
			if (cellValue.getStringCellValue().equalsIgnoreCase("fruit_name")) {
				fruit_name_col = currentCellNo;
			} else if (cellValue.getStringCellValue().equalsIgnoreCase("price")) {
				price_col = currentCellNo;
			}
			currentCellNo++;
		}

		return sheet;

	}

	private static boolean updateExcel(XSSFSheet sheet, int rowNo, int colNo, int updateValue) throws IOException {
		Cell updateCell = sheet.getRow(rowNo).getCell(colNo);
		updateCell.setCellValue(updateValue);

		FileOutputStream fos = new FileOutputStream(filePath);
		wb.write(fos);
		wb.close();
		return true;

	}

	private static int updateRowNo(XSSFSheet sheet, String fruit) {
		// TODO Auto-generated method stub
		int i = 1;
		while (!sheet.getRow(i).getCell(fruit_name_col).getStringCellValue().equalsIgnoreCase(fruit)) {
			i++;
		}

		return i;
	}

}
