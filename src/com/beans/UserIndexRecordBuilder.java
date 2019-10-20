package com.beans;

public class UserIndexRecordBuilder {
	private String ipAddress, latitude, longitude, time, id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public UserIndexRecordBuilder(){
		this.ipAddress = "";
		this.latitude = "";
		this.longitude = "";
		this.time = "";
	}
	
	public UserIndexRecordBuilder(String ipAddress, String latitude, String longitude, String time, String id){
		this.ipAddress = ipAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.time = time;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "UserTrackerBuilder [ipAddress=" + ipAddress + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", time=" + time + ", id=" + id + "]";
	}
}
