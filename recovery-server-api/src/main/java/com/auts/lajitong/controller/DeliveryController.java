package com.auts.lajitong.controller;

import com.auts.lajitong.model.common.ResponseData;
import com.auts.lajitong.service.DeliveryService;
import com.auts.lajitong.util.RequestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户投递相关
 * @author zqh
 */
@RestController
@CrossOrigin
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    private static final Logger LOGGER = LogManager.getLogger(DeliveryController.class);

    /**
     * 回收物品识别
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/know")
    public ResponseData know(HttpServletRequest request) {
        String data = RequestUtil.buildReq(request);
        if (null == data) {
            ResponseData responseData = new ResponseData();
            responseData.setErr_code(1);
            responseData.setErr_msg("获取请求参数异常");
            return responseData;
        }
        return deliveryService.know(data);
    }

    /**
     * 获取用户投递记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/orderList")
    public ResponseData orderList(HttpServletRequest request) {
        String data = RequestUtil.buildReq(request);
        if (null == data) {
            ResponseData responseData = new ResponseData();
            responseData.setErr_code(1);
            responseData.setErr_msg("获取请求参数异常");
            return responseData;
        }
        return deliveryService.orderList(data);
    }
}
