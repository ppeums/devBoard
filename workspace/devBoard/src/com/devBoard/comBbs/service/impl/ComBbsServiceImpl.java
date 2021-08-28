package com.devBoard.comBbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.devBoard.comBbs.dao.ComBbsDAO;
import com.devBoard.comBbs.service.ComBbsService;
import com.devBoard.comBbs.vo.ComBbsVO;
import com.devBoard.common.service.FileService;
import com.devBoard.framework.util.MultipartFileUtil;
import com.devBoard.framework.vo.FileVO;
import com.devBoard.sample.vo.ComCommentVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Class Name : ComBbsServiceImpl.java
 * @Description : 게시판을 처리하는 Business Implement Class
 * @Modification Information  
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2014. 2. 6. 송제승          최초생성
 * 
 * @author 송제승
 * @since 2014. 2. 6.
 * @version 1.0
 * @see
 * 
 *  Copyright (C) 2014 by Sinsuldong All right reserved.
 */
@Service("comBbsService")
public class ComBbsServiceImpl extends AbstractServiceImpl implements ComBbsService {

	/** 게시판을 처리하는 DAO Class */
	@Resource(name="comBbsDAO")
	private ComBbsDAO comBbsDAO;
	
	/** 첨부파일 처리를 위한  service */
	@Resource(name = "fileService")
	private FileService fileService;
	
	@Override
	public List<ComBbsVO> retrieveComBbsList(ComBbsVO vo) throws Exception {
		return comBbsDAO.selectComBbsList(vo);
	}
	
	public ComBbsVO retrieveComBbs(ComBbsVO vo) throws Exception {
		ComBbsVO resultVO = comBbsDAO.retrieveComBbs(vo);
		
		if(resultVO == null) {
			throw processException("error.common.retrieve");
		}
		
		return resultVO;
	}
	
	@Override
	public int retrieveComBbsListCount(ComBbsVO vo) throws Exception {
		return comBbsDAO.selectComBbsListCount(vo);
	}
	
	public int insertComBbs(ComBbsVO vo) throws Exception {
		int id = comBbsDAO.insertComBbs(vo);
		return id;
	}

	@Override
	public ComBbsVO retrieveComBbsView(ComBbsVO vo) throws Exception {
		ComBbsVO resultVO = comBbsDAO.selectComBbsView(vo);
		
		if(resultVO == null) {
			throw processException("error.common.retrieve");
		}
		return resultVO;
	}

	@Override
	public void updateComBbs(ComBbsVO vo)
			throws Exception {
		comBbsDAO.updateComBbs(vo);
	}

	@Override
	public void updateComBbsCnt(ComBbsVO vo)
		throws Exception {
		comBbsDAO.updateComBbsCnt(vo);
	}
	@Override
	public void delteComBbs(ComBbsVO vo) throws Exception {
		
		ComCommentVO comcommentVO = new ComCommentVO();
		comBbsDAO.deleteComBbs(vo);
	}

}
