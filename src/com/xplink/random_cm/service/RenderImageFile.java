package com.xplink.random_cm.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.thebuzzmedia.imgscalr.Scalr;

public class RenderImageFile {
	private static final Logger logger = LogManager
			.getLogger(RenderImageFile.class);
	private String width;
	private String height;

	public void renderSizeImage(String imgPath, String fileType)

	{
		try {

			// Read from a file
			FileInputStream fileInputStream = new FileInputStream(imgPath);
			Iterator readers = ImageIO.getImageReadersBySuffix(fileType
					.toUpperCase());
			ImageReader imageReader = (ImageReader) readers.next();
			ImageInputStream imageInputStream = ImageIO
					.createImageInputStream(fileInputStream);
			imageReader.setInput(imageInputStream, false);

			imageReader
					.addIIOReadProgressListener(new MyIIOReadProgressListener());

			BufferedImage imageBuffered = imageReader.read(0);

			int type = imageBuffered.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
					: imageBuffered.getType();
			imageBuffered = resizeImage(imageBuffered);

			ImageIO.write(imageBuffered, fileType, new File(imgPath));

		} catch (IOException e) {
			logger.debug(e.getMessage());
		}
	}

	public BufferedImage resizeImage(BufferedImage originalImage) {
		BufferedImage resizedImage = null;
		int IMG_WIDTH = 0;
		int IMG_HEIGHT = 0;


			IMG_WIDTH = Integer.parseInt(width);
			IMG_HEIGHT = Integer.parseInt(height);

			IMG_WIDTH = resizeWidthCondition(originalImage.getWidth(),
					originalImage.getHeight(), IMG_WIDTH, IMG_HEIGHT);
			logger.debug("IMG_WIDTH " +IMG_WIDTH);
			
			IMG_HEIGHT = resizeHeightCondition(originalImage.getWidth(),
					originalImage.getHeight(), IMG_WIDTH, IMG_HEIGHT);
			logger.debug("IMG_HEIGHT "+IMG_HEIGHT);
		
		resizedImage = Scalr.resize(originalImage, IMG_WIDTH, IMG_HEIGHT);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(resizedImage, null, IMG_WIDTH, IMG_HEIGHT);
		g.dispose();

		return resizedImage;
	}

	public int resizeWidthCondition(int originnalWidth, int originalHeight,
			int IMG_WIDTH, int IMG_HEIGHT) {
		if (originnalWidth > IMG_WIDTH || originalHeight > IMG_HEIGHT) {
			if (originnalWidth > originalHeight) {
				IMG_WIDTH = getLastWidth(originnalWidth, originnalWidth,
						IMG_WIDTH);
			} else if (originnalWidth < originalHeight) {
				IMG_WIDTH = getLastWidth(originnalWidth, originalHeight,
						IMG_HEIGHT);
			} else {
				IMG_WIDTH = getLastWidth(originnalWidth, originnalWidth,
						IMG_HEIGHT);
			}
		} else {
			IMG_WIDTH = originnalWidth;
		}
		return IMG_WIDTH;
	}

	public int resizeHeightCondition(int originnalWidth, int originalHeight,
			int IMG_WIDTH, int IMG_HEIGHT) {
		if (originnalWidth > IMG_WIDTH || originalHeight > IMG_HEIGHT) {
			if (originnalWidth > originalHeight) {
				IMG_HEIGHT = getLastHeight(originalHeight, originnalWidth,
						IMG_WIDTH);
			} else if (originnalWidth < originalHeight) {
				IMG_HEIGHT = getLastHeight(originalHeight, originalHeight,
						IMG_HEIGHT);
			} else {
				IMG_HEIGHT = getLastHeight(originalHeight, originnalWidth,
						IMG_HEIGHT);
			}
		} else {
			IMG_HEIGHT = originalHeight;
		}
		return IMG_HEIGHT;
	}

	public int getLastWidth(int width, int path, int ratio) {
		int result = (width * ratio) / path;
		return result;

	}

	public int getLastHeight(int height, int path, int ratio) {
		int result = (height * ratio) / path;
		return result;

	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

}
