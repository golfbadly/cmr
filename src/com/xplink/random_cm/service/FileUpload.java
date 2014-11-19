package com.xplink.random_cm.service;

import java.io.IOException;
import java.util.Map;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadException;

import org.apache.commons.fileupload.FileItem;

/**
 * Interface class for upload file.
 * 
 * @author Jitrika Nanta
 */
public interface FileUpload {

	/**
	 * @param mrequest multipart/form-data requests
	 * @param storedDirectory String : store folder
	 * @param parameterFileName Map<String,String> parameterFileName.put("field name", "child
	 *        name");
	 * @return Map : result complete : parameterResult.put("field name", "child name"); incomplete :
	 *         parameterResult.put("field name", "");
	 */
	public Map uploadFile(MultipartFormDataRequest mrequest, String storedDirectory,
			Map<String, String> parameterFileName) throws UploadException, IOException, Exception;

	/**
	 * @param mrequest
	 * @param storedDirectory
	 * @param parameterFileName
	 * @param size
	 * @return
	 */
	public Map uploadFile(MultipartFormDataRequest mrequest, String storedDirectory,
			Map<String, String> parameterFileName, int size) throws UploadException, IOException,
			Exception;

	/**
	 * Change image name of file(default name) and upload image to directory from config file.
	 * 
	 * @param mrequest multipart/form-data requests
	 * @param storedDirectory directory to store
	 * @param tagIdUpload tag id of HTML to upload image
	 * @param imgName image name to store
	 * @return String of image name.
	 */
	public String uploadFileWithChangeName(MultipartFormDataRequest mrequest,
			String storedDirectory, String tagIdUpload, String imgName) throws UploadException,
			IOException, Exception;
	
	
	
	
	
	   public Map uploadFile(Map mrequest, String storedDirectory,
	            Map<String, String> parameterFileName) throws UploadException, IOException, Exception;

	    /**
	     * @param mrequest
	     * @param storedDirectory
	     * @param parameterFileName
	     * @param size
	     * @return
	     */
	    public Map uploadFile(Map mrequest, String storedDirectory,
	            Map<String, String> parameterFileName, int size) throws UploadException, IOException,
	            Exception;

	    
	    
	    /**
	     * Change image name of file(default name) and upload image to directory from config file.
	     * 
	     * @param mrequest multipart/form-data requests
	     * @param storedDirectory directory to store
	     * @param tagIdUpload tag id of HTML to upload image
	     * @param imgName image name to store
	     * @return String of image name.
	     */
	    public String uploadFileWithChangeName(FileItem item,
	            String storedDirectory, String tagIdUpload, String imgName) throws UploadException,
	            IOException, Exception;
}

