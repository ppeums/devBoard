package com.devBoard.framework.collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * @Class Name : MultiValueMap.java
 * @Description : MultiValueMap.java Class
 * @Modification Information
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012. 8. 3.  송제승     최초생성
 * @ 2012. 8.29.  송제승                  toXml() 메소드 추가.
 * 
 * @author 송제승
 * @since 2012. 8. 3.
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
public class MultiValueMap implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5651888449654122575L;

	private HashMap<Object, ArrayList<Object>> keyAndList = new HashMap<Object, ArrayList<Object>>();
	
	/**
	 * 해당 index에 key, value를 설정한다.
	 * 
	 * @param index index
	 * @param key key
	 * @param value value
	 */
	public void set(int index, Object key, Object value) {
		ArrayList<Object> list = keyAndList.get(key);
		
		if(list==null) list = new ArrayList<Object>();
		
		// index가 size를 초과하는 경우 add
		int addSize = index - list.size() + 1;
		
		for(int i=0; i<addSize; i++) {
			list.add(null);
		}
		
		list.set(index, value);
		
		keyAndList.put(key, list);
	}
	
	/**
	 * index, key에 해당하는 value를 리턴한다.
	 * 
	 * @param index index
	 * @param key key
	 * @return value
	 */
	public Object get(int index, Object key) {
		Object result = null;
		
		ArrayList<Object> list = keyAndList.get(key);
		
		if(list!=null) result = list.get(index);
		
		return result;
	}
	
	public Set<Object> keySet() {
		return keyAndList.keySet();
	}
	
	/**
	 * key에 해당하는 value 건수를 리턴한다.
	 * 
	 * @param key key
	 * @return 건수
	 */
	public int size(Object key) {
		int size = 0;
		
		ArrayList<Object> list = keyAndList.get(key);
		
		if(list!=null) size = list.size();
		
		return size;
	}
	
	/**
	 * 모든 데이터를 문자열로 출력한다.
	 * 
	 * @return 데이터에 해당하는 문자열
	 */
	public String toString() {
		StringBuffer result = new StringBuffer();
		
		Object[] keys = keyAndList.keySet().toArray();
		int maxSize = 0;
		boolean isFirst = false;
		
		for(Object key : keys) {
			if(maxSize<keyAndList.get(key).size()) maxSize = keyAndList.get(key).size();
		}
		
		result.append("\nData: \n");
		
		for(int i=0; i<maxSize; i++) {
			isFirst = false;
			
			if(i!=0) result.append("\n");
			
			result.append("[");
			
			for(int j=0; j<keys.length; j++) {
				if(i<keyAndList.get(keys[j]).size()) {
					if(isFirst) result.append(", ");
					
					isFirst = true;
					
					result.append(keys[j].toString()).append("=").append(keyAndList.get(keys[j]).get(i).toString());
				}
			}
			
			result.append("]");
		}
		
		result.append("\n").append("Record Count: ").append(maxSize);
		
		return result.toString();
	}
	
	/**
	 * 데이터를 Xml 형태로 변환한다.
	 * 
	 * @param serviceId
	 * @return 데이터에 해당하는 XML
	 */
	public String toXml(String serviceId){
		
		StringBuffer result = new StringBuffer();
		
		Object[] keys = keyAndList.keySet().toArray();
		int maxSize = 0; // Array 중 제일 큰 Array Size.
		for(Object key : keys) {
			if(maxSize<keyAndList.get(key).size()) 
				maxSize = keyAndList.get(key).size();
		}
		
		result.append("<?xml version=\"1.0\" encoding=\"euc-kr\"?>\n");
		result.append("<").append(serviceId).append(">\n");
		
		for(int i=0; i<maxSize; i++) {
			
			for(int j=0; j<keys.length; j++) {
				if(i < keyAndList.get(keys[j]).size()) {
					result.append("    <").append(keys[j]).append(">\n"); // Key Tag
					result.append("        <Value>").append(keyAndList.get(keys[j]).get(i)).append("</Value>\n"); // Value Tag
					result.append("    </").append(keys[j]).append(">\n"); // Key Tag
				}
			}
			
			result.append("</").append(serviceId).append(">\n");
		}
		return result.toString();
	}
	
	
	
	public static void main(String[] args){
		
		MultiValueMap inputData = new MultiValueMap();
		
    	inputData.set(0, "RKIHO", "0002");							// 라우팅기호
    	inputData.set(0, "STR10", "vo.getBtlKind()");					// 게시판 구분
    	inputData.set(0, "STR11", "vo.getApplyYy()");					// 신청년도
    	inputData.set(0, "STR12", "(String)vo.getSeqNo().toString()");// 일련번호 
    	inputData.set(0, "STR13", "DateUtil.getToday()");	// 입력일자
    	inputData.set(0, "STR14", "vo.getJuminNo()");					// 주민번호
    	inputData.set(0, "STR15", "");								// 증번호
    	inputData.set(0, "STR16", "vo.getWrNm()");					// 작성자명
    	inputData.set(0, "STR17", "emailId");							// 이메일 ID
    	inputData.set(0, "STR18", "emailDomain");						// 이메일 도메인
    	inputData.set(0, "STR19", "vo.getTelNo()");					// 전화번호
    	inputData.set(0, "STR20", "04");							// 접수방법
    	inputData.set(0, "STR21", "");								// 방문예약일자 (방문예약인 경우)
    	inputData.set(0, "STR22", "vo.getApplCont()");				// 민원상담내역
    	inputData.set(0, "STR23", "1");								// 민원상담결과 내역
    	inputData.set(0, "STR24", "DateUtil.getToday");	// 접수일자
    	inputData.set(0, "STR25", "99991231");						// 전송일자 
    	inputData.set(0, "STR26", "99991231");						// 처리일자
    	inputData.set(0, "STR27", "01");							//
    	inputData.set(0, "STR28", "");								//
    	inputData.set(0, "STR29", "");								//
    	inputData.set(0, "STR30", "");								//
    	inputData.set(0, "STR31", "vo.getBrchCd()");					// 접수지사
    	inputData.set(0, "STR32", "DateUtil.getToday()");	// 접수일자
    	inputData.set(0, "STR33", "");								//
    	inputData.set(0, "GKGD10_SUJINJA_NM", "vo.getWrNm()");		// 수진자성명
    	inputData.set(0, "GKGD10_SUJIN_DT", "vo.getSujinDt()");		// 진료일자
    	inputData.set(0, "GKGD10_YKIHO_KIND_CD", "vo.getYoyangKindCd()");	// 요양기관종별 구분코드
    	inputData.set(0, "GKGD10_YKIHO", "vo.getYkiho()");			// 요양기관기호
    	inputData.set(0, "GKGF01_M_TEL_NO", "vo.getHpNo()");			// 휴대폰번호
    	inputData.set(3, "GKGD10_SMS_RECV_AGRE", "vo.getSmsSendYn()");	// SMS 수신여부

//    	System.out.println(inputData.toString());
    	System.out.println(inputData.toXml("s_jgea100_u1"));
	}

}
