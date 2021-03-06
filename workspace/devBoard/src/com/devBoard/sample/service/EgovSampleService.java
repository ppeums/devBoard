package com.devBoard.sample.service;

import java.util.List;

import com.devBoard.framework.vo.FileVO;
import com.devBoard.sample.vo.ComCommentVO;
import com.devBoard.sample.vo.SampleVO;


/**
 * @Class Name : EgovSampleService.java
 * @Description : 샘플게시판을 처리하는 Service Class
 * @Modification Information  
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012.12.11  송제승     최초생성
 * 
 * @author 송제승
 * @since 2012.12.11
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
public interface EgovSampleService {
	
	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public int insertSample(SampleVO vo, List<FileVO> fileList, String userId) throws Exception;
	
	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 VO
	 * @return void형
	 */
	public void updateSample(SampleVO vo);
	
	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 VO
	 * @return void형
	 */
	public void deleteSample(SampleVO vo);
	
	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 조회한 글
	 * @exception Exception
	 */
	public SampleVO retrieveSample(SampleVO vo) throws Exception;
	
	/**
	 * 글 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 */
	public List<SampleVO> retrieveSampleList(SampleVO vo);
	
	/**
	 * 글 총 갯수를 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 */
	public int retrieveSampleListCount(SampleVO vo);
	
	/**
	 * 댓글. 목록을 조회
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 */
	public List<ComCommentVO> retrieveComCommentList(ComCommentVO vo) throws Exception;
	
	/**
	 * 댓글. 목록건수를 조회
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 */
	public int retrieveComCommentListCount(ComCommentVO vo) throws Exception;
	
	/**
	 * 댓글.등록
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 */
	public int insertCommentList(ComCommentVO vo) throws Exception;
	
	/**
	 * 댓글.삭제.
	 * @param vo - 삭제할 정보가 담긴 VO
	 * @return void형
	 */
	public void delteLibFreeNoticeComment(ComCommentVO vo) throws Exception;
	
	/**
	 * 댓글의 개수를 구한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int commentCount(SampleVO vo) throws Exception;
	
	public int commentCount(ComCommentVO vo) throws Exception;

}
