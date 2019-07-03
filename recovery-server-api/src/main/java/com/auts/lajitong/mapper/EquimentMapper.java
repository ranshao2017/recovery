package com.auts.lajitong.mapper;

import com.auts.lajitong.model.dao.EquimentModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EquimentMapper {

    @Select("select * from tbl_device")
    @Results({
    	@Result(property = "eid", column = "eid"),
    	@Result(property = "createTime", column = "create_time"),
    	@Result(property = "remark", column = "remark"),
    	@Result(property = "deviceId", column = "device_id"),
    	@Result(property = "latitude", column = "latitude"),
    	@Result(property = "longitude", column = "longitude"),
    	@Result(property = "status", column = "status")
    })
    List<EquimentModel> queryEquList();

}