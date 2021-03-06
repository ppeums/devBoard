package com.devBoard.framework.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import org.apache.log4j.Logger;

/**  
 * @Class Name : ImageUtil.java
 * @Description : ImageUtil Class
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
public class ImageUtil {
	private final static Logger logger = Logger.getLogger(ImageUtil.class);	// Logger 처리
	
	/**
	 * 원본 이미지를 설정된 비율에 맞게 JPG로 축소 및 확대한다.
	 * 
	 * @param sourcePath 원본 이미지 경로
	 * @param targetPath Resize된 이미지 경로
	 * @param 비율(%)
	 */
	public static String resizeToJpg(String sourcePath, int percent) {
		String targetPath = "";
		int width = 0;
		int height = 0;
		
		try {
			targetPath = sourcePath.substring(0, sourcePath.lastIndexOf(".")) + "_" + percent + ".jpg";
			ParameterBlock pb = new ParameterBlock();
			pb.add(sourcePath);
						
			RenderedOp ro = JAI.create("fileload", pb);
			
			BufferedImage bi = ro.getAsBufferedImage();
			width = bi.getWidth();
			height = bi.getHeight();
			
			logger.debug(sourcePath);
			logger.debug("source size : " + width + "px, " + height + "px");
			
			width = width*percent/100;
			height = height*percent/100;
			
			logger.debug(targetPath);
			logger.debug("target size : " + width + "px, " + height + "px");
			
			BufferedImage thumb = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			
			Graphics2D g = thumb.createGraphics();
			g.drawImage(bi, 0, 0, width, height, null); // 정해진 버퍼 사이즈에 맞춰서 드로우
			
			File file = new File(targetPath);
			ImageIO.write(thumb, "jpg", file);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return targetPath;
	}
	
	/**
	 * 원본 이미지를 설정된 width, height에 맞게 JPG로 축소 및 확대한다.
	 * 
	 * @param sourcePath 원본 이미지 경로
	 * @param targetPath Resize된 이미지 경로
	 * @param 비율(%)
	 */
	public static String resizeToJpg(String sourcePath, int targetWidth, int targetHeight) {
		String targetPath = "";
		
		targetPath = sourcePath.substring(0, sourcePath.lastIndexOf(".")) + "_" + targetWidth + "X" + targetHeight + ".jpg";
		resizeToJpg(sourcePath, targetPath, targetWidth, targetHeight);
		
		return targetPath;
	}
	
	/**
	 * 원본 이미지를 설정된 width, height에 맞게 JPG로 축소 및 확대한다.
	 * 
	 * @param sourcePath 원본 이미지 경로
	 * @param targetPath Resize된 이미지 경로
	 * @param 비율(%)
	 */
	public static void resizeToJpg(String sourcePath, String targetPath, int targetWidth, int targetHeight) {
		try {
			ParameterBlock pb = new ParameterBlock();
			pb.add(sourcePath);
			
			RenderedOp ro = JAI.create("fileload", pb);
			
			BufferedImage bi = ro.getAsBufferedImage();
			
			logger.debug(sourcePath);
			logger.debug("source size : " + bi.getWidth() + "px, " + bi.getHeight() + "px");
			
			logger.debug(targetPath);
			logger.debug("target size : " + targetWidth + "px, " + targetHeight + "px");
			
			BufferedImage thumb = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			
			Graphics2D g = thumb.createGraphics();
			g.drawImage(bi, 0, 0, targetWidth, targetHeight, null); // 정해진 버퍼 사이즈에 맞춰서 드로우
			
			File file = new File(targetPath);
			ImageIO.write(thumb, "jpg", file);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/*
	public static void main(String[] args) {
		String sourcePath = "C:/Temp/test.jpg";
		String targetPath = "";
			
		targetPath = ImageUtil.resizeToJpg(sourcePath, 20);
		
		System.out.println(targetPath);
		
		targetPath = ImageUtil.resizeToJpg(sourcePath, 100, 200);
		
		System.out.println(targetPath);
	}
	*/

}
