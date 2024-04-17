package com.csmtech.exporter;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ObjectiveQuestionExcelExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public ObjectiveQuestionExcelExporter() {

		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		
		sheet = workbook.createSheet("Objective Question");

        // Create a title row
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Objective Questions");

        // Merge cells for the title row
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));

        // Create a cell style for the title
        CellStyle titleStyle = workbook.createCellStyle();
        XSSFFont titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);

        // Apply the style to the title cell
        titleCell.setCellStyle(titleStyle);
		

		
		Row row = sheet.createRow(1);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		 style.setFont(font);
	     style.setAlignment(HorizontalAlignment.CENTER);
		createCell(row, 0, "Question Text", style);
		createCell(row, 1, "Option 1", style);
		createCell(row, 2, "Option 2", style);
		createCell(row, 3, "Option 3", style);
		createCell(row, 4, "Option 4", style);
		createCell(row, 5, "Option 5", style);
		createCell(row, 6, "Correct Answer 1", style);
		createCell(row, 7, "Correct Answer 2", style);
		createCell(row, 8, "Correct Answer 3", style);
		createCell(row, 9, "Correct Answer 4", style);
		createCell(row, 10, "Correct Answer 5", style);
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines() {

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}

}