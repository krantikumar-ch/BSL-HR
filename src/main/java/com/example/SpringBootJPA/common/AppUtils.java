package com.example.SpringBootJPA.common;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AppUtils {

	@SuppressWarnings("rawtypes")
	public static void addExcelToResponse(List<Map<String,String>> columns,
			List entityList, String sheetName, String fileName,
			HttpServletResponse response
			) throws IOException{
		
		try(XSSFWorkbook workbook = new XSSFWorkbook()){
			
			XSSFSheet sheet = workbook.createSheet(sheetName);
			int rowNum=0;
			XSSFRow headers  = sheet.createRow(rowNum); 
			XSSFCellStyle style = workbook.createCellStyle();
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			
			
			XSSFFont font = workbook.createFont();
			font.setBold(true);
			style.setFont(font);     
			for(int i = 0;i<columns.size();i++){
				Map<String, String> column = columns.get(i);
				Cell cell = headers.createCell(i);
				cell.setCellStyle(style);
				
				cell.setCellValue(column.get("label"));
			}
		
			for(int i=0;i<entityList.size();i++){
				XSSFRow columnRow  = sheet.createRow(++rowNum); 
				Object entity = entityList.get(i);
				for(int j = 0;j<columns.size();j++){
					Map<String, String> column = columns.get(j);
					try{
						Field field = entity.getClass().getDeclaredField(column.get("name"));
						field.setAccessible(true);
						Object cellValue = field.get(entity);
						columnRow.createCell(j).setCellValue(cellValue != null ? cellValue.toString() : null);
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
			}		
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("file-name",  fileName+".xlsx");
			workbook.write(response.getOutputStream());
		}
		
	}
}
