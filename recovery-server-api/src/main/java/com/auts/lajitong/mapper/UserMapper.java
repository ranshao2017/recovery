package com.auts.lajitong.mapper;

import org.apache.ibatis.annotations.*;


public interface UserMapper {

    @Update("update tbl_user set nick_name = #{nick_name} where uid = #{uid}")
    int updateNickName(@Param("uid") int uid, @Param("nick_name") String nickName);

}
