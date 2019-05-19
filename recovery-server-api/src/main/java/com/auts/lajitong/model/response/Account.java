package com.auts.lajitong.model.response;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 账户相关
 * @author zqh
 */
public class Account {

    private int uid;
    @JSONField(name = "account_id")
    private String accountId;
    @JSONField(name = "nick_name")
    private String nickName;
    private int sex;
    private int status;
    @JSONField(name = "current_profit")
    private int currentProfit;
    @JSONField(name = "deliver_count")
    private int deliverCount;
    @JSONField(name = "total_profit")
    private int totalProfit;
    @JSONField(name = "create_time")
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
