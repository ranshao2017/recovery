package com.auts.lajitong.mapper;

import org.apache.ibatis.annotations.*;

import com.auts.lajitong.model.dao.AccountModel;

/**
 * 账户相关mapper
 * @author zqh
 */
public interface AccountMapper {

    @Select("select * from tbl_user where account_id=#{accountId} limit 1")
    @Results({
            @Result(property = "uid", column = "uid"),
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "status", column = "status"),
            @Result(property = "currentProfit", column = "current_profit"),
            @Result(property = "deliverCount", column = "deliver_count"),
            @Result(property = "totalProfit", column = "total_profit"),
            @Result(property = "wxsOpenId", column = "wxs_open_id"),
            @Result(property = "faceToken", column = "face_token"),
            @Result(property = "icCard", column = "ic_card"),
            @Result(property = "createTime", column = "create_time")
    })
    AccountModel queryAccountByPhone(@Param("accountId") String accountId);

    @Insert("insert into tbl_user (account_id, nick_name, sex, status, current_profit, deliver_count, " +
            "total_profit, wxs_open_id, face_token, ic_card, create_time) "
            + "values (#{ac.accountId},#{ac.nickName},#{ac.sex},#{ac.status},#{ac.currentProfit}," +
            "#{ac.deliverCount},#{ac.totalProfit},#{ac.wxsOpenId},#{ac.faceToken},#{ac.icCard},#{ac.createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "ac.uid")
    int register(@Param("ac") AccountModel ac);

    @Select("select * from tbl_user where face_token=#{faceToken} limit 1")
    @Results({
            @Result(property = "uid", column = "uid"),
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "status", column = "status"),
            @Result(property = "currentProfit", column = "current_profit"),
            @Result(property = "deliverCount", column = "deliver_count"),
            @Result(property = "totalProfit", column = "total_profit"),
            @Result(property = "wxsOpenId", column = "wxs_open_id"),
            @Result(property = "faceToken", column = "face_token"),
            @Result(property = "icCard", column = "ic_card"),
            @Result(property = "createTime", column = "create_time")
    })
    AccountModel queryAccountByFaceToken(@Param("faceToken") String faceToken);

    @Update("update tbl_user set account_id = #{model.accountId}, nick_name=#{model.nickName},sex=#{model.sex}," +
            "current_profit=#{model.currentProfit},deliver_count=#{model.deliverCount},total_profit=#{model.totalProfit}," +
            "wxs_open_id=#{model.wxsOpenId},face_token=#{model.faceToken},ic_card=#{model.icCard},create_time=#{model.createTime} where uid=#{model.uid}")
    int updateAccountModel(@Param("model") AccountModel model);

    @Select("select * from tbl_user where ic_card=#{card} limit 1")
    @Results({
            @Result(property = "uid", column = "uid"),
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "status", column = "status"),
            @Result(property = "currentProfit", column = "current_profit"),
            @Result(property = "deliverCount", column = "deliver_count"),
            @Result(property = "totalProfit", column = "total_profit"),
            @Result(property = "wxsOpenId", column = "wxs_open_id"),
            @Result(property = "faceToken", column = "face_token"),
            @Result(property = "icCard", column = "ic_card"),
            @Result(property = "createTime", column = "create_time")
    })
    AccountModel queryAccountByIcCard(@Param("card") String card);

    @Select("select * from tbl_user where uid=#{uid} limit 1")
    @Results({
            @Result(property = "uid", column = "uid"),
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "status", column = "status"),
            @Result(property = "currentProfit", column = "current_profit"),
            @Result(property = "deliverCount", column = "deliver_count"),
            @Result(property = "totalProfit", column = "total_profit"),
            @Result(property = "wxsOpenId", column = "wxs_open_id"),
            @Result(property = "faceToken", column = "face_token"),
            @Result(property = "icCard", column = "ic_card"),
            @Result(property = "createTime", column = "create_time")
    })
    AccountModel queryAccountByUid(@Param("uid") String uid);
}
