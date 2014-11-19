package com.xplink.random_cm.datamodel;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class SlideShowPreview{

	private String slideId;
	private String image;
	private String url;
	private char deleteFlag;
	private Timestamp displayDate;
	private Timestamp expireDate;
	private String createdBy;
	private Timestamp createdDate;
	private String updatedBy;
	private Timestamp updatedDate;
	private Set<SlideShowUploadPreview> slideSet = new HashSet<SlideShowUploadPreview>();

	public String getSlideId() {
		return slideId;
	}

	public void setSlideId(String slideId) {
		this.slideId = slideId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public char getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(char deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Timestamp getDisplayDate() {
		return displayDate;
	}

	public void setDisplayDate(Timestamp displayDate) {
		this.displayDate = displayDate;
	}

	public Timestamp getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Timestamp expireDate) {
		this.expireDate = expireDate;
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

	public Set<SlideShowUploadPreview> getSlideSet() {
		return slideSet;
	}

	public void setSlideSet(Set<SlideShowUploadPreview> slideSet) {
		this.slideSet = slideSet;
	}
}
