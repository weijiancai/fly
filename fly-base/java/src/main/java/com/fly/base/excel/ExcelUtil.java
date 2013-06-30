package com.fly.base.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class ExcelUtil {
    public static void main(String[] args) throws IOException {
        Workbook workbook = new HSSFWorkbook(new FileInputStream("C:\\Users\\wei_jc\\Desktop\\excel\\苏宁同方节能补贴7568份.xls"));
        Workbook workbook1 = new HSSFWorkbook(new FileInputStream("C:\\Users\\wei_jc\\Desktop\\excel\\苏宁同方节能补贴7442份.xls"));
        Sheet sheet = workbook.getSheetAt(0);
        List<String> list = new ArrayList<String>();
        for (Row row : sheet) {
            Cell cell = row.getCell(3);
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
            }

            list.add(cell.getStringCellValue());
        }

        List<String> result = new ArrayList<String>();

        for (Row row : workbook1.getSheetAt(0)) {
            Cell cell = row.getCell(3);
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
            }
            String value = cell.getStringCellValue();
            if (!list.contains(value)) {
                result.add(value);
            }
        }
        for (String str : result) {
            System.out.println(str);
        }
    }
}
