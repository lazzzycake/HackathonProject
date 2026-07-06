package Utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    private final String filePath;

    // Constructor
    public ExcelUtility(String filePath) {
        this.filePath = filePath;
    }

    // ---------- Helper: open OR create workbook ----------
    private Workbook getWorkbook() throws IOException {
        File file = new File(filePath);

        if (file.exists()) {
            // File exists → open it
            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fis);
            fis.close();
            return workbook;
        } else {
            // File doesn't exist → make sure parent folder exists, then create empty workbook
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();   // create TestData/ folder if missing
            }
            return new XSSFWorkbook();
        }
    }

    // ---------- Helper: get OR create sheet ----------
    private Sheet getSheet(Workbook workbook, String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) sheet = workbook.createSheet(sheetName);
        return sheet;
    }

    // ---------- Helper: save workbook ----------
    private void saveWorkbook(Workbook workbook) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        workbook.close();
        fos.close();
    }

    // ---------- WRITE a single cell ----------
    public void writeCell(String sheetName, int rowNum, int colNum, String value) throws IOException {
        Workbook workbook = getWorkbook();
        Sheet sheet = getSheet(workbook, sheetName);

        Row row = sheet.getRow(rowNum);
        if (row == null) row = sheet.createRow(rowNum);

        Cell cell = row.getCell(colNum);
        if (cell == null) cell = row.createCell(colNum);

        cell.setCellValue(value);

        saveWorkbook(workbook);
    }

    // ---------- READ a single cell ----------
    public String readCell(String sheetName, int rowNum, int colNum) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) throw new IOException("File not found: " + filePath);

        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            workbook.close();
            fis.close();
            throw new IOException("Sheet not found: " + sheetName);
        }

        String value = "";
        Row row = sheet.getRow(rowNum);
        if (row != null && row.getCell(colNum) != null) {
            value = new DataFormatter().formatCellValue(row.getCell(colNum));
        }

        workbook.close();
        fis.close();
        return value;
    }

    // ---------- Get row count ----------
    public int getRowCount(String sheetName) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) return 0;

        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        int rows = (sheet == null) ? 0 : sheet.getLastRowNum() + 1;

        workbook.close();
        fis.close();
        return rows;
    }
}