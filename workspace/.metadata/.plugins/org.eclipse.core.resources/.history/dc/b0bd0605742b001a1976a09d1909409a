package com.devBoard.common.service;

import java.util.List;

import com.devBoard.framework.vo.FileVO;

/**
 * @Class Name : FileService.java
 * @Description : FileService.java Class
 * @Modification Information
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2013. 8. 6.  송제승     최초생성
 * 
 * @author 송제승
 * @since 2013. 8. 6.
 * @version 1.0
 * @see
 * 
 * Copyright (C) 2013 by Kap All right reserved.
 */
public interface FileService {
	/**
	 * 첨부파일을 등록한다.
	 * @param vo - 등록할 정보가 담긴 VO
	 * @return 첨부파일번호
	 * @exception Exception
	 */
	public int insertComFile(FileVO vo) throws Exception;
	
	/**
	 * 첨부파일상세를 등록한다.
	 * @param vo - 등록할 정보가 담긴 VO
	 * @exception Exception
	 */
	public void insertComFileDtl(FileVO vo) throws Exception;
	
	/**
	 * 첨부파일상세 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 첨부파일상세 목록
	 */
	public List<FileVO> retrieveComFileDtlList(FileVO vo) throws Exception;
	
	/**
	 * 첨부파일상세 정보를 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 조회한 첨부파일 정보
	 */
	public FileVO retrieveComFileDtl(FileVO vo) throws Exception;
	
	/**
	 * 첨부파일상세를 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 VO
	 */
	public void deleteComFileDtl(FileVO vo) throws Exception;
	
	/**
	 * 첨부파일을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 VO
	 */
	public void deleteComFile(FileVO vo) throws Exception;
	
}
