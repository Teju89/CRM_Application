package com.crm.generic.fileUtitlity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtitlity {
	
	public String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws Exception {
		FileInputStream stream = new FileInputStream(new File("./TestData/Data.xlsx"));
		Workbook workbook = WorkbookFactory.create(stream);
		Sheet sheet = workbook.getSheet(sheetName);
		return sheet.getRow(rowNum).getCell(cellNum).toString();
	}
	
	public int getRowCount(String sheetName) throws Exception {
		FileInputStream stream = new FileInputStream(new File("./TestData/Data.xlsx"));
		Workbook workbook = WorkbookFactory.create(stream);
		Sheet sheet = workbook.getSheet(sheetName);
		return sheet.getPhysicalNumberOfRows();
	}
	
	public void setDataIntoExcel(String sheetName, int rowNum, int colNum, String data) throws Exception {
		FileInputStream stream = new FileInputStream(new File("./TestData/Data.xlsx"));
		Workbook workbook = WorkbookFactory.create(stream);
		Sheet sheet = workbook.getSheet(sheetName);
		sheet.getRow(rowNum).createCell(colNum).setCellValue(data);
		
		FileOutputStream fos = new FileOutputStream(new File("./TestData/Data.xlsx"));
		workbook.write(fos);
		workbook.close();
	}
}











