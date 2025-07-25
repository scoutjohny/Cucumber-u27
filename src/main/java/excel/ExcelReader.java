package excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    public Map<String, String> getRowData (String fileName, String sheetName, int row) throws IOException {
        FileInputStream fis = new FileInputStream("src/test/testData/"+fileName+".xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        int lastColNum = sheet.getRow(1).getLastCellNum();
        Map<String, String> data = new HashMap<>();
        for(int i = 0; i<lastColNum;i++){
            String key = sheet.getRow(1).getCell(i).getStringCellValue();
            String value = sheet.getRow(row+1).getCell(i).getStringCellValue();
            data.put(key, value);
        }
        return data;
    }

    public Map<String, String> getRowDataByID (String fileName, String sheetName, String tc_id) throws IOException {
        FileInputStream fis = new FileInputStream("src/test/testData/"+fileName+".xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        int lastColNum = sheet.getRow(1).getLastCellNum();
        int lastRowNum = sheet.getLastRowNum();
        Map<String, String> data = new HashMap<>();
        for(int i = 0;i<=lastRowNum;i++){
            if(sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(tc_id)){
                for(int j = 0; j<lastColNum;j++){
                    String key = sheet.getRow(1).getCell(j).getStringCellValue();
                    String value = sheet.getRow(i).getCell(j).getStringCellValue();
                    data.put(key, value);
                }
            }
        }
        return data;
    }
}
