package com.devBoard.comBbs.service;

import java.util.List;

import com.devBoard.comBbs.vo.ComBbsVO;
import com.devBoard.framework.vo.FileVO;



/**
 * @Class Name : ComBbsService.java
 * @Description : 답변형게시판을 처리하는 Service Class
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
public interface ComBbsService {
	/**
	 * 글 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 */
	public List<ComBbsVO> retrieveComBbsList(ComBbsVO vo) throws Exception;
	
	/**
	 * 글 상세를 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 상세
	 */
	public ComBbsVO retrieveComBbs(ComBbsVO vo) throws Exception;
	
	/**
	 * 글 총 갯수를 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 */
	public int retrieveComBbsListCount(ComBbsVO vo) throws Exception;
	
	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 VO
	 * @param fileList - 등록할 첨부파일 정보가 담긴 fileList
	 * @param userId - 등록자ID
	 * @return 등록 결과
	 * @exception Exception
	 */
	public int insertComBbs(ComBbsVO vo) throws Exception;
	
	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 조회한 글
	 * @exception Exception
	 */
	public ComBbsVO retrieveComBbsView(ComBbsVO vo) throws Exception;
	
	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 VO
	 * @param fileList - 수정할 첨부파일 정보가 담긴 fileList
	 * @param userId - 수정자ID
	 * @return void형
	 * @exception Exception
	 */
	public void updateComBbs(ComBbsVO vo) throws Exception;
	
	/**
	 * 카운터즈가.
	 * @param vo - 수정할 정보가 담긴 VO
	 * @param fileList - 수정할 첨부파일 정보가 담긴 fileList
	 * @param userId - 수정자ID
	 * @return void형
	 * @exception Exception
	 */
	
	public void updateComBbsCnt(ComBbsVO vo) throws Exception;
	
	/**
	 * 코드상세를 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 VO
	 * @return void형
	 */
	public void delteComBbs(ComBbsVO vo) throws Exception;
	
}