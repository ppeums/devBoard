package com.devBoard.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

/**  
 * @Class Name : BeanUtil.java
 * @Description : BeanUtil Class
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
public class BeanUtil {
	
	/**
	 * VO의 I/U/D Status에 따라 VO를 추출하여 반환
	 * @param voList VO List
	 * @param status Status
	 * @return Status에 따라 추출된 VO List
	 */
	/*
	@SuppressWarnings("unchecked")
	public static List getValueObjectListByStatus(List voList, String status) {
		List<Object> resultVoList = new ArrayList();
		
		for(Object vo : voList) {
			if(status.equals(((CommonVO)vo).getStatus())) {
				resultVoList.add(vo);
			}
		}
		
		return resultVoList;
	}
	*/
	
	/**
	 * Field에 대한 값 설정
	 * 
	 * @param vo Value Object
	 * @param method Method
	 * @param value String Value
	 * @throws Exception
	 */
	public static void setField(Object vo, Method method, String value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if(value!=null) {
			@SuppressWarnings("rawtypes")
			Class type = method.getParameterTypes()[0]; // 첫번째 Parameter만 처리
			
			if(type==String.class) {
				method.invoke(vo, value);
			} else {
				if(!"".equals(value)) {
					if(type==boolean.class) {
						method.invoke(vo, Boolean.parseBoolean(value));
					} else if(type==Boolean.class) {
						method.invoke(vo, Boolean.valueOf(value));
					} else if(type==short.class) {
						method.invoke(vo, Short.parseShort(value));
					} else if(type==Short.class) {
						method.invoke(vo, Short.valueOf(value));
					} else if(type==int.class) {
						method.invoke(vo, Integer.parseInt(value));
					} else if(type==Integer.class) {
						method.invoke(vo, Integer.valueOf(value));
					} else if(type==long.class) {
						method.invoke(vo, Long.parseLong(value));
					} else if(type==Long.class) {
						method.invoke(vo, Long.valueOf(value));
					} else if(type==float.class) {
						method.invoke(vo, Float.parseFloat(value));
					} else if(type==Float.class) {
						method.invoke(vo, Float.valueOf(value));
					} else if(type==double.class) {
						method.invoke(vo, Double.parseDouble(value));
					} else if(type==Double.class) {
						method.invoke(vo, Double.valueOf(value));
					} else if(type==BigDecimal.class) {
						method.invoke(vo, new BigDecimal(value));
					}
				}
			}
		}
	}
	
	/**
	 * Array Field에 대한 값 설정
	 * 
	 * @param vo Value Object
	 * @param method Method
	 * @param values String Array
	 * @throws Exception
	 */
	private static void setField(Object vo, Method method, String[] values) throws Exception {
		if(values!=null) {
			@SuppressWarnings("rawtypes")
			Class type = method.getParameterTypes()[0];	// 첫번째 Parameter만 처리(Array인 경우는 java.lang.String[]와 같음)
			
			if(type==String[].class) {
				method.invoke(vo, (Object)values);
			} else {
				if(type==boolean[].class) {
					method.invoke(vo, toBooleanArray(values));
				} else if(type==java.lang.Boolean[].class) {
					method.invoke(vo, toBooleanClassArray(values));
				} else if(type==short[].class) {
					method.invoke(vo, toShortArray(values));
				} else if(type==java.lang.Short[].class) {
					method.invoke(vo, toShortClassArray(values));
				} else if(type==int[].class) {
					method.invoke(vo, toIntegerArray(values));
				} else if(type==java.lang.Integer[].class) {
					method.invoke(vo, toIntegerClassArray(values));
				} else if(type==long[].class) {
					method.invoke(vo, toLongArray(values));
				} else if(type==java.lang.Long[].class) {
					method.invoke(vo, toLongClassArray(values));
				} else if(type==float[].class) {
					method.invoke(vo, toFloatArray(values));
				} else if(type==java.lang.Float[].class) {
					method.invoke(vo, toFloatClassArray(values));
				} else if(type==double[].class) {
					method.invoke(vo, toDoubleArray(values));
				} else if(type==java.lang.Double[].class) {
					method.invoke(vo, toDoubleClassArray(values));
				} else if(type==java.math.BigDecimal[].class) {
					method.invoke(vo, toBigDecimalArray(values));
				}
			}
		}
	}
	
	/**
	 * 메소드명으로부터 컬럼명을 가져온다.
	 * 
	 * @param methodName Method Name
	 * @return Column Name
	 */
	public static String getColumnName(String methodName) {
		String columnName = methodName.substring(3); // set 제거
		
		columnName = columnName.substring(0, 1).toLowerCase() + columnName.substring(1); // 첫 문자를 소문자로 변경
		
		return columnName;
	}
	
	/**
	 * VO에 값을 설정한다.
	 * 
	 * @param issacweb IssacWebServer
	 * @param vo Value Object
	 * @throws Exception
	 */
	public static void setValue(HttpServletRequest request, Object vo) throws Exception {
		Method[] methods = vo.getClass().getMethods();
		boolean isArray = false;
		String name = "";
		String value = "";
		String[] values = null;
		
		for(Method method : methods) {
			if(method.getName().startsWith("set")) {
				name = getColumnName(method.getName());
				isArray = method.getParameterTypes()[0].isArray(); // 첫번째 Parameter만 처리
				
				if(isArray) {
					values = request.getParameterValues(name);
					setField(vo, method, values);
				} else {
					value = request.getParameter(name);
					setField(vo, method, value);
				}
			}
		}
	}
	
	private static Object toBooleanArray(String[] values) throws Exception {
		boolean[] results = new boolean[values.length];
		
		for(int i=0; i<values.length; i++) {
			if(values[i]!=null && !"".equals(values[i])) results[i] = Boolean.parseBoolean(values[i]);
		}
		
		return results;
	}
	private static Object toBooleanClassArray(String[] values) throws Exception {
		Boolean[] results = new Boolean[values.length];
		
		for(int i=0; i<values.length; i++) {
			if(values[i]!=null && !"".equals(values[i])) results[i] = Boolean.valueOf(values[i]);
		}
		
		return results;
	}
	private static Object toShortArray(String[] values) throws Exception {
		short[] results = new short[values.length];
		
		for(int i=0; i<values.length; i++) {
			if(values[i]!=null && !"".equals(values[i])) results[i] = Short.parseShort(values[i]);
		}
		
		return results;
	}
	private static Object toShortClassArray(String[] values) throws Exception {
		Short[] results = new Short[values.length];
		
		for(int i=0; i<values.length; i++) {
			if(values[i]!=null && !"".equals(values[i])) results[i] = Short.valueOf(values[i]);
		}
		
		return results;
	}
	private static Object toIntegerArray(String[] values) throws Exception {
		int[] results = new int[values.length];
		
		for(int i=0; i<values.length; i++) {
			if(values[i]!=null && !"".equals(values[i])) results[i] = Integer.parseInt(values[i]);
		}
		
		return results;
	}
	private static Object toIntegerClassArray(String[] values) throws Exception {
		Integer[] results = new Integer[values.length];
		
		for(int i=0; i<values.length; i++) {
			if(values[i]!=null && !"".equals(values[i])) results[i] = Integer.valueOf(values[i]);
		}
		
		return results;
	}
	private static Object toLongArray(String[] values) throws Exception {
		long[] results = new long[values.length];
		
		for(int i=0; i<values.length; i++) {
			if(values[i]!=null && !"".equals(values[i])) results[i] = Long.parseLong(values[i]);
		}
		
		return results;
	}
	private static Object toLongClassArray(String[] values) throws Exception {
		Long[] results = new Long[values.length];
		
		for(int i=0; i<values.length; i++) {
			if(values[i]!=null && !"".equals(values[i])) results[i] = Long.valueOf(values[i]);
		}
		
		return results;
	}
	private static Object toFloatArray(String[] values) throws Exception {
		float[] results = new float[values.length];
		
		for(int i=0; i<values.length; i++) {
			if(values[i]!=null && !"".equals(values[i])) results[i] = Float.parseFloat(values[i]);
		}
		
		return results;
	}
	private static Object toFloatClassArray(String[] values) throws Exception {
		Float[] results = new Float[values.length];
		
		for(int i=0; i<values.length; i++) {
			if(values[i]!=null && !"".equals(values[i])) results[i] = Float.valueOf(values[i]);
		}
		
		return results;
	}
	private static Object toDoubleArray(String[] values) throws Exception {
		double[] results = new double[values.length];
		
		for(int i=0; i<values.length; i++) {
			if(values[i]!=null && !"".equals(values[i])) results[i] = Double.parseDouble(values[i]);
		}
		
		return results;
	}
	private static Object toDoubleClassArray(String[] values) throws Exception {
		Double[] results = new Double[values.length];
		
		for(int i=0; i<values.length; i++) {
			if(values[i]!=null && !"".equals(values[i])) results[i] = Double.valueOf(values[i]);
		}
		
		return results;
	}
	private static Object toBigDecimalArray(String[] values) throws Exception {
		BigDecimal[] results = new BigDecimal[values.length];
		
		for(int i=0; i<values.length; i++) {
			if(values[i]!=null && !"".equals(values[i])) results[i] = new BigDecimal(values[i]);
		}
		
		return results;
	}
}
