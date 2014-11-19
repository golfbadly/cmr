package com.xplink.random_cm.datamodel;

import java.sql.Timestamp;

public class SlideShowUploadPreview {

	private int uploadID;
	private transient SlideShowPreview slideRef;
	private String uploadType;
	private String fileName;
	private String fileDESC;
	private String createdBy;
	private Timestamp createdDate;
	private String updatedBy;
	private Timestamp updatedDate;

	public int getUploadID() {
		return uploadID;
	}

	public void setUploadID(int uploadID) {
		this.uploadID = uploadID;
	}

	public SlideShowPreview getSlideRef() {
		return slideRef;
	}

	public void setSlideRef(SlideShowPreview slideRef) {
		this.slideRef = slideRef;
	}

	public String getUploadType() {
		return uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDESC() {
		return fileDESC;
	}

	public void setFileDESC(String fileDESC) {
		this.fileDESC = fileDESC;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
}
