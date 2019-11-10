package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

import net.sf.json.JSONArray;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class Test {
	LinkedBlockingQueue<Integer>  quque = new LinkedBlockingQueue<Integer>();
	
	public Test(){
		  //readRes("C:\\Users\\Administrator\\Desktop\\signin.xls", 0);
	}

	public static void main(String[] args) throws InterruptedException {
		//new Test();
		
		Calendar d = Calendar.getInstance();
		d.set(2016, 5 ,12, 16, 15);
		System.out.println(d.getTimeInMillis());
		System.out.println(System.currentTimeMillis());
		
		
		
		
	}
	
	
//	public  void readRes(String file, int page){
//		Workbook readwb = null;
//			// 构建Workbook对象
//			InputStream instream = null;
//			try {
//				instream = new FileInputStream(file);
//				readwb = Workbook.getWorkbook(instream);
//			} catch (Exception e1) {
//			}
//			
//			Sheet readsheet = readwb.getSheet(page);
//			int rsColumns = readsheet.getColumns();
//			int rsRows = readsheet.getRows();
//			
//			for (int i = 3; i < rsRows; i++)
//			{
//				for (int j = 0; j < rsColumns; j++)
//				{
//					Cell cell = readsheet.getCell(j, i);
//					System.out.println(cell.getContents());
//				}
//			}
//	}
	 public static ArrayList<String[]> readRes(File excelFile) throws BiffException, IOException {
	        ArrayList<String[]> list = new ArrayList<String[]>();
	        Workbook rwb = null;
	        Cell cell = null;
	        InputStream stream = new FileInputStream(excelFile);
	        rwb = Workbook.getWorkbook(stream);   // 获取Excel文件对象
	        Sheet sheet = rwb.getSheet(0);     // 获取文件的指定工作表 默认的第一个
	        for (int i = 3; i < sheet.getRows(); i++) {  // 行数(表头的目录不需要，从1开始)
	            String[] str = new String[sheet.getColumns()];  // 创建一个数组 用来存储每一列的值
	            for (int j = 0; j < sheet.getColumns(); j++) {  // 列数
	                cell = sheet.getCell(j, i);
	                str[j] = cell.getContents(); //当前i行、当前j列对应的单元格内容，赋值给当前一维数组的当前j数据元素
	            }
	            list.add(str);
	        }
	        return list;
	    }
	
}
