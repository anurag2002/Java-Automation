import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	// POI API Strategy
	// Identity TestCases column by scanning the entire 1st row
	// once the column is identified, then scan the entire column to identify
	// purchase testcase row
	// after you grab purchase testcase row, pull all the data of the row and feed
	// it to test

	public ArrayList<String> getData(String testCaseName) throws IOException {
		// fileInputStream argument
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis = new FileInputStream("G:\\SELF\\Java Automation\\Selenium-Automation\\Book1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis); // make the xl file accessible

		int sheetsCount = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetsCount; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) { // check the sheet name
				XSSFSheet sheet = workbook.getSheetAt(i); // access the particular sheet
				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next(); // firstrow
				Iterator<Cell> cells = firstRow.cellIterator();
				int k = 0, column = 0;
				while (cells.hasNext()) {
					Cell value = cells.next();
					if (value.getStringCellValue().equalsIgnoreCase("TestCases")) { // testcases column searched
						column = k; // column number stored
						break;
					}
					k++;

				}
				System.out.println("column no: " + column);

				// once column is identified, scan entrire testcase row to identify purchase
				// testcase row
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						// after you grab purchase testcase row, pull all the data of the row and feed
						// it to test
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellType() == CellType.STRING) {
								a.add(c.getStringCellValue());
							} else {

								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
					}
				}
			}
		}
		return a;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

	}

}
