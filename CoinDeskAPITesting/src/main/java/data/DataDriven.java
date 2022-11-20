package data;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class DataDriven {

    public static Object[][] expectedData() throws IOException, InvalidFormatException {
        File file = new File("src\\main\\resources\\Data\\ipinfo.xlsx");
        //FileInputStream fi = new FileInputStream(file);
        XSSFWorkbook xwb = new XSSFWorkbook(file);
        XSSFSheet sheet = xwb.getSheet("Sheet1");
        int rows = sheet.getPhysicalNumberOfRows();

        int cols = sheet.getRow(0).getPhysicalNumberOfCells();
        Object[][] apiDataTest = new Object[rows-1][cols];
        for(int i=1;i<rows;i++){
            for(int j=0;j<cols;j++){
                apiDataTest[i-1][j]= sheet.getRow(i).getCell(j).toString();
            }
        }
        xwb.close();
        return apiDataTest;
    }
}
