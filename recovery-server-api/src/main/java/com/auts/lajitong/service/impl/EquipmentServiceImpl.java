package com.auts.lajitong.service.impl;

import com.auts.lajitong.mapper.EquimentMapper;
import com.auts.lajitong.model.common.ResponseData;
import com.auts.lajitong.model.dao.EquimentModel;
import com.auts.lajitong.model.response.Equiment;
import com.auts.lajitong.service.EquipmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	private static final Logger LOGGER = LogManager.getLogger(EquipmentServiceImpl.class);

	@Autowired
	EquimentMapper mapper;

	/**
	 * 设备列表
	 * @return
	 */
	@Override
	public ResponseData equList() {
		ResponseData responseData = new ResponseData();
		LOGGER.info("获取设备列表");
		List<EquimentModel> list = mapper.queryEquList();
		List<Equiment> eList = new ArrayList<>();
		for (EquimentModel model : list){
			Equiment dto = new Equiment();
			BeanUtils.copyProperties(model, dto);
			eList.add(dto);
		}
		responseData.setErr_code(0);
		responseData.setErr_msg("获取设备列表成功");
		responseData.setData(eList);
		return responseData;
	}
}
