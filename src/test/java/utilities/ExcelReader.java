package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
    public static synchronized Map<String, String> getData(String sheetName, String scenarioName) {
        Map<String, String> dataMap = new HashMap<>();
        String filePath = "src/test/resources/testData/TestData.xlsx";
        DataFormatter formatter = new DataFormatter();
        try (Workbook workbook = WorkbookFactory.create(new File(filePath), null, true)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return dataMap;

            Row headerRow = sheet.getRow(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                if (currentRow == null) continue;

                String currentScenario = formatter.formatCellValue(currentRow.getCell(0)).trim();
                if (currentScenario.equalsIgnoreCase(scenarioName.trim())) {
                    for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                        String columnName = headerRow.getCell(j).getStringCellValue();
                        String cellValue = formatter.formatCellValue(currentRow.getCell(j));
                        dataMap.put(columnName, cellValue);
                    }
                    break; 
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading Excel at " + filePath + ": " + e.getMessage());
        }
        return dataMap;
    }

    public static synchronized Map<String, String> getPersonalDetailsData(String sheetName, String testcaseKey) {
        Map<String, String> dataMap = new HashMap<>();
        String filePath = "src/test/resources/testData/Onboarding_TestData.xlsx";
        DataFormatter formatter = new DataFormatter();

        try (Workbook workbook = WorkbookFactory.create(new File(filePath), null, true)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return dataMap;

            Row headerRow = sheet.getRow(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                if (currentRow == null) continue;

                String currentScenario = formatter.formatCellValue(currentRow.getCell(0)).trim();
                if (currentScenario.equalsIgnoreCase(testcaseKey.trim())) {
                    for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                        String columnName = headerRow.getCell(j).getStringCellValue();
                        String cellValue = formatter.formatCellValue(currentRow.getCell(j));
                        dataMap.put(columnName, cellValue);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading Onboarding Excel: " + e.getMessage());
        }
        return dataMap;
    }

	 

}

