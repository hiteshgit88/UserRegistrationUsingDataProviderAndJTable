package Util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelHelper {

    private int intRowNum;
    private int intColNum;

    private Object[][] arrExcelData;

    private Workbook objWorkBook;
    private Sheet objWorkSheet;
    private Row objRow;
    private Cell objCell;

    private synchronized Sheet getExcelWorkBook(String strFilePath, String strSheetName) {

        try (FileInputStream objFile = new FileInputStream(strFilePath)) {

            objWorkBook = null;
            objWorkSheet = null;

            if (strFilePath.lastIndexOf(".") != -1) {
                if (strFilePath.substring(strFilePath.lastIndexOf(".")).equals(".xls")) {
                    objWorkBook = new HSSFWorkbook(objFile);
                } else if (strFilePath.substring(strFilePath.lastIndexOf(".")).equals(".xlsx")) {
                    objWorkBook = new XSSFWorkbook(objFile);
                }
            }
            if (objWorkBook != null) {

                objWorkSheet = objWorkBook.getSheet(strSheetName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return objWorkSheet;
    }

    public Object[][] readDataFromExcel(String strFilePath, String strSheetName){
        try{

            objWorkSheet = getExcelWorkBook(strFilePath, strSheetName);

            if(objWorkSheet != null){
                objRow = objWorkSheet.getRow(0);

                intRowNum = objWorkSheet.getPhysicalNumberOfRows() - 1;
                intColNum = objRow.getLastCellNum();

                arrExcelData = new Object[intRowNum][intColNum];

                for (int rowCnt = 0; rowCnt < intRowNum; rowCnt++){
                    objRow = objWorkSheet.getRow(rowCnt +1);

                    for (int colCnt = 0; colCnt < intRowNum; colCnt++){
                        if (objRow == null){
                            arrExcelData[rowCnt][colCnt] = "";
                        }else {
                            objCell = objRow.getCell(colCnt);
                            objCell.setCellType(CellType.STRING);

                            if (objCell == null){
                                arrExcelData[rowCnt][colCnt] = "";
                            }else {
                                arrExcelData[rowCnt][colCnt] = objCell.getStringCellValue();
                            }
                        }
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return arrExcelData;
    }
}