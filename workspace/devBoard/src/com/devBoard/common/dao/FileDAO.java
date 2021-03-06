package com.devBoard.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.devBoard.framework.vo.FileVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


/**
 * @Class Name : FileDAO.java
 * @Description : 첨부파일을 처리하는 DAO Class
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

@Repository("fileDAO")
public class FileDAO extends EgovAbstractDAO {

	/**
	 * 첨부파일을 등록한다.
	 * @param vo - 등록할 정보가 담긴 VO
	 * @return 첨부파일번호
	 */
	public int insertComFile(FileVO vo) {
		return (Integer)insert("fileDAO.insertComFile", vo);
	}
	
	/**
	 * 첨부파일상세를 등록한다.
	 * @param vo - 등록할 정보가 담긴 VO
	 */
	public void insertComFileDtl(FileVO vo) {
		insert("fileDAO.insertComFileDtl", vo);
	}
	
	/**
	 * 첨부파일상세 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 첨부파일상세 목록
	 */
	@SuppressWarnings("unchecked")
	public List<FileVO> selectComFileDtlList(FileVO vo) {
		return list("fileDAO.selectComFileDtlList", vo);
	}
	
	/**
	 * 첨부파일상세 정보를 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 조회한 첨부파일 정보
	 */
	public FileVO selectComFileDtl(FileVO vo) {
		return (FileVO) selectByPk("fileDAO.selectComFileDtl", vo);
	}
	
	/**
	 * 첨부파일상세를 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 VO
	 */
	public void deleteComFileDtl(FileVO vo) {
		delete("fileDAO.deleteComFileDtl", vo);
	}
	
	/**
	 * 첨부파일을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 VO
	 */
	public void deleteComFile(FileVO vo) {
		delete("fileDAO.deleteComFile", vo);
	}
	
}
