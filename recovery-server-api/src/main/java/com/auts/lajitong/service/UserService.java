package com.auts.lajitong.service;

import com.auts.lajitong.model.common.ResponseData;

public interface UserService {

    /**
     * 修改昵称
     * @param data
     * @return
     */
    ResponseData editNickName(String data);
}
