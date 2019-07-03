package com.auts.lajitong.model.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 设备相关数据库
 * @author zqh
 */
public class Equiment {

    private int eid;
    @JSONField(name = "device_id")
    @JsonProperty(value = "device_id")
    private String deviceId;
    @JSONField(name = "create_time")
    @JsonProperty(value = "create_time")
    private String createTime;
    private Double latitude;
    private Double longitude;
    private String status;
    @JSONField(name = "s_status")
    @JsonProperty(value = "s_status")
    private String sStatus;
    private String remark;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getsStatus() {
        if("0".equals(status)){
            return "正常";
        }else if("1".equals(status)){
            return "故障";
        }else if("2".equals(status)){
            return "满溢";
        }else if("3".equals(status)){
            return "不可用";
        }
        return status;
    }

    public void setsStatus(String sStatus) {
        this.sStatus = sStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Equiment{" +
                "eid=" + eid +
                ", deviceId='" + deviceId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", status='" + status + '\'' +
                ", sStatus='" + sStatus + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
