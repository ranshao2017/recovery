package com.auts.lajitong.service.impl;

import com.auts.lajitong.mapper.UserMapper;
import com.auts.lajitong.model.common.ResponseData;
import com.auts.lajitong.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 修改昵称
     * @param data
     * @return
     */
    @Override
    public ResponseData editNickName(String data) {
        ResponseData responseData = new ResponseData();
        LOGGER.info("修改昵称" + data);
        JSONObject jsonObject = new JSONObject(data);
        int uid = jsonObject.getInt("uid");
        String nickName = jsonObject.getString("nick_name");
        if(StringUtils.isBlank(nickName)){
            responseData.setErr_code(1);
            responseData.setErr_msg("昵称不能为空");
            return responseData;
        }
        userMapper.updateNickName(uid, nickName);
        responseData.setErr_code(0);
        responseData.setErr_msg("修改昵称成功");
        return responseData;
    }
}
