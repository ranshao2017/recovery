package com.auts.lajitong.model.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.auts.lajitong.consts.SexTypeEnum;
import com.auts.lajitong.consts.StatusTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * 账户相关
 * @author zqh
 */
public class Account {

    private int uid;
    @JSONField(name = "account_id")
    @JsonProperty(value = "account_id")
    private String accountId;
    @JSONField(name = "nick_name")
    @JsonProperty(value = "nick_name")
    private String nickName;
    private int sex;
    private int status;
    @JSONField(name = "current_profit")
    @JsonProperty(value = "current_profit")
    private int currentProfit;
    @JSONField(name = "deliver_count")
    @JsonProperty(value = "deliver_count")
    private int deliverCount;
    @JSONField(name = "total_profit")
    @JsonProperty(value = "total_profit")
    private int totalProfit;
    @JSONField(name = "create_time")
    @JsonProperty(value = "create_time")
    private String createTime;

    private String s_sex;
    private String s_status;
    private String s_current_profit;
    private String s_total_profit;

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

    public String getS_sex() {
        return SexTypeEnum.getTextByValue(sex);
    }

    public void setS_sex(String s_sex) {
        this.s_sex = s_sex;
    }

    public String getS_status() {
        return StatusTypeEnum.getTextByValue(status);
    }

    public void setS_status(String s_status) {
        this.s_status = s_status;
    }

    public String getS_current_profit() {
        return new BigDecimal(currentProfit).movePointLeft(2).toString();
    }

    public void setS_current_profit(String s_current_profit) {
        this.s_current_profit = s_current_profit;
    }

    public String getS_total_profit() {
        return new BigDecimal(totalProfit).movePointLeft(2).toString();
    }

    public void setS_total_profit(String s_total_profit) {
        this.s_total_profit = s_total_profit;
    }

    @Override
    public String toString() {
        return "Account{" +
                "uid=" + uid +
                ", accountId='" + accountId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", status=" + status +
                ", currentProfit=" + currentProfit +
                ", deliverCount=" + deliverCount +
                ", totalProfit=" + totalProfit +
                ", createTime='" + createTime + '\'' +
                ", s_sex='" + s_sex + '\'' +
                ", s_status='" + s_status + '\'' +
                ", s_current_profit='" + s_current_profit + '\'' +
                ", s_total_profit='" + s_total_profit + '\'' +
                '}';
    }
}
