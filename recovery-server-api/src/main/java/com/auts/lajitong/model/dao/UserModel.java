package com.auts.lajitong.model.dao;

public class UserModel {

    private String id;

    private String accountId;

    private String nickName;

    private int sex; //1-男，2-女

    private int status;//0-启用，1-停用

    private String currentProfit;

    private int deliverCount;

    private String totalProfit;

    private String wxsOpenId;

    private long createTime;

    /**
     * 人脸唯一标识
     */
    private String faceToken;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getWxsOpenId() {
        return wxsOpenId;
    }

    public void setWxsOpenId(String wxsOpenId) {
        this.wxsOpenId = wxsOpenId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCurrentProfit() {
        return currentProfit;
    }

    public void setCurrentProfit(String currentProfit) {
        this.currentProfit = currentProfit;
    }

    public int getDeliverCount() {
        return deliverCount;
    }

    public void setDeliverCount(int deliverCount) {
        this.deliverCount = deliverCount;
    }

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", accountId='" + accountId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", status=" + status +
                ", currentProfit='" + currentProfit + '\'' +
                ", deliverCount=" + deliverCount +
                ", totalProfit='" + totalProfit + '\'' +
                ", wxsOpenId='" + wxsOpenId + '\'' +
                ", createTime=" + createTime +
                ", faceToken='" + faceToken + '\'' +
                '}';
    }
}
