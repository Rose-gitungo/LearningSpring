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

public class SubjectiveQuestionExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public SubjectiveQuestionExcelExporter() {
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		 sheet = workbook.createSheet("Subjective Question");

	        // Create a title row
	        Row titleRow = sheet.createRow(0);
	        Cell titleCell = titleRow.createCell(0);
	        titleCell.setCellValue("Subjective Questions");

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

	        // Create a new row for the column headers
	        Row headerRow = sheet.createRow(1);

	        // Set the cell style for the header row
	        CellStyle headerStyle = workbook.createCellStyle();
	        XSSFFont headerFont = workbook.createFont();
	        headerFont.setBold(true);
	        headerStyle.setFont(headerFont);
	        headerStyle.setAlignment(HorizontalAlignment.CENTER);

	        // Create header cells with style
	        createCell(headerRow, 0, "Question Text", headerStyle);
	        createCell(headerRow, 1, "Correct Answer", headerStyle);
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