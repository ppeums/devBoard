package com.devBoard.framework.view;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**  
 * @Class Name : EgovMapXmlView.java
 * @Description : EgovMapXmlView Class
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
public class EgovMapXmlView extends AbstractView {
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void renderMergedOutputModel(@SuppressWarnings("rawtypes") Map model
			,HttpServletRequest request ,HttpServletResponse response) throws Exception {
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = null;
		
		try {
			writer = response.getWriter();
			
			StringBuffer xml = new StringBuffer();
		    
			@SuppressWarnings("unchecked")
			List<EgovMap> list = (List<EgovMap>)model.get("list");
			
			// 단일 EgovMap 처리 2012.06.29
			EgovMap map = (EgovMap)model.get("map");
			
			xml.append("<xmlView>");
			
			// List
			if(list!=null) {
				for(EgovMap item:list) {
					xml.append("<item>");
					xml.append(getEgovMapToXml(item));
					xml.append("</item>");
				}
			} 
			
			// map
			if(map != null){
				xml.append("<item>");
				xml.append(getEgovMapToXml(map));
				xml.append("</item>");
			}
		    
			xml.append("</xmlView>");
			
			writer.write(xml.toString());
		} finally {
			if(writer!=null) writer.close();
		}
	}
	
	/**
	 * EgovMap을 XML로 변환한다.
	 * 
	 * @param map EgovMap
	 * @return 변환된 XML
	 */
	protected String getEgovMapToXml(EgovMap map) {
		StringBuffer xmlBuffer = new StringBuffer();
		for (Object obj : map.keyList()) {
			String key = (String) obj;
			xmlBuffer.append("<" + key + "><![CDATA[" + map.get(key) + "]]></" + key + ">");
		}
		return xmlBuffer.toString();
	}
}
