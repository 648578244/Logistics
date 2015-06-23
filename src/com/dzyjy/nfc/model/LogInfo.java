package com.dzyjy.nfc.model;

public class LogInfo {
	private String sendUnit;
	private String sendTime;
	private String sendAddress;
	private String sendMan;
	private String photo;
	
	private boolean isNowItem;
	
	public boolean isNowItem() {
		return isNowItem;
	}
	public void setNowItem(boolean isNowItem) {
		this.isNowItem = isNowItem;
	}
	public String getSendUnit() {
		return sendUnit;
	}
	public void setSendUnit(String sendUnit) {
		this.sendUnit = sendUnit;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getSendAddress() {
		return sendAddress;
	}
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}
	public String getSendMan() {
		return sendMan;
	}
	public void setSendMan(String sendMan) {
		this.sendMan = sendMan;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
