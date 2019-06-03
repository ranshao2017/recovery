package com.auts.lajitong.controller;

import com.alibaba.fastjson.JSON;
import com.auts.lajitong.model.common.ResponseData;
import com.auts.lajitong.service.UserService;
import com.auts.lajitong.util.RequestUtil;
import com.auts.lajitong.websocket.WebsocketServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 小程序用户中心
 * @author zqh
 */
@RestController
@CrossOrigin
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 修改昵称
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/equipment/editNickName")
    public ResponseData editNickName(HttpServletRequest request) {
        String data = RequestUtil.buildReq(request);
        ResponseData responseData = new ResponseData();
        if (null == data) {
            responseData.setErr_code(1);
            responseData.setErr_msg("获取请求参数异常");
            return responseData;
        }
        return userService.editNickName(data);
    }
}
