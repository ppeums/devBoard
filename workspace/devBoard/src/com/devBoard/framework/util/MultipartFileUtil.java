package com.devBoard.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.devBoard.comBbs.vo.ComBbsVO;
import com.devBoard.common.service.FileService;
import com.devBoard.framework.config.ExXMLConfiguration;
import com.devBoard.framework.exception.ExRuntimeException;
import com.devBoard.framework.vo.FileVO;
import com.devBoard.sample.vo.SampleVO;

/**
 * @Class Name : MultipartFileUtil.java
 * @Description : MultipartFileUtil Class
 * @Modification Information @ @ 수정일 수정자 수정내용 @ ---------- ---------
 *               ------------------------------- @ 2012. 12. 11. 송제승 최초생성
 * 
 * @author 송제승
 * @since 2012. 12. 11.
 * @version 1.0
 * @see Copyright (C) by Sinsuldong All right reserved.
 */
public class MultipartFileUtil {
	public static final int BUFFER_SIZE = 8192;
	public static final String SEPERATOR = "/";

	private static Logger logger = Logger.getLogger(MultipartFileUtil.class);

	/**
	 * request에서 파일 정보를 추출한다.
	 * 
	 * @param request
	 *            HttpServletRequest 객체
	 * @param systemCd
	 *            시스템 코드
	 * @return FileVO List
	 */
	public static List<FileVO> getFileList(MultipartHttpServletRequest request,
			String systemCd) throws Exception {
		return getFileList(request, systemCd, true);
	}

	/**
	 * request에서 파일 정보를 추출한다.
	 * 
	 * @param request
	 *            HttpServletRequest 객체
	 * @param systemCd
	 *            시스템 코드
	 * @param addTimeStamp
	 *            파일 명 뒤에 timestamp 값을 붙일것인지 여부
	 * @return FileVO List
	 */
	public static List<FileVO> getFileList(MultipartHttpServletRequest request,
			String systemCd, boolean addTimeStamp) throws Exception {
		ExXMLConfiguration conf = ExXMLConfiguration.getInstance();
		// MultipartHttpServletRequest multiRequest =
		// (MultipartHttpServletRequest) request;

		List<FileVO> fileList = new ArrayList<FileVO>();
		List<MultipartFile> files = request.getFiles("upfile");
		logger.debug("파일즈 크기 : " + files.size());

		// 파일 추출
		// Map<String, MultipartFile> files = request.getFileMap();

		// 해킹방지 : systemCd에 대한 경로순회 문자열 필터링
		if (systemCd.indexOf("..") != -1 || systemCd.indexOf("&") != -1) {
			throw new ExRuntimeException("경로순회에 의한 해킹 시도가 발생하였습니다.");
		}

		String rootPath = System.getProperty("uploadpath");
		// String directory = rootPath + SEPERATOR + systemCd;
		String directory = rootPath + "\\" + systemCd;

		// 2012.08.14 경로 미 존재 시 생성함.
		File temp = new File(directory);
		if (!temp.exists()) {
			temp.mkdirs();
		}

		// Iterator<Entry<String, MultipartFile>> itr =
		// files.entrySet().iterator();
		MultipartFile file;
		String filenameToSave;
		String originalFilename;

		for (int i = 0; i < files.size(); i++) {
			file = files.get(i);
			originalFilename = files.get(i).getOriginalFilename();

			if (!"".equals(originalFilename)) {
				// 웹보안(확장자 체크)
				if (!checkFileExt(originalFilename)) {
					throw new ExRuntimeException("업로드할 수 없는 파일입니다.");
				}

				// 저장되는 파일명
				if (addTimeStamp) {
					filenameToSave = getFilenameToSave(directory,
							originalFilename);
				} else {
					filenameToSave = originalFilename;
				}

				// 파일 저장
				file.transferTo(new File(directory + SEPERATOR + filenameToSave));

				// 파일 정보를 VO에 설정
				FileVO fileVO = new FileVO();

				fileVO.setFileStrePath(directory);
				fileVO.setFileStreNm(filenameToSave);
				fileVO.setFileStreOriNm(originalFilename);
				fileVO.setFileSize(file.getSize());
				fileList.add(fileVO);

				logger.debug("File uploaded : " + directory + SEPERATOR
						+ filenameToSave);
			}
		}
		logger.debug("파일리스트 크기 : " + fileList.size());
		return fileList;
	}

	/**
	 * 파일 중복 처리
	 */
	private static String getFilenameToSave(String directory, String fileName) {
		String time = DateUtil.dateToString(Calendar.getInstance().getTime(),
				"yyyyMMddHHmmssSSS");
		String fileNamePart;
		String fileExtPart;
		int pointInx = fileName.lastIndexOf(".");

		// 확장자가 없는 경우
		if (pointInx == -1) {
			fileNamePart = fileName;
			fileExtPart = "";
		}
		// 확장자가 있는 경우
		else {
			fileNamePart = fileName.substring(0, pointInx);
			fileExtPart = fileName.substring(pointInx);
		}

		File file = new File(directory + SEPERATOR + fileNamePart + "[" + time
				+ "]" + fileExtPart);

		// 파일 중복 시 index 추가
		if (file.exists()) {
			int i = 1;

			// 무한 루핑 방지를 위해 100까지만 카운팅
			while (i <= 100) {
				file = new File(directory + SEPERATOR + fileNamePart + "["
						+ time + "-" + i + "]" + fileExtPart);

				if (!file.exists())
					break;

				i++;
			}
		}

		return file.getName();
	}

	/**
	 * 이미지에 대한 미리보기 기능을 제공한다.
	 * 
	 * mimeType의 경우는 JSP 상에서 다음과 같이 얻을 수 있다.
	 * getServletConfig().getServletContext().getMimeType(name);
	 * 
	 * @param response
	 * @param where
	 * @param serverSubPath
	 * @param physicalName
	 * @param mimeType
	 * @throws Exception
	 */
	public static void viewFile(HttpServletResponse response,
			String serverSubPath, String physicalName, String mimeTypeParam)
			throws Exception {
		String mimeType = mimeTypeParam;
		String downFileName = serverSubPath + SEPERATOR + physicalName;

		File file = new File(downFileName);

		if (!file.exists()) {
			throw new FileNotFoundException(downFileName);
		}

		if (!file.isFile()) {
			throw new FileNotFoundException(downFileName);
		}

		byte[] b = new byte[BUFFER_SIZE];

		if (mimeType == null) {
			mimeType = "application/octet-stream;";
		}

		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", "filename=image;");

		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;

		try {
			fin = new BufferedInputStream(new FileInputStream(file));
			outs = new BufferedOutputStream(response.getOutputStream());

			int read = 0;

			while ((read = fin.read(b)) != -1) {
				outs.write(b, 0, read);
			}
		} finally {
			if (outs != null) {
				outs.close();
			}

			if (fin != null) {
				fin.close();
			}
		}
	}

	/**
	 * 파일 확장자를 체크한다.
	 * 
	 * @param fileName
	 *            파일명
	 * @return 체크 결과
	 * @throws ExRuntimeException
	 */
	private static boolean checkFileExt(String fileName)
			throws ExRuntimeException {
		ExXMLConfiguration conf = ExXMLConfiguration.getInstance();
		String fileExt = FileUtil.getFileExt(fileName);
		String policy = conf.getString("file.upload.policy");
		boolean result;

		if ("allow".equals(policy)) {
			result = false;
			String[] allowExtList = conf.getStringArray("file.upload." + policy
					+ "-ext");

			for (String allow : allowExtList) {
				if (fileExt.compareToIgnoreCase(allow) == 0) {
					result = true;
					break;
				}
			}
		} else if ("deny".equals(policy)) {
			result = true;
			String[] denyExtList = conf.getStringArray("file.upload." + policy
					+ "-ext");

			for (String deny : denyExtList) {
				if (fileExt.compareToIgnoreCase(deny) == 0) {
					result = false;
					break;
				}
			}
		} else {
			throw new ExRuntimeException("업로드 정책이 올바르지 않습니다.");
		}

		return result;
	}

	/**
	 * 업로드 허용 확장자 목록을 반환한다.
	 * 
	 * @return 업로드 허용 확장자 목록
	 */
	public static String getUploadAllowExtList() {
		StringBuffer result = new StringBuffer();

		String[] allowExtList = ExXMLConfiguration.getInstance()
				.getStringArray("file.upload.allow-ext");

		for (int i = 0; i < allowExtList.length; i++) {
			if (i != 0)
				result.append(",");

			result.append(allowExtList[i]);
		}

		return result.toString();
	}

	/**
	 * 업로드 거부 확장자 목록을 반환한다.
	 * 
	 * @return 업로드 거부 확장자 목록
	 */
	public static String getUploadDenyExtList() {
		StringBuffer result = new StringBuffer();

		String[] denyExtList = ExXMLConfiguration.getInstance().getStringArray(
				"file.upload.deny-ext");

		for (int i = 0; i < denyExtList.length; i++) {
			if (i != 0)
				result.append(",");

			result.append(denyExtList[i]);
		}

		return result.toString();
	}

	/**
	 * 첨부파일을 등록한다.
	 * 
	 * @param fileService
	 *            - 파일처리를 위한 Service
	 * @param fileList
	 *            - 파일목록
	 * @param userId
	 *            - 등록자 아이디
	 * @return 첨부파일 번호
	 * @throws Exception
	 */
	public static int insertComFileDtl(FileService fileService,
			List<FileVO> fileList, String userId) throws Exception {
		int comFileSeq = 0;
		if (fileList.size() != 0) {
			// 첨부파일 정보를 DB에 등록한다.
			FileVO fileVO = new FileVO();
			fileVO.setRgstId(userId);
			comFileSeq = fileService.insertComFile(fileVO);
			// 첨부파일 상세정보를 DB에 등록한다.
			for (int i = 0; i < fileList.size(); i++) {
				FileVO fileVOSub = fileList.get(i);
				fileVOSub.setComFileSeq(comFileSeq); // 첨부파일번호
				fileVOSub.setRgstId(userId); // 등록자ID
				fileVOSub.setFileExt(FileUtil.getFileExt(fileVOSub
						.getFileStreNm()));
				fileVOSub.setFileCd("");
				fileVOSub.setFileOrder(1);
				fileService.insertComFileDtl(fileVOSub);
			}
		}
		return comFileSeq;
	}

	/**
	 * 첨부파일을 수정한다.
	 * 
	 * @param fileService
	 *            - 파일처리를 위한 Service
	 * @param fileList
	 *            - 파일목록
	 * @param userId
	 *            - 등록자 아이디
	 * @param vo
	 *            - 수정 정보를 가진 VO
	 * @param deleteYn
	 *            - 첨부파일 삭제여부
	 * @param excelYn
	 *            - 처리구분
	 * @return
	 * @throws Exception
	 */
	public static int updateComFileDtl(FileService fileService,
			List<FileVO> fileList, String userId, Object vo, Boolean deleteYn)
			throws Exception {
		int comFileSeq = ((SampleVO) vo).getComFileSeq();

		if (fileList.size() == 0) {

		} else {
			if (comFileSeq == 0) {
				// 첨부파일 정보를 DB에 등록한다.
				FileVO fileVO = new FileVO();
				fileVO.setRgstId(userId);
				comFileSeq = fileService.insertComFile(fileVO);
			}
			deleteComFileDtl(fileService, comFileSeq, deleteYn);
			// 첨부파일 상세정보를 DB에 등록한다.
			for (int i = 0; i < fileList.size(); i++) {
				FileVO fileVOSub = fileList.get(i);
				fileVOSub.setComFileSeq(comFileSeq); // 첨부파일번호
				fileVOSub.setRgstId(userId); // 등록자ID
				fileVOSub.setFileExt(FileUtil.getFileExt(fileVOSub
						.getFileStreNm()));
				fileVOSub.setFileCd("");
				fileVOSub.setFileOrder(1);
				fileService.insertComFileDtl(fileVOSub);
			}
		}

		return comFileSeq;
	}
	
	/**
	 * 답글게시판 - 첨부파일을 수정한다.
	 * @param fileService
	 * @param fileList
	 * @param userId
	 * @param vo
	 * @param deleteYn
	 * @return
	 * @throws Exception
	 */
	public static int updateComFileDtl2(FileService fileService,
			List<FileVO> fileList, String userId, Object vo, Boolean deleteYn)
			throws Exception {
		int comFileSeq = ((ComBbsVO) vo).getComFileSeq();

		if (fileList.size() == 0) {

		} else {
			if (comFileSeq == 0) {
				// 첨부파일 정보를 DB에 등록한다.
				FileVO fileVO = new FileVO();
				fileVO.setRgstId(userId);
				comFileSeq = fileService.insertComFile(fileVO);
			}
			deleteComFileDtl(fileService, comFileSeq, deleteYn);
			// 첨부파일 상세정보를 DB에 등록한다.
			for (int i = 0; i < fileList.size(); i++) {
				FileVO fileVOSub = fileList.get(i);
				fileVOSub.setComFileSeq(comFileSeq); // 첨부파일번호
				fileVOSub.setRgstId(userId); // 등록자ID
				fileVOSub.setFileExt(FileUtil.getFileExt(fileVOSub
						.getFileStreNm()));
				fileVOSub.setFileCd("");
				fileVOSub.setFileOrder(1);
				fileService.insertComFileDtl(fileVOSub);
			}
		}

		return comFileSeq;
	}
	
	/**
	 * 첨부파일 리스트를 삭제한다.
	 * @param fileService
	 * @param comFileSeq
	 * @param deleteYn
	 * @throws Exception
	 */
	public static void deleteComFileDtl(FileService fileService,
			int comFileSeq, Boolean deleteYn) throws Exception {
		FileVO fileVO11 = new FileVO();
		fileVO11.setComFileSeq(comFileSeq);
		List<FileVO> fileList1 = fileService.retrieveComFileDtlList(fileVO11);
		for (int i = 0; i < fileList1.size(); i++) {
			FileVO fileVO = new FileVO();
			fileVO = fileList1.get(i);
			FileVO deleteFile = fileService.retrieveComFileDtl(fileVO);
			if (deleteYn) {
				FileUtil.deleteFile(deleteFile.getFileStrePath() + "/"
						+ deleteFile.getFileStreNm());
			}
			fileService.deleteComFileDtl(deleteFile); // 디비삭제
		}

	}
	
	/**
	 * 첨부파일 하나를 삭제한다.
	 * @param fileService
	 * @param comFileDtlSeq
	 * @param deleteYn
	 * @throws Exception
	 */
	@RequestMapping("/framework/deleteEachComFileDtl.do")
	@ResponseBody
	public static void deleteEachComFileDtl(FileService fileService, 
			int comFileDtlSeq, Boolean deleteYn) throws Exception {
		FileVO fileVO = new FileVO();
		fileVO.setComFileDtlSeq(comFileDtlSeq);
		FileVO deleteFile = fileService.retrieveComFileDtl(fileVO);
		if (deleteYn) {
			FileUtil.deleteFile(deleteFile.getFileStrePath() + "/"
					+ deleteFile.getFileStreNm());
		}
		fileService.deleteComFileDtl(deleteFile);
	}
}
