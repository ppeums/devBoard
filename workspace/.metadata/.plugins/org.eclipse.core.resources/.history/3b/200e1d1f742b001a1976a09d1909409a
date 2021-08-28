package com.devBoard.framework.vo;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

/**  
 * @Class Name : CommonVO.java
 * @Description : CommonVO Class
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2012.12.11           최초생성
 * 
 * @author 송제승
 * @since 2012. 12.11
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by Sinsuldong All right reserved.
 */
public class CommonVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2190952078627732288L;

	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색Keyword */
    private String searchKeyword = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";
    
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 10;
    
    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    /** 시작일자 **/
    private String startDate;
    
    /** 종료일자 **/
    private String endDate;

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
    
    /**
	 * @return the searchCondition
	 */
	public String getSearchCondition() {
		return searchCondition;
	}

	/**
	 * @param searchCondition the searchCondition to set
	 */
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	/**
	 * @return the searchKeyword
	 */
	public String getSearchKeyword() {
		return searchKeyword;
	}

	/**
	 * @param searchKeyword the searchKeyword to set
	 */
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	/**
	 * @return the searchUseYn
	 */
	public String getSearchUseYn() {
		return searchUseYn;
	}

	/**
	 * @param searchUseYn the searchUseYn to set
	 */
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	/**
	 * @return the pageIndex
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * @return the pageUnit
	 */
	public int getPageUnit() {
		return pageUnit;
	}

	/**
	 * @param pageUnit the pageUnit to set
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the firstIndex
	 */
	public int getFirstIndex() {
		return firstIndex;
	}

	/**
	 * @param firstIndex the firstIndex to set
	 */
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	/**
	 * @return the lastIndex
	 */
	public int getLastIndex() {
		return lastIndex;
	}

	/**
	 * @param lastIndex the lastIndex to set
	 */
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	/**
	 * @return the recordCountPerPage
	 */
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	/**
	 * @param recordCountPerPage the recordCountPerPage to set
	 */
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	/**
	 * VO를 문자열로 변환한다.
	 * 
	 * @return 문자열
	 */
	public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

	/**
	 * VO를 XML로 변환한다.
	 * @return XML
	 */
    public String toXml() {
    	StringBuffer xmlBuffer = new StringBuffer();
    	
    	Method[] methodArray = this.getClass().getMethods();
    	
    	String fieldName;
    	Object dataObj;
    	String data = null;
    	Method method;
    	@SuppressWarnings("rawtypes")
		Class type;
    	
		for (int i = 0; i < methodArray.length; i++) {
			try {
				method = methodArray[i];
				type = method.getReturnType();
				
				if( method.getModifiers()==Modifier.PUBLIC && method.getName().startsWith("get") ) {
					fieldName = this.getFieldName(method.getName());
					xmlBuffer.append("<").append(fieldName).append(">");
					
					// Number
					if( type.isPrimitive() || type==Short.class || type==Integer.class || type==Long.class ||
						type==Float.class || type==Double.class || type == BigDecimal.class) {
						dataObj = method.invoke(this, new Object[]{});
						
						if(dataObj!=null) {
							data = dataObj.toString();
						}
						
						if(data==null) {
							xmlBuffer.append("");
						} else if("0.0".equals(data)) {
							xmlBuffer.append("0");
						} else {
							// *.0
							if(data.endsWith(".0")) {
								xmlBuffer.append(data.substring(0, data.indexOf("."))); // .0 제거
							} else {
								xmlBuffer.append(data);
							}
						}
					}
					// String
					else if( type == String.class ) {
						data = (String)method.invoke(this, new Object[]{});
						
						if(data==null) data = "";
						
						xmlBuffer.append("<![CDATA[").append(data).append("]]>");
					}
					
					xmlBuffer.append("</").append(fieldName).append(">");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
    	return xmlBuffer.toString();
    }
    
    private String getFieldName(String methodName) {
    	// get 제거, 첫문자 소문자 처리
    	String fieldName = methodName.substring(3,4).toLowerCase() + methodName.substring(4);
    	
    	return fieldName;
    }
}
