package com.pljay.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils
{
	private static Logger logger = Logger.getLogger(ExcelUtils.class);
	private static Workbook wb;
	private static Sheet sheet;
	private static Row row;

	public static Workbook ReadExcelUtils(String filepath)
	{
		if (filepath == null) {
			return wb;
		}
		String ext = filepath.substring(filepath.lastIndexOf("."));
		try
		{
			InputStream is = new FileInputStream(filepath);
			if (".xls".equals(ext)) {
				wb = new HSSFWorkbook(is);
			} else if (".xlsx".equals(ext)) {
				wb = new XSSFWorkbook(is);
			} else {
				wb = null;
			}
			is.close();
		}
		catch (FileNotFoundException e)
		{
			logger.error("FileNotFoundException", e);
		}
		catch (IOException e)
		{
			logger.error("IOException", e);
		}
		return wb;
	}

	public String[] readExcelTitle(Workbook wb)
			throws Exception
	{
		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);

		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			title[i] = row.getCell(i).getStringCellValue();
		}
		return title;
	}

	private static Object getCellFormatValue(Cell cell)
	{
		Object cellvalue = "";
		if (cell != null) {
			switch (cell.getCellType())
			{
			case 0: 
			case 2: 
				if (DateUtil.isCellDateFormatted(cell))
				{
					Date date = cell.getDateCellValue();
					cellvalue = date;
				}
				else
				{
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			case 1: 
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default: 
				cellvalue = "";

				break;
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

	public static Map<Integer, Map<Integer, Object>> readExcelContent(Workbook wb, int s)
			throws Exception
	{
		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}
		Map<Integer, Map<Integer, Object>> content = new HashMap();

		sheet = wb.getSheetAt(s);

		int rowNum = sheet.getLastRowNum();

		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		for (int i = 1; i <= rowNum; i++)
		{
			row = sheet.getRow(i);
			int j = 0;
			Map<Integer, Object> cellValue = new HashMap();
			while (j < colNum)
			{
				Object obj = getCellFormatValue(row.getCell(j));
				cellValue.put(Integer.valueOf(j), obj);
				j++;
			}
			content.put(Integer.valueOf(i), cellValue);
		}
		return content;
	}
}


