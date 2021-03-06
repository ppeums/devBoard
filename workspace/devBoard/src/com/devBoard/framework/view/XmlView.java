package com.devBoard.framework.view;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devBoard.framework.vo.CommonVO;

import org.springframework.web.servlet.view.AbstractView;

/**  
 * @Class Name : XmlView.java
 * @Description : XmlView Class
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
public class XmlView extends AbstractView {
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void renderMergedOutputModel(@SuppressWarnings("rawtypes") Map model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = null;
		
		try {
			writer = response.getWriter();
			
			StringBuffer xml = new StringBuffer();
		    
			@SuppressWarnings("unchecked")
			List<CommonVO> list = (List<CommonVO>)model.get("list");
			CommonVO vo = (CommonVO)model.get("vo");
			
			xml.append("<xmlView>");
			
			// List
			if(list!=null) {
				for(CommonVO item:list) {
					xml.append("<item>");
					xml.append(item.toXml());
					xml.append("</item>");
				}
			}
			// VO
			else {
				xml.append("<item>");
				xml.append(vo.toXml());
				xml.append("</item>");
			}
		    
			xml.append("</xmlView>");
			
			writer.write(xml.toString());
		} finally {
			if(writer!=null) writer.close();
		}
	}
}
