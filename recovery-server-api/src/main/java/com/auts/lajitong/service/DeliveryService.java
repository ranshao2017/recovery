package com.auts.lajitong.service;

import com.auts.lajitong.model.common.ResponseData;

/**
 * 投递相关
 * @author zqh
 */
public interface DeliveryService {

    /**
     * 回收物品识别
     * @param data
     * @return
     */
    ResponseData know(String data);

    /**
     * 获取用户投递记录
     * @param data
     * @return
     */
    ResponseData orderList(String data);
}
