package com.xplink.random_cm.datamodel;

import java.sql.Timestamp;
import java.util.List;

public class EventBean {

	private Integer id;
	private String eventName;
	private String Randomed;
	
	private Timestamp dateStart;
	private Timestamp dateEnd;
	private List<Integer> inviteFriend;
	private int eventDetailId; 
	private int inviteUserId;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;
	private String imgType;
	private String desc;
	private String flag;
	private Boolean readyState;
	
	
	
	public Boolean getReadyState() {
		return readyState;
	}
	public void setReadyState(Boolean readyState) {
		this.readyState = readyState;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Timestamp getDateStart() {
		return dateStart;
	}
	public void setDateStart(Timestamp dateStart) {
		this.dateStart = dateStart;
	}
	public Timestamp getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Timestamp dateEnd) {
		this.dateEnd = dateEnd;
	}
	public List<Integer> getInviteFriend() {
		return inviteFriend;
	}
	public void setInviteFriend(List<Integer> inviteFriend) {
		this.inviteFriend = inviteFriend;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String string) {
		this.createBy = string;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getEventDetailId() {
		return eventDetailId;
	}
	public void setEventDetailId(int eventDetailId) {
		this.eventDetailId = eventDetailId;
	}
	public int getInviteUserId() {
		return inviteUserId;
	}
	public void setInviteUserId(int inviteUserId) {
		this.inviteUserId = inviteUserId;
	}
	public String getImgType() {
		return imgType;
	}
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getRandomed() {
		return Randomed;
	}
	public void setRandomed(String randomed) {
		this.Randomed = randomed;
	}
	

	
}
