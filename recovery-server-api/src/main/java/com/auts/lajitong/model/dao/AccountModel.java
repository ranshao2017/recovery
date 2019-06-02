package com.auts.lajitong.model.dao;

/**
 * 账户相关数据库Model.
 * @author zqh
 */
public class AccountModel {

    private int uid;
    private String accountId;
    private String nickName;
    private int sex;
    private int status;
    private int currentProfit;
    private int deliverCount;
    private int totalProfit;
    private String wxsOpenId;
    private String icCard;
    private String createTime;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public int getCurrentProfit() {
        return currentProfit;
    }

    public void setCurrentProfit(int currentProfit) {
        this.currentProfit = currentProfit;
    }

    public int getDeliverCount() {
        return deliverCount;
    }

    public void setDeliverCount(int deliverCount) {
        this.deliverCount = deliverCount;
    }

    public int getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(int totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getWxsOpenId() {
        return wxsOpenId;
    }

    public void setWxsOpenId(String wxsOpenId) {
        this.wxsOpenId = wxsOpenId;
    }

    public String getIcCard() {
        return icCard;
    }

    public void setIcCard(String icCard) {
        this.icCard = icCard;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "uid=" + uid +
                ", accountId='" + accountId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", status=" + status +
                ", currentProfit=" + currentProfit +
                ", deliverCount=" + deliverCount +
                ", totalProfit=" + totalProfit +
                ", wxsOpenId='" + wxsOpenId + '\'' +
                ", icCard='" + icCard + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
