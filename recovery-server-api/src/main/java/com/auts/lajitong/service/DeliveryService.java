package com.auts.lajitong.service;

import com.auts.lajitong.model.dao.LitteredhatDeliveryListModel;

import java.util.List;

public interface DeliveryService {

    List<LitteredhatDeliveryListModel> getListByUser(String userId) throws Exception;

}
