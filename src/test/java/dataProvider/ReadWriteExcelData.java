package dataProvider;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadWriteExcelData {
    FileInputStream fileInputStream;
    XSSFWorkbook xssfWorkbook;
    XSSFSheet xssfSheet;

    public ReadWriteExcelData() throws IOException {
        fileInputStream=new FileInputStream(new File(ConfigFileReader.Testdata()));
        xssfWorkbook = new XSSFWorkbook(fileInputStream);
    }
    public XSSFSheet Sheet(String sheetName)
    {
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        return xssfSheet;
    }
}
