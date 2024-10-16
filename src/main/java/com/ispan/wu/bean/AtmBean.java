package com.ispan.wu.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class AtmBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int key;
	private String city;
	private String district;
	private String postNo;
	private String postName;
	private String phone;
	private String address;
	private double longitude;
	private double latitude;
	private boolean hasAtm;
	private boolean hasDeposit;
	private boolean hasPassbookUpdate;
	private boolean hasPassbookUpdateMachine;
	private boolean has200cash;
	private boolean hasVoiceAssistant;
	private boolean isOutside;
	private int cityNo;
	private Timestamp insertTime;
	private Timestamp updateTime;
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public boolean isHasAtm() {
		return hasAtm;
	}
	public void setHasAtm(boolean hasAtm) {
		this.hasAtm = hasAtm;
	}
	public boolean isHasDeposit() {
		return hasDeposit;
	}
	public void setHasDeposit(boolean hasDeposit) {
		this.hasDeposit = hasDeposit;
	}
	public boolean isHasPassbookUpdate() {
		return hasPassbookUpdate;
	}
	public void setHasPassbookUpdate(boolean hasPassbookUpdate) {
		this.hasPassbookUpdate = hasPassbookUpdate;
	}
	public boolean isHasPassbookUpdateMachine() {
		return hasPassbookUpdateMachine;
	}
	public void setHasPassbookUpdateMachine(boolean hasPassbookUpdateMachine) {
		this.hasPassbookUpdateMachine = hasPassbookUpdateMachine;
	}
	public boolean isHas200cash() {
		return has200cash;
	}
	public void setHas200cash(boolean has200cash) {
		this.has200cash = has200cash;
	}
	public boolean isHasVoiceAssistant() {
		return hasVoiceAssistant;
	}
	public void setHasVoiceAssistant(boolean hasVoiceAssistant) {
		this.hasVoiceAssistant = hasVoiceAssistant;
	}
	public boolean isOutside() {
		return isOutside;
	}
	public void setOutside(boolean isOutside) {
		this.isOutside = isOutside;
	}
	public int getCityNo() {
		return cityNo;
	}
	public void setCityNo(int cityNo) {
		this.cityNo = cityNo;
	}
	
	public Timestamp getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}
	
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return String.format("[key=%d, city=%s, district=%s, postNo=%s, postName=%s, phone=%s, address=%s, longitude=%3.6f, latitude=%3.6f, hasAtm=%b, hasDeposit=%b, hasPassbookUpdate=%b, hasPassbookUpdateMachine=%b, has200cash=%b, hasVoiceAssistant=%b, isOutside=%b, cityNo=%d, insertTime=%s, updateTime=%s]",key, city, district, postNo, postName, phone, address, longitude, latitude, hasAtm, hasDeposit, hasPassbookUpdate, hasPassbookUpdateMachine, has200cash, hasVoiceAssistant, isOutside, cityNo, insertTime, updateTime);
	}
	
	
	
}
