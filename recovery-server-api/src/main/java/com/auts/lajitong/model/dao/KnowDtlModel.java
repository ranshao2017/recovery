package com.auts.lajitong.model.dao;
/**
 * 物品识别信息Model
 * @author zqh
 */
public class KnowDtlModel {

    private int hid;
    //图像保存路径
    private String imagePath;
    private String deviceId;
	private int uid;
	private String knowName;
	private String knowTime;

	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getKnowName() {
		return knowName;
	}

	public void setKnowName(String knowName) {
		this.knowName = knowName;
	}

	public String getKnowTime() {
		return knowTime;
	}

	public void setKnowTime(String knowTime) {
		this.knowTime = knowTime;
	}

	@Override
	public String toString() {
		return "KnowDtlModel{" +
				"hid=" + hid +
				", imagePath='" + imagePath + '\'' +
				", deviceId='" + deviceId + '\'' +
				", uid=" + uid +
				", knowName='" + knowName + '\'' +
				", knowTime='" + knowTime + '\'' +
				'}';
	}
}
