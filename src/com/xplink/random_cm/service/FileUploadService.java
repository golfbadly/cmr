package com.xplink.random_cm.service;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Class for help upload image
 * 
 * @author Jitrika Nanta
 */
public class FileUploadService {

	/**
	 * @param path String : http://localhost/Content/image_name.jpg
	 * @return String : image_name.jpg
	 */
	public static String splitImageNameBySlash(String path) throws Exception {
		String[] split = path.split("/");
		int size = split.length;
		String imageName = split[size - 1];
		return imageName;
	}

	/**
	 * @param fullName String : image_name.jpg
	 * @return String : image_name
	 */
	public static String splitImageName(String fullName) throws Exception {
		int index = fullName.indexOf(".");
		String imageName = fullName.substring(0, index);
		return imageName;
	}

	/**
	 * @param fullName String : image_name.jpg
	 * @return String : jpg
	 */
	public static String splitImageType(String fullName) throws Exception {
		int beginIndex = fullName.lastIndexOf(".");
		int endIndex = fullName.length();
		String type = fullName.substring(beginIndex + 1, endIndex);
		return type;
	}

	/**
	 * @param oldPathFileName String : .../Image/old_name.jpg
	 * @param newPathFileName String : .../Image/new_name.jpg
	 * @return String : new_name.jpg
	 */
	public static String renameFile(String oldPathFileName, String newPathFileName)
			throws Exception {

		String newFileName = null;
		File file = new File(oldPathFileName);
		boolean fileResult = file.isFile();
		if (fileResult == true) {
			file.renameTo(new File(newPathFileName));
			newFileName = newPathFileName;
		}
		return newFileName;
	}

	/**
	 * @param pathFile String : .../Image/old_name.jpg
	 * @return boolean complete : return true incomplete : return false
	 */
	public static boolean deleteFile(String pathFile) throws Exception {
		try {
			File file = new File(pathFile);
			file.delete();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	public static BufferedImage scaleImage(BufferedImage bufferedImage, int size) throws Exception {

		double boundSize = size;
		int origWidth = bufferedImage.getWidth();
		int origHeight = bufferedImage.getHeight();

		double scale;

		if (origHeight > origWidth)
			scale = boundSize / origHeight;
		else
			scale = boundSize / origWidth;

		/*
		 * Don't scale up small images.
		 */
		if (scale >= 1.0)
			return (bufferedImage);

		int scaledWidth = (int) (scale * origWidth);
		int scaledHeight = (int) (scale * origHeight);

		Image scaledImage = bufferedImage.getScaledInstance(scaledWidth, scaledHeight,
				Image.SCALE_SMOOTH);

		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D g = scaledBI.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(scaledImage, 0, 0, null);
		g.dispose();

		return (scaledBI);
	}
}
