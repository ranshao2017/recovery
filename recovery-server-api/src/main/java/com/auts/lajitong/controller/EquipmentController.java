package com.auts.lajitong.controller;

import com.alibaba.fastjson.JSON;
import com.auts.lajitong.model.common.ResponseData;
import com.auts.lajitong.service.EquipmentService;
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
 * 设备相关
 * @author zqh
 */
@RestController
@CrossOrigin
public class EquipmentController {

    private static final Logger LOGGER = LogManager.getLogger(EquipmentController.class);

    @Autowired
    private EquipmentService equipmentService;

    /**
     * 设备列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/equipment/list")
    public ResponseData equList() {
        return equipmentService.equList();
    }


}
