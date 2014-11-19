package com.xplink.random_cm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.SocketException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;



public class AttachmentController extends SimpleFormController {
	private static final Logger logger = LogManager
			.getLogger(AttachmentController.class);

	private String workspacePath;

	private String noPhotoPath;

	private int bufferSize;

	private List imageExtensionList;

	/**
	 * set CommandClass set CommandName
	 */
//	public AttachmentController() {
//		setCommandClass(AddressViewModel.class);
//		setCommandName("addressViewModel");
//	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String path = request.getParameter("path");
		String fileName = getFileName(path);
		logger.debug("path:" + path + ", File Name : " + fileName);
		String pathFile = null;
		if (path != null && !"".equals(path.trim())) {
			pathFile = workspacePath + path;
		} else {
			logger.info("path for getting image is null.");
			return goToFileNotFoundPage();
		}
		// String fullpath = getWorkspacePath() + "/" + path;
		File image = new File(pathFile);
		if (!image.exists()) {
			logger.warn("The image file does not exists:" + pathFile);
			boolean isImage = isImageFile(fileName);
			if(isImage){
				pathFile = getNoPhotoPath();
				image = new File(pathFile);
			}else{
				return goToFileNotFoundPage();
			}
			
		}

		FileInputStream is = new FileInputStream(image);
		ServletOutputStream os = response.getOutputStream();
		try {
			response.reset();
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", " filename=" + fileName);

			// Simple
			// byte buf[] = new byte[1024 * 64];
			// int len;
			// while ((len = is.read(buf)) > 0) {
			// os.write(buf, 0, len);
			// }

			// Filechannel
			// FileChannel in = is.getChannel();
			// ByteBuffer buffer = ByteBuffer.allocate(1024 * 64);
			// int len;
			// while ((len = in.read(buffer)) > 0) {
			// buffer.flip();
			// os.write(buffer.array(), 0, len);
			// buffer.clear();
			// }

			// MappedByteBuffer
			FileChannel in = is.getChannel();
			long fileSize = in.size();
			if (fileSize > 0) {
				int size = getBufferSize();
				int sizeArray = 0;
				if (fileSize < size) {
					sizeArray = (int) fileSize;
				} else {
					sizeArray = size;
				}
				MappedByteBuffer buffer = in.map(FileChannel.MapMode.READ_ONLY,
						0, fileSize);
				byte[] buf = new byte[sizeArray];
				int nGet;
				while (buffer.hasRemaining()) {
					nGet = Math.min(buffer.remaining(), size);
					buffer.get(buf, 0, nGet);
					os.write(buf, 0, nGet);
				}
			} else {
				logger.warn("File:" + pathFile + ", size:" + fileSize);
			}

	

		} catch (SocketException e) {
			logger.error("get image path:" + pathFile, e);
		} catch (Exception e) {
			logger.error("get image path:" + pathFile, e);
		} finally {
			is.close();
			os.close();
		}

		// }
		// else {
		// Profiler.log(Profiler.WHAT2EAT_PROFILER,
		// "Attachment page, START query all subcategory order by category id :
		// "
		// + Profiler.dateFormat.format(new Date()));
		// logger.info("path for getting image is null.");
		// }
		return null;
	}

	private boolean isImageFile(String fileName) {
		boolean isImage = false;
		if (fileName != null && imageExtensionList != null) {
			String[] nameArr = fileName.split("\\p{Punct}");
			if (nameArr != null && nameArr.length > 1) {
				String extension = nameArr[nameArr.length - 1];
				logger.debug("file name:"+fileName+", extension:"+extension);
				int size = imageExtensionList.size();
				for (int i = 0; i < size; i++) {
					String imageExtension = (String) imageExtensionList.get(i);
					if (extension.equalsIgnoreCase(imageExtension)) {
						isImage = true;
						logger.debug("This file is image.");
						break;
					}
				}
			}
		}
		return isImage;
	}

	private String getFileName(String fileWithPath) {
		String fileNameWithPath[] = fileWithPath.split("/");
		String fileName = fileNameWithPath[fileNameWithPath.length - 1];
		return fileName;
	}
	
	private ModelAndView goToFileNotFoundPage(){
		logger.debug("Redirect to fileNotFound.html");
		return new ModelAndView("error-fileNotFound");
	}

	/**
	 * @return the workspacePath
	 */
	public String getWorkspacePath() {
		return workspacePath;
	}

	/**
	 * @param workspacePath
	 *            the workspacePath to set
	 */
	public void setWorkspacePath(String workspacePath) {
		this.workspacePath = workspacePath;
	}

	/**
	 * @return the bufferSize
	 */
	public int getBufferSize() {
		return bufferSize;
	}

	/**
	 * @param bufferSize
	 *            the bufferSize to set
	 */
	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	/**
	 * @return the noPhotoPath
	 */
	public String getNoPhotoPath() {
		return noPhotoPath;
	}

	/**
	 * @param noPhotoPath
	 *            the noPhotoPath to set
	 */
	public void setNoPhotoPath(String noPhotoPath) {
		this.noPhotoPath = noPhotoPath;
	}

	/**
	 * @return the imageExtensionList
	 */
	public List getImageExtensionList() {
		return imageExtensionList;
	}

	/**
	 * @param imageExtensionList
	 *            the imageExtensionList to set
	 */
	public void setImageExtensionList(List imageExtensionList) {
		this.imageExtensionList = imageExtensionList;
	}
}
