package com.auts.lajitong.model.response;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
 * 投递记录
 * @author zqh
 */
public class DeliveryOrder {

    private int tid;
    @JSONField(name = "order_no")
    private String orderNo;
    @JSONField(name = "device_id")
    private String deviceId;
    @JSONField(name = "bin_no")
    private String binNo;
    private int orderType;
    private int weight;
    private int price;
    private int amount;
    @JSONField(name = "delivery_time")
    private String deliveryTime;

    private String s_order_type;
    private String s_weight;
    private String s_price;
    private String s_amount;

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

    public String getS_order_type() {
        return s_order_type;
    }

    public void setS_order_type(String s_order_type) {
        this.s_order_type = s_order_type;
    }

    public String getS_weight() {
        return new BigDecimal(weight).movePointLeft(3).toString();
    }

    public void setS_weight(String s_weight) {
        this.s_weight = s_weight;
    }

    public String getS_price() {
        return new BigDecimal(price).movePointLeft(2).toString();
    }

    public void setS_price(String s_price) {
        this.s_price = s_price;
    }

    public String getS_amount() {
        return new BigDecimal(amount).movePointLeft(2).toString();
    }

    public void setS_amount(String s_amount) {
        this.s_amount = s_amount;
    }

    @Override
    public String toString() {
        return "DeliveryOrder{" +
                "tid=" + tid +
                ", orderNo='" + orderNo + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", binNo='" + binNo + '\'' +
                ", orderType=" + orderType +
                ", weight=" + weight +
                ", price=" + price +
                ", amount=" + amount +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", s_order_type='" + s_order_type + '\'' +
                ", s_weight='" + s_weight + '\'' +
                ", s_price='" + s_price + '\'' +
                ", s_amount='" + s_amount + '\'' +
                '}';
    }
}
