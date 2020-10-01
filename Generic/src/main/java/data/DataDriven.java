package data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataDriven {

    public static XSSFWorkbook wb = null;
    public static XSSFSheet sheet = null;
    public static Cell cell = null;
    public static FileOutputStream fio = null;
    public static int numberOfRows, numberOfCol, rowNum;


    public void writeBack(String value) throws IOException {
        wb = new XSSFWorkbook();
        sheet = wb.createSheet();
        Row row = sheet.createRow(rowNum);
        row.setHeightInPoints(10);

        fio = new FileOutputStream(new File("ExcelFile.xls"));
        wb.write(fio);
        for (int i = 0; i < row.getLastCellNum(); i++) {
            row.createCell(i);
            cell.setCellValue(value);
        }
        fio.close();
        wb.close();
    }


    public void ExcelDataProvider(String XlsPath) {
        File src = new File(XlsPath);
        try {
            FileInputStream fis = new FileInputStream(src);
            wb = new XSSFWorkbook(fis);
        } catch (Exception e) {
            System.out.println("Unable to read Excel File" + e.getMessage());
        }
    }

    public String getStringData(String sheetName, int row, int column) {
        return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
    }

    public String getStringData(int sheetIndex, int row, int column) {
        return wb.getSheetAt(sheetIndex).getRow(row).getCell(column).getStringCellValue();
    }

    public double getNumericData(String sheetName, int row, int column) {
        return wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
    }

    public double getNumericData(int sheetIndex, int row, int column) {
        return wb.getSheetAt(sheetIndex).getRow(row).getCell(column).getNumericCellValue();
    }

    static Workbook book;
    static Sheet sheet1;

    // public static String path="C:/Users/ssbra/Desktop/New Microsoft Office Excel Worksheet.xlsx";
    public static String path = "";


}
