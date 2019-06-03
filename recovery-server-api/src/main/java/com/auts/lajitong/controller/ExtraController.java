package com.auts.lajitong.controller;

import com.alibaba.fastjson.JSON;
import com.auts.lajitong.model.common.ResponseData;
import com.auts.lajitong.util.RequestUtil;
import com.auts.lajitong.websocket.WebsocketServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统辅助相关
 * @author zqh
 */
@RestController
@CrossOrigin
public class ExtraController {

    private static final Logger LOGGER = LogManager.getLogger(ExtraController.class);

    /**
     * 系统升级
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/equipment/upgrade")
    public ResponseData upgrade(HttpServletRequest request) {
        String data = RequestUtil.buildReq(request);
        ResponseData responseData = new ResponseData();
        if (null == data) {
            responseData.setErr_code(1);
            responseData.setErr_msg("获取请求参数异常");
            return responseData;
        }
        JSONObject obj = new JSONObject();
        obj.put("data_type", "system");
        JSONObject jsonObject = new JSONObject(data);
        obj.put("data", jsonObject);
        boolean result = WebsocketServer.sendMsg(JSON.toJSONString(obj));
        if(result){
            responseData.setErr_code(0);
            responseData.setErr_msg("广播终端设备升级消息成功");
            return responseData;
        }else{
            responseData.setErr_code(1);
            responseData.setErr_msg("广播终端设备升级消息失败");
            return responseData;
        }
    }
}
