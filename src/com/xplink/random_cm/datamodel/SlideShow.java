package com.xplink.random_cm.datamodel;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class SlideShow {
	private int slideId;
	private String image;
	private String url;
	private char deleteFlag;
	private Timestamp displayDate;
	private Timestamp expireDate;
	private String createdBy;
	private Timestamp createdDate;
	private String updatedBy;
	private Timestamp updatedDate;
	private  Set<SlideShowUpload> slideSet = new HashSet<SlideShowUpload>();
	
	public int getSlideId() {
		return slideId;
	}
	public void setSlideId(int slideId) {
		this.slideId = slideId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public Set<SlideShowUpload> getSlideSet() {
		return slideSet;
	}
	public void setSlideSet(Set<SlideShowUpload> slideSet) {
		this.slideSet = slideSet;
	}
	
	public char getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(char deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

	
}
