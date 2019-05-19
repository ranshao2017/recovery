package com.auts.lajitong.mapper;

import java.util.List;

import com.auts.lajitong.model.dao.*;
import org.apache.ibatis.annotations.*;

/**
 * 投递相关
 * @author zqh
 *
 */
public interface DeliveryMapper {

    @Select("select * from tbl_know_obj where know_name=#{knowName} limit 1")
    @Results({
            @Result(property = "kid", column = "kid"),
            @Result(property = "knowName", column = "know_name"),
            @Result(property = "objType", column = "obj_type")
    })
    KnowObjModel queryKnowObjByName(@Param("knowName") String knowName);

    @Select("select * from tbl_obj_type where oid=#{oid} limit 1")
    @Results({
            @Result(property = "oid", column = "oid"),
            @Result(property = "objectName", column = "object_name"),
            @Result(property = "unitPrice", column = "unit_price"),
            @Result(property = "remark", column = "remark")
    })
    ObjTypeModel queryObjTypeByOid(@Param("oid") int oid);

    @Insert("insert into tbl_know_obj (know_name, obj_type) values (#{kom.knowName},#{kom.objType})")
    @Options(useGeneratedKeys = true, keyProperty = "kom.kid")
    int saveKnowObj(@Param("kom") KnowObjModel kom);

    @Insert("insert into tbl_know_dtl (image_path, device_id, uid, know_name, know_time) values (#{kdm.imagePath},#{kdm.deviceId}," +
            "#{kdm.uid},#{kdm.knowName},#{kdm.knowTime})")
    @Options(useGeneratedKeys = true, keyProperty = "kdm.hid")
    int saveKnowDtl(@Param("kdm") KnowDtlModel kdm);

    @Select("select * from tbl_order where uid=#{uid} order by delivery_time desc")
    @Results({
    	@Result(property = "tid", column = "tid"),
    	@Result(property = "orderNo", column = "order_no"),
    	@Result(property = "uid", column = "uid"),
    	@Result(property = "deviceId", column = "device_id"),
    	@Result(property = "binNo", column = "bin_no"),
    	@Result(property = "orderType", column = "order_type"),
    	@Result(property = "weight", column = "weight"),
    	@Result(property = "price", column = "price"),
    	@Result(property = "amount", column = "amount"),
    	@Result(property = "deliveryTime", column = "delivery_time")
    })
    List<DeliveryOrderModel> queryDeliveryListByUser(@Param("uid") int uid);

    @Select("select * from tbl_obj_type")
    @Results({
            @Result(property = "oid", column = "oid"),
            @Result(property = "objectName", column = "object_name"),
            @Result(property = "unitPrice", column = "unit_price"),
            @Result(property = "remark", column = "remark")
    })
    List<ObjTypeModel> queryObjTypes();

}
