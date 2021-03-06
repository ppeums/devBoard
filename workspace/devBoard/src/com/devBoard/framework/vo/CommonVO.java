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
 * @ 2013. 7. 26.           최초생성
 * 
 * @author 송제승
 * @since 2013. 7. 26.
 * @version 1.0
 * @see
 * 
 *  Copyright (C) 2013 by Kap All right reserved.
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
    private int searchPageIndex = 1;
    private int pageIndex = 1;
    private int searchSqPageIndex = 1;
    
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
    private int searchRecordCountPerPage = 10;
    private int searchMasterRecordCountPerPage = 10;
    private int searchSqRecordCountPerPage = 10;
    /** 시작일자 **/
    private String startDate;
    
    /** 종료일자 **/
    private String endDate;
    
    /** 삭제된파일아이디 **/
    private String deleteFileId;
    
    /** 선택된 화면아이디 **/
    private String selectedId;
    
    private String comFileTempSeq; // dextUploadFL처리용
    
    private int comFileSeq; // 첨부파일번호
    
    private String chk;	// 체크값
    
    private int comFileDtlSeq;
    private String param1; // 부모창 param1
	private String param2; // 부모창 param2
	private String param3; // 부모창 param3
	private String param4; // 부모창 param4
	private String param5; // 부모창 param5
	private String param6; // 부모창 param6
	private String param7; // 부모창 param7
	private String param8; // 부모창 param8
	private String param9; // 부모창 param9
	private String param10; // 부모창 param10
	private String param11; // 부모창 param11
	private String param12; // 부모창 param12
	private String param13; // 부모창 param13
	private String param14; // 부모창 param14
	private String param15; // 부모창 param15
	private String param16; // 부모창 param16
	private String param17; // 부모창 param17
	private String param18; // 부모창 param18
	private String param19; // 부모창 param19
	private String param20; // 부모창 param20
	private String param21; // 부모창 param21
	
	private String callback; // 부모창 function이름
	private String menuKey; // 메뉴키
	private String menuUrl;
	private String selectedSvcServiceInfoId;
	
	private String uploadProcess; // 업로드 상태값
	private String strReturnUrl; // 복귀유알엘
	
	public String getParam20() {
		return param20;
	}
	public void setParam20(String param20) {
		this.param20 = param20;
	}
	public String getParam21() {
		return param21;
	}
	public void setParam21(String param21) {
		this.param21 = param21;
	}
	public String getParam13() {
		return param13;
	}
	public void setParam13(String param13) {
		this.param13 = param13;
	}
	public String getParam14() {
		return param14;
	}
	public void setParam14(String param14) {
		this.param14 = param14;
	}
	public String getParam15() {
		return param15;
	}
	public void setParam15(String param15) {
		this.param15 = param15;
	}
	public String getParam16() {
		return param16;
	}
	public void setParam16(String param16) {
		this.param16 = param16;
	}
	public String getParam17() {
		return param17;
	}
	public void setParam17(String param17) {
		this.param17 = param17;
	}
	public String getParam18() {
		return param18;
	}
	public void setParam18(String param18) {
		this.param18 = param18;
	}
	public String getParam19() {
		return param19;
	}
	public void setParam19(String param19) {
		this.param19 = param19;
	}
	public String getStrReturnUrl() {
		return strReturnUrl;
	}
	public void setStrReturnUrl(String strReturnUrl) {
		this.strReturnUrl = strReturnUrl;
	}
	public int getSearchSqPageIndex() {
		return searchSqPageIndex;
	}
	public void setSearchSqPageIndex(int searchSqPageIndex) {
		this.searchSqPageIndex = searchSqPageIndex;
	}
	public int getSearchSqRecordCountPerPage() {
		return searchSqRecordCountPerPage;
	}
	public void setSearchSqRecordCountPerPage(int searchSqRecordCountPerPage) {
		this.searchSqRecordCountPerPage = searchSqRecordCountPerPage;
	}
	public String getParam8() {
		return param8;
	}
	public void setParam8(String param8) {
		this.param8 = param8;
	}
	public int getSearchMasterRecordCountPerPage() {
		return searchMasterRecordCountPerPage;
	}
	public void setSearchMasterRecordCountPerPage(int searchMasterRecordCountPerPage) {
		this.searchMasterRecordCountPerPage = searchMasterRecordCountPerPage;
	}
	public int getSearchPageIndex() {
		return searchPageIndex;
	}
	public void setSearchPageIndex(int searchPageIndex) {
		this.searchPageIndex = searchPageIndex;
	}
	public int getSearchRecordCountPerPage() {
		return searchRecordCountPerPage;
	}
	public void setSearchRecordCountPerPage(int searchRecordCountPerPage) {
		this.searchRecordCountPerPage = searchRecordCountPerPage;
	}
	public String getParam3() {
		return param3;
	}
	public void setParam3(String param3) {
		this.param3 = param3;
	}
	public String getParam4() {
		return param4;
	}
	public void setParam4(String param4) {
		this.param4 = param4;
	}
	public String getParam5() {
		return param5;
	}
	public void setParam5(String param5) {
		this.param5 = param5;
	}


	public String getParam9() {
		return param9;
	}
	public void setParam9(String param9) {
		this.param9 = param9;
	}
	
	public String getParam10() {
		return param10;
	}
	public void setParam10(String param10) {
		this.param10 = param10;
	}
	public String getParam11() {
		return param11;
	}
	public void setParam11(String param11) {
		this.param11 = param11;
	}
	public String getParam12() {
		return param12;
	}
	public void setParam12(String param12) {
		this.param12 = param12;
	}
	public String getParam6() {
		return param6;
	}
	public void setParam6(String param6) {
		this.param6 = param6;
	}
	public String getParam7() {
		return param7;
	}
	
	public void setParam7(String param7) {
		this.param7 = param7;
	}
	public String getSelectedSvcServiceInfoId() {
		return selectedSvcServiceInfoId;
	}
	public void setSelectedSvcServiceInfoId(String selectedSvcServiceInfoId) {
		this.selectedSvcServiceInfoId = selectedSvcServiceInfoId;
	}
	public String getMenuKey() {
		return menuKey;
	}
	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public int getComFileDtlSeq() {
		return comFileDtlSeq;
	}
	public void setComFileDtlSeq(int comFileDtlSeq) {
		this.comFileDtlSeq = comFileDtlSeq;
	}
	public int getComFileSeq() {
		return comFileSeq;
	}
	public void setComFileSeq(int comFileSeq) {
		this.comFileSeq = comFileSeq;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
    
	public String getComFileTempSeq() {
		return comFileTempSeq;
	}
	public void setComFileTempSeq(String comFileTempSeq) {
		this.comFileTempSeq = comFileTempSeq;
	}
    
	public String getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(String selectedId) {
		this.selectedId = selectedId;
	}

	public String getDeleteFileId() {
		return deleteFileId;
	}

	public void setDeleteFileId(String deleteFileId) {
		this.deleteFileId = deleteFileId;
	}

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

	public String getUploadProcess() {
		return uploadProcess;
	}
	public void setUploadProcess(String uploadProcess) {
		this.uploadProcess = uploadProcess;
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
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
    
}
