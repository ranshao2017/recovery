package com.auts.lajitong.model.dao;

/**
 * 投递记录
 * @author zqh
 */
public class DeliveryOrderModel {

    private int tid;
    private String orderNo;
    private int uid;
    private String deviceId;
    private String binNo;
    private int orderType;
    private int weight;
    private int price;
    private int amount;
    private String deliveryTime;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getBinNo() {
        return binNo;
    }

    public void setBinNo(String binNo) {
        this.binNo = binNo;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String toString() {
        return "DeliveryOrderModel{" +
                "tid=" + tid +
                ", orderNo='" + orderNo + '\'' +
                ", uid=" + uid +
                ", deviceId='" + deviceId + '\'' +
                ", binNo='" + binNo + '\'' +
                ", orderType=" + orderType +
                ", weight=" + weight +
                ", price=" + price +
                ", amount=" + amount +
                ", deliveryTime='" + deliveryTime + '\'' +
                '}';
    }
}
