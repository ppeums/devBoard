package com.devBoard.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.devBoard.framework.collection.MultiValueMap;
import com.devBoard.framework.exception.ExRuntimeException;

/**  
 * @Class Name : ExcelUtil.java
 * @Description : 엑셀 파일 업로드 및 다운로드 시 필요한 기능을 제공한다.
 * @Modification Information
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012. 12. 11.  송제승     최초생성
 * 
 * @author 송제승
 * @since 2012. 12. 11.
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
public class ExcelUtil {

	// private final Logger logger = Logger.getLogger(this.getClass());
	
    private String          filePath = null;
    private HSSFWorkbook    workbook = null;
    private HSSFSheet       sheet    = null;
    private POIFSFileSystem fs       = null;
    private FileInputStream fis      = null;

    /**
     * <PRE>
     * Default 생성자
     * </PRE>
     */
    public ExcelUtil() {
    }

    /**
     * <PRE>
     * 엑셀 파일 경로를 설정하는 생성자
     * </PRE>
     * 
     * @param filePath 파일 경로
     */
    public ExcelUtil(String filePath) {
        this.filePath = filePath;
    }

    /**
     * <PRE>
     * 엑셀 파일을 오픈한다.
     * </PRE>
     */
    public void open() {
        try {
            fis = new FileInputStream(filePath);
            fs = new POIFSFileSystem(fis);

            // 워크북 생성
            workbook = new HSSFWorkbook(fs);
            // 시트 생성
            sheet = workbook.getSheetAt(0);
        } catch (Exception e) {
            throw new ExRuntimeException(e);
        }
    }
    
    /**
     * 엑셀 파일을 생성한다.
     */
    public void save(){
    	FileOutputStream fileOut =  null;
    	
    	try{
	    	fileOut = new FileOutputStream(filePath);
	    	workbook.write(fileOut);
	    } catch (Exception e) {
    		throw new ExRuntimeException(e);
        } finally{
        	if(fileOut != null){
        		try{ fileOut.close(); } catch(Exception e) { throw new ExRuntimeException(e); }
        	}
        }
    }

    /**
     * <PRE>
     * 엑셀 파일을 닫는다.
     * </PRE>
     */
    public void close() {
        try {
            if (fis != null) fis.close();
        } catch (IOException ie) {
        	throw new ExRuntimeException(ie);
        }
    }

    /**
     * <PRE>
     * 데이터가 존재하는 Row 수를 반환한다.
     * </PRE>
     * 
     * @return 데이터가 존재하는 Row 수
     */
    public int getPhysicalNumberOfRows() {
        int endRowIndex = sheet.getPhysicalNumberOfRows();

        return endRowIndex;
    }

    /**
     * <PRE>
     * Row, Column에 해당하는 셀 값을 반환한다.
     * </PRE>
     * 
     * @param rowIndex Row Index
     * @param columnIndex Column Index
     * @return 셀 값
     */
    public String getColumn(int rowIndex, int columnIndex) {
        String value = null;

        try {
            HSSFRow row = sheet.getRow(rowIndex);

            if (row != null) {
                HSSFCell cell = row.getCell(columnIndex);

                if (cell != null) {
                    if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                        if (cell.toString().indexOf(".") > -1) {
                            if ("0.0".equals(cell.toString())) {
                                value = "0";
                            } else {
                                value = new BigDecimal(cell.toString()).setScale(7, BigDecimal.ROUND_DOWN).toString();
                                value = removeTrailZero(value);
                            }
                        } else {
                            value = new BigDecimal(cell.getNumericCellValue()).toString();
                        }
                    } else {
                        value = cell.toString();
                    }
                }
            }

            return value;
        } catch (Exception e) {
            throw new ExRuntimeException(e);
        }
    }
    
    
    /**
     * <PRE>
     * Row, Column에 해당하는 셀 값을 저장한다..
     * </PRE>
     * 
     * @param rowIndex Row Index
     * @param columnIndex Column Index
     * @return 
     */
    public void setColumn(int rowIndex, int columnIndex, String val) {
    	try {
    		HSSFRow row = sheet.getRow(rowIndex);
    		
    		if(row == null) row = sheet.createRow(rowIndex);
    		
			HSSFCell cell = row.getCell(columnIndex);
			
			if(cell == null) cell = row.createCell(columnIndex);
			
			cell.setCellValue(new HSSFRichTextString(val));
    	} catch (Exception e) {
    		throw new ExRuntimeException(e);
    	}
    }

    /**
     * <PRE>
     * Default Head Cell Style 생성
     * </PRE>
     * 
     * @param workbook Cell Style 생성 대상이 되는 HSSFWorkbook
     * @return Default Head Cell Style
     */
    public HSSFCellStyle createDefaultHeadCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle headStyle = workbook.createCellStyle();
        headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headStyle.setRightBorderColor(HSSFColor.BLACK.index);
        headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headStyle.setTopBorderColor(HSSFColor.BLACK.index);
        headStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return headStyle;
    }

    /**
     * <PRE>
     * Default Data Cell Style 생성
     * </PRE>
     * 
     * @param workbook Cell Style 생성 대상이 되는 HSSFWorkbook
     * @return Default Data Cell Style
     */
    public HSSFCellStyle createDefaultDataCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        dataStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        dataStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        dataStyle.setRightBorderColor(HSSFColor.BLACK.index);
        dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        dataStyle.setTopBorderColor(HSSFColor.BLACK.index);

        return dataStyle;
    }

    /**
     * <PRE>
     * Right-Align Data Cell Style 생성
     * </PRE>
     * 
     * @param workbook Cell Style 생성 대상이 되는 HSSFWorkbook
     * @return Right-Align Data Cell Style
     */
    public HSSFCellStyle createRightAlignDataCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle rightAlignDataStyle = workbook.createCellStyle();
        rightAlignDataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        rightAlignDataStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        rightAlignDataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        rightAlignDataStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        rightAlignDataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        rightAlignDataStyle.setRightBorderColor(HSSFColor.BLACK.index);
        rightAlignDataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        rightAlignDataStyle.setTopBorderColor(HSSFColor.BLACK.index);
        rightAlignDataStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

        return rightAlignDataStyle;
    }

    /**
     * <PRE>
     * Center-Align Data Cell Style 생성
     * </PRE>
     * 
     * @param workbook Cell Style 생성 대상이 되는 HSSFWorkbook
     * @return Center-Align Data Cell Style
     */
    public HSSFCellStyle createCenterAlignDataCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle centerAlignDataStyle = workbook.createCellStyle();
        centerAlignDataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        centerAlignDataStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        centerAlignDataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        centerAlignDataStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        centerAlignDataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        centerAlignDataStyle.setRightBorderColor(HSSFColor.BLACK.index);
        centerAlignDataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        centerAlignDataStyle.setTopBorderColor(HSSFColor.BLACK.index);
        centerAlignDataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        return centerAlignDataStyle;
    }

    /**
     * <PRE>
     * Number Type Data Cell Style 생성
     * </PRE>
     * 
     * @param workbook Cell Style 생성 대상이 되는 HSSFWorkbook
     * @return Number Type Data Cell Style
     */
    public HSSFCellStyle createNumericDataCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle numericDataStyle = workbook.createCellStyle();
        numericDataStyle.cloneStyleFrom(createDefaultDataCellStyle(workbook));
        HSSFDataFormat format = workbook.createDataFormat();

        numericDataStyle.setDataFormat(format.getFormat("###,##0"));
        numericDataStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

        return numericDataStyle;
    }

    /**
     * <PRE>
     * Row Index에 해당하는 Row 생성
     * </PRE>
     * 
     * @param rowIndex Row Index
     * @param cellValues Cell Value List
     * @param cellStyle Cell Style
     */
    public void createRow(HSSFSheet sheet, int rowIndex, String[] cellValues, HSSFCellStyle cellStyle) {
        HSSFRow headRow = sheet.createRow(rowIndex);

        for (int h = 0; h < cellValues.length; h++) {
            HSSFCell cell = headRow.createCell(h);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(cellValues[h]));
        }
    }
    
    /**
     * <PRE>
     * Row Index에 해당하는 Row 생성
     * </PRE>
     * 
     * @param rowIndex Row Index
     * @param cellValues Cell Value List
     * @param cellStyle Cell Style
     */
    public void createRow(HSSFWorkbook workbook, HSSFSheet sheet, int rowIndex, String[] cellValues, HSSFCellStyle[] cellStyles) {
        HSSFRow headRow = sheet.createRow(rowIndex);
        HSSFDataFormat format = workbook.createDataFormat();
        
        for (int h = 0; h < cellValues.length; h++) {
            HSSFCell cell = headRow.createCell(h);
            cell.setCellStyle(cellStyles[h]);
            
            // 숫자인 경우
            if(cellStyles[h].getDataFormat()==format.getFormat("###,##0") &&
               cellStyles[h].getAlignment()==HSSFCellStyle.ALIGN_RIGHT) {
            	cell.setCellValue(Long.parseLong(cellValues[h]));
            } else {
            	cell.setCellValue(new HSSFRichTextString(cellValues[h]));
            }
        }
    }

    /**
     * <PRE>
     * 문자열 끝부분의 반복되는 0을 제거한다.
     * </PRE>
     * 
     * @param param 제거 대상 값
     * @return 끝부분의 반복되는 0이 제거된 값
     */
    private String removeTrailZero(String param) {
        String tmpB = param.substring(param.indexOf(".") + 1);
        String tmpF = param.substring(0, param.indexOf("."));
        int zz = tmpB.length() - 1;

        if (Integer.parseInt(tmpB) > 0) {
            for (int i = zz; i < tmpB.length(); i--) {
                if ("0".equals(tmpB.substring(i))) {
                    tmpB = tmpB.substring(0, i);
                } else {
                    tmpB = "." + tmpB;
                    break;
                }
            }
        } else {
            tmpB = "";
        }

        return tmpF + tmpB;
    }

    /*
    public static void main(String[] args) throws Exception {
        ExcelUtil eu = new ExcelUtil("d:\\temp\\test.xls");

        try {
            eu.open();

            eu.setColumn(0, 0, "testvalue");
            eu.setColumn(0, 1, "testvalue");
            eu.setColumn(0, 2, "testvalue");
            eu.setColumn(1, 3, "testvalue");
            eu.setColumn(2, 4, "testvalue");
            eu.setColumn(3, 5, "testvalue");
            eu.setColumn(4, 6, "testvalue");
            eu.save();
            
        } finally {
            eu.close();
        }
    }
    */
    
    /**
     * xslx 엑셀파일로 부터 데이터 목록을 추출한다.
     */
	public MultiValueMap xslxExportExcelToList(String fileName) {
		MultiValueMap multiValueMap = new MultiValueMap();
		try {
			XSSFWorkbook xworkBook;
			xworkBook = new XSSFWorkbook(new FileInputStream(new File(fileName)));
			XSSFSheet xsheet = null;
			XSSFRow xrow = null;
			XSSFCell xcell = null;
			int sheetnum = xworkBook.getNumberOfSheets();//시트개수
			xsheet = xworkBook.getSheetAt(0);   //첫 시트
	
			int rows = xsheet.getPhysicalNumberOfRows();  //행의 수
	   
			for( int i=0;i<rows; i++ ){
				xrow = xsheet.getRow(i);          //row에 입력 된 값이 없을 경우 null을 리턴함.
				int cells = xrow.getPhysicalNumberOfCells();
	    	   
				for( int i2=0;i2<cells;i2++ ){
					xcell = xrow.getCell(i2);
					multiValueMap.set(i, "cell"+i, xcell.getStringCellValue());
					switch(xcell.getCellType()){
					 
					case 0:
						System.out.println(xcell.getNumericCellValue());
						break;
					
					case 1:
						System.out.println(xcell.getStringCellValue());
						break;
					
					case Cell.CELL_TYPE_FORMULA:
						System.out.println(xcell.getCellFormula());
					}
	    	    
				}
	    	    
	    	}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return multiValueMap;
	}
    
	/**
	 * xls 엑셀파일로 부터 데이터 목록을 추출한다.
	 */
	public MultiValueMap xlsExportExcelToList(String fileName) {
		MultiValueMap multiValueMap = new MultiValueMap();
		try {
			HSSFWorkbook hworkBook = new HSSFWorkbook(new FileInputStream(new File(fileName)));
			HSSFSheet hsheet = null;
			HSSFRow hrow = null;
			HSSFCell hcell = null;
			int sheetnum = hworkBook.getNumberOfSheets();//시트개수
			hsheet = hworkBook.getSheetAt(0);   //첫 시트
			
			int rows = hsheet.getPhysicalNumberOfRows();  //행의 수
			   
			for( int i=0;i<rows; i++ ){
				hrow = hsheet.getRow(i);          //row에 입력 된 값이 없을 경우 null을 리턴함.
				int cells = hrow.getPhysicalNumberOfCells();
				    	   
				for( int i2=0;i2<cells;i2++ ){
					hcell = hrow.getCell(i2);
					hcell = hrow.getCell(i2);
					multiValueMap.set(i, "cell"+i, hcell.getStringCellValue());
				    switch(hcell.getCellType()){
				    	case 0:
				    		System.out.println(hcell.getNumericCellValue());
				    		break;
				    	case 1:
				    		System.out.println(hcell.getStringCellValue());
				    	    break;
				    	case Cell.CELL_TYPE_FORMULA:
				    	     System.out.println(hcell.getCellFormula());
				    }
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return multiValueMap;
	}
}
