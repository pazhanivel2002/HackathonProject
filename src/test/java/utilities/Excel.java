package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	public void excel(String data,int row,int col,String Sheet) throws IOException {
		FileInputStream fi=new FileInputStream(System.getProperty("user.dir")+"\\testdata\\DataFile.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		if(wb.getSheetIndex(Sheet)==-1) {
			wb.createSheet(Sheet);
		}
		XSSFSheet sheet=wb.getSheet(Sheet);
		if(sheet.getRow(row)==null) {
			sheet.createRow(row);
		}
		XSSFRow ro=sheet.getRow(row);
		XSSFCell cell=ro.createCell(col);
		cell.setCellValue(data);
		FileOutputStream fo=new FileOutputStream(System.getProperty("user.dir")+"\\testData\\DataFile.xlsx");
		wb.write(fo);
		wb.close();
		fi.close();
		fo.flush();
		fo.close();
	}

}
