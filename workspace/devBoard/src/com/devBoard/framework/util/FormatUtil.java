package com.devBoard.framework.util;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import com.devBoard.framework.exception.ExRuntimeException;

/**  
 * @Class Name : FormatUtil.java
 * @Description : FormatUtil Class
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
public class FormatUtil {
    public static final String EMPTY = "";
	public static final String SCRIPT = "(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)";
	public static final String OBJECT = "(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)";
	public static final String APPLET = "(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)";
	public static final String EMBED = "(E|e)(M|m)(B|b)(E|e)(D|d)";
	public static final String FORM = "(F|f)(O|o)(R|r)(M|m)";
	public static final String NOT_PERMITTED = "Not Permitted";
	
	/**
	 * number 3자리씩 comma 붙이기
	 * 
	 * @param num 숫자
	 * @return comma가 붙은 숫자
	 */
	public static String formatComma(int num) {
		String str = String.valueOf(num);
		str = formatComma(str);
		
		return str;
	}
	
	/**
	 * number 3자리씩 comma 붙이기
	 * 
	 * @param numTo 숫자
	 * @return comma가 붙은 숫자
	 */
	public static String formatComma(String numTo) {
		String num = numTo;
		String spotFront = "";
		String spotAfter = "";
		String ret = "";
		String tmp = "";
		int len;

		if( (num==null) || num.equals("") ) {
			ret = "0";
			return ret;
		} else { 
			num = num.trim();
		}
		
		StringTokenizer tok = new StringTokenizer(num,".");
		
		if( tok.countTokens() > 1 ) {
			spotAfter = num.substring( num.indexOf(".") );
		}

		spotFront = tok.nextToken();

		if( spotFront.length() > 0 && spotFront.charAt(0)=='-' ) {
			ret = "-";
			spotFront = spotFront.substring(1);
		}

		len = spotFront.length();

		for(int i=1 ; i <= len ; i++ ) {
			tmp = spotFront.charAt(len-i) + tmp;
			if ( i%3 == 0 && i < len) {
				tmp = "," + tmp;
			}
		}

		ret = ret + tmp + spotAfter;
		return ret;
	}
	
	/**
	 * 문자열에서 앞과 뒤의 0을 제거한다.
	 * 
	 * @param zeroStrTo 문자열
	 * @return 0이 제거된 문자열
	 */
	public static String removeZero(String zeroStrTo) {
		String zeroStr = zeroStrTo;
		
		if(zeroStr==null || zeroStr.trim().equals("")) return "-";

		//if(zeroStr.trim().equals("")) return "-";
		java.util.StringTokenizer st = new java.util.StringTokenizer(zeroStr, ".");
		
		String others = "";
		if(st.countTokens() > 1) others = zeroStr.substring(zeroStr.indexOf("."));
		zeroStr = st.nextToken();

		int index = 0;
		String signStr = "";
		
		if(zeroStr.charAt(0) == '-') {
			signStr = "-";
			zeroStr = zeroStr.substring(1);
		} else if(zeroStr.charAt(0) == '+') {
			signStr = "";
			zeroStr = zeroStr.substring(1);
		}

		if(zeroStr.equals("")) return "-";
		
		while(zeroStr.charAt(index) == '0') {
			index++;
			if(index >= zeroStr.length()) return signStr + "0" + others;
		}
		
		if(zeroStr.length()==0) return signStr + "0" + others;
		
		return signStr + zeroStr.substring(index) + others;
	}

	/**
	 * 스트링의 마지막에서 두번째자리에 소수점찍기 formatting
	 * 
	 * @param pointStrTo 문자열
	 * @return 마지막에서 두번째자리에 소수점이 찍힌 문자열
	 */
	public static String format2Point(String pointStrTo) {
		String pointStr = pointStrTo;
		
		if( pointStr == null || pointStr.equals("") ) {
			return "";
		}

		int flag = 0;

		if( pointStr.charAt(0) == '-' ) {
			pointStr = pointStr.substring(1);
			flag = 1;
		}

		int pointInt = pointStr.length();
		String retStr = null;

		if( pointInt > 2 ) {
			int temp = pointInt - 2;
					
			String first = pointStr.substring(0,temp);
			String last = pointStr.substring(temp,pointInt);

			retStr = first + "." + last;
		} else if( pointInt == 2 ) {
			retStr = "0." + pointStr;
		} else if( pointInt == 1 ) {
			retStr = "0.0" + pointStr;
		} else {
			retStr = "-";
		}
		
		if( flag == 1 )
			return "-"+retStr;
		else
			return retStr;
	}
	
	/**
	 * YYYYMMDD -> YYYY/MM/DD로 날짜 포맷
	 * 
	 * @param str 포맷되지 않은 날짜
	 * @return YYYY/MM/DD로 포맷된 날짜
	 */
	public static String formatDate(String str) {
		return formatDate(str, "/");
	}
	
	/**
	 * YYYYMMDD -> YYYY-MM-DD로 날짜 포맷
	 * 
	 * @param str 포맷되지 않은 날짜
	 * @return YYYY-MM-DD로 포맷된 날짜
	 */
	public static String formatDate2(String str) {
		return formatDate(str, "-");
	}
	
	/**
	 * 구분자에 맞게 날짜를 포맷하여 반환한다.
	 * 
	 * @param str 포맷되지 않은 날짜
	 * @param seperatorType 구분자
	 * @return 포맷된 날짜
	 */
	public static String formatDate(String str, String seperatorType) {
		String retStr = "";
		
		if(str != null && str.length() == 8) {
			retStr = str.substring(0, 4) + seperatorType + str.substring(4, 6) + seperatorType + str.substring(6);			
		} else if (str != null && str.length() == 6) {
		    retStr = str.substring(0, 4) + seperatorType + str.substring(4, 6);
		}
		
		return retStr;
	}
	
	/**
	 * 지정된 소수점 이하자리까지 반환
	 * 
	 * @param num 숫자
	 * @param position 소수점 이하자리
	 * @return 지정된 자리만큼 잘린 수
	 */
	public static double cutNumPosition(double num, int position) {
		int base = 1;
		
		for(int i=0; i < position; i++) {
			base = base * 10;
		}
		
		return Math.floor(num * base)/base;
	}
	
	/**
	 * 지정된 소수점 이하자리까지 반환
	 * 
	 * @param num 숫자(문자열)
	 * @param position 소수점 이하자리
	 * @return 지정된 자리만큼 잘린 수(문자열)
	 */
	public static String cutNumPosition(String num, int position) {
		double d = Double.parseDouble(num);
		
		return String.valueOf(cutNumPosition(d, position));
	}
	
	/**
	 * 지정된 소수점 자리에서 반올림
	 * 
	 * @param num 반올림할 숫자
	 * @param position 반올림할 자리
	 * @return 반올림된 숫자
	 */
	public static double roundNumber(double num, int position) {
		int base = 1;
		
		for(int i=0; i < position; i++) {
			base = base * 10;
		}
		
		return Math.floor(num * base + 0.5)/base;
	}
	
	/**
	 * 지정된 소수점 자리에서 반올림
	 * 
	 * @param num 반올림할 숫자(문자열)
	 * @param position 반올림할 자리
	 * @return 반올림된 숫자(문자열)
	 */
	public static String roundNumber(String num, int position) {
		double d = Double.parseDouble(num);
		
		return String.valueOf(roundNumber(d, position));
	}
	
	/**
	 * 지정한 길이와 비교하여 남는 영역의 왼쪽을 0으로 채운 후 반환한다.
	 * 예) numSpaceToZero(5, "123") -> 00123, numSpaceToZero(2, "123") -> 00
	 * 
	 * @param size 총길이
	 * @param num 숫자
	 * @return 왼쪽을 0으로 채운 값
	 */
	public static String numSpaceToZero(int size, String num) {
		StringBuffer result = new StringBuffer();

		int numInt = num.length();
		int temp = size - numInt;

		if(temp >= 0) {
			for(int i = 0; i < temp; i++) {
	    			result.append("0");
			}
    		
			result.append(num);
		} else {
			for(int i = 0; i < size; i++) {
	    			result.append("0");
			}
		}
		
		return result.toString();
	}
    
	/**
	 * 문자열이 null인지를 검사한다.
	 * <BR>문자열이 null 또는 white space인 경우에는 "true"을 반환한다.
	 * 
	 * @param str 문자열
	 * @return Null 여부
	 */
	public static boolean isNull( String str ) {
	    return ( str == null || "null".equals( str ) || "".equals( str ) ); 
	}
	
	/**
	 * 문자열이 null이 아닌지를 검사한다.
	 * <BR>문자열이 null 또는 white space인 경우에는 "거짓"을 반환한다.
	 * 
	 * @param str 문자열
	 * @return null 아님 여부
	 */
	public static boolean isNotNull( String str ) {
	    return !( str == null || "".equals( str ) );
	}
	
	/**
	 * 문자열이 null인 경우에는 whiteSpace를 반환한다.
	 * 
	 * @param string 문자열
	 * @return null -> empty string 처리된 문자열
	 */
	public static String nullToEmptyString( String string ) { 
		return isNull( string ) ? "" : string;
	}
	
	/**
	 * null이면 다른 문자열로 치환한다.
	 * 
	 * @param str1 null 비교 대상 문자열
	 * @param str2 치환 문자열
	 * @return 치환된 문자열
	 */
	public static String switchingNull(String str1, String str2) {
		if(isNull(str1)) {
			return str2;
		} else {
			return str1;
		}
	}

	/**
	 * 문자열 배열에 대해 null이면 다른 문자열로 치환한다.
	 * 
	 * @param str1 null 비교 대상 문자열 배열
	 * @param str2 치환 문자열 배열
	 * @return 치환된 문자열 배열
	 */
	public static String[] switchingNull(String []str1, String []str2) {
		if( str1 == null ) {
			return str2;
		} else {
			return str1;
		}
	}
	
	/**
	 * 특정 인코딩의 문자열을 다른 인코딩으로 변환한다.
	 * 
	 * @param str 변환할 문자열
	 * @param encode 기존 인코딩
	 * @param charsetName 대상 인코딩
	 * @return 인코딩 변환된 문자열
	 */
	private static String encodeText(String str, String encode, String charsetName) {
		String result = null;
		
		try {
			result = isNull( str ) ? null : new String( str.getBytes( encode ), charsetName );
		} catch( UnsupportedEncodingException e ) {
			throw new ExRuntimeException(e);
		}
		
		return result;
	}
	
	/**
	 * 문자열을 euc-kr에서 8859_1로 디코딩 한다.
	 * 
	 * @param str 변환할 문자열
	 * @return 변환된 문자열
	 */
	public static String decode(String str) {
	    return encodeText( str, "euc-kr", "8859_1" ); 
	}
	
	/**
	 * 문자열을 8859_1에서 euc-kr로 인코딩 한다.
	 * 
	 * @param str 변환할 문자열
	 * @return 변환된 문자열
	 */        
	public static String encode(String str) {
	    return encodeText( str, "8859_1", "euc-kr" );
	}

	/**
	 * 문자열을 8859_1에서 utf-8로 인코딩 한다.
	 * @param str 변환할 문자열
	 * @return 변환된 문자열
	 */        
	public static String encodeUtf( String str ) {
	    return encodeText( str, "8859_1", "utf-8" );
	}
}