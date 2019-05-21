package com.auts.lajitong.controller;

import com.auts.lajitong.model.common.ResponseData;
import com.auts.lajitong.service.AccountService;
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
 * 账户相关接口
 *
 * @author zqh
 */
@RestController
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountService accountService;

    private static final Logger LOGGER = LogManager.getLogger(AccountController.class);

    /**
     * 发送短信验证码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendSms")
    public ResponseData sendSms(HttpServletRequest request) {
        String data = RequestUtil.buildReq(request);
        if (null == data) {
            ResponseData responseData = new ResponseData();
            responseData.setErr_code(1);
            responseData.setErr_msg("获取请求参数异常");
            return responseData;
        }
        return accountService.sendSms(data);
    }

    /**
     * 手机号码登录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/phoneLogin")
    public ResponseData phoneLogin(HttpServletRequest request) {
        String data = RequestUtil.buildReq(request);
        if (null == data) {
            ResponseData responseData = new ResponseData();
            responseData.setErr_code(1);
            responseData.setErr_msg("获取请求参数异常");
            return responseData;
        }
        return accountService.phoneLogin(data);
    }

    /**
     * 手机号码和验证码登录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/phoneCodeLogin")
    public ResponseData phoneCodeLogin(HttpServletRequest request) {
        String data = RequestUtil.buildReq(request);
        if (null == data) {
            ResponseData responseData = new ResponseData();
            responseData.setErr_code(1);
            responseData.setErr_msg("获取请求参数异常");
            return responseData;
        }
        return accountService.phoneCodeLogin(data);
    }

    /**
     * 人脸识别登录
     */
    @ResponseBody
    @RequestMapping("/faceLogin")
    public ResponseData faceLogin(HttpServletRequest request) {
        String data = RequestUtil.buildReq(request);
        if (null == data) {
            ResponseData responseData = new ResponseData();
            responseData.setErr_code(1);
            responseData.setErr_msg("获取请求参数异常");
            return responseData;
        }
        return accountService.faceLogin(data);
    }


    /**
     * 人脸识别注册
     */
    @ResponseBody
    @RequestMapping("/faceRegister")
    public ResponseData faceRegister(HttpServletRequest request) {
        String data = RequestUtil.buildReq(request);
        if (null == data) {
            ResponseData responseData = new ResponseData();
            responseData.setErr_code(1);
            responseData.setErr_msg("获取请求参数异常");
            return responseData;
        }
        return accountService.faceRegister(data);
    }

    /**
     * IC卡登录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/icLogin")
    public ResponseData icLogin(HttpServletRequest request) {
        String data = RequestUtil.buildReq(request);
        if (null == data) {
            ResponseData responseData = new ResponseData();
            responseData.setErr_code(1);
            responseData.setErr_msg("获取请求参数异常");
            return responseData;
        }
        return accountService.icLogin(data);
    }

    /**
     * IC卡注册
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/icRegister")
    public ResponseData icRegister(HttpServletRequest request) {
        String data = RequestUtil.buildReq(request);
        if (null == data) {
            ResponseData responseData = new ResponseData();
            responseData.setErr_code(1);
            responseData.setErr_msg("获取请求参数异常");
            return responseData;
        }
        return accountService.icRegister(data);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUser")
    public ResponseData getUser(HttpServletRequest request) {
        String data = RequestUtil.buildReq(request);
        if (null == data) {
            ResponseData responseData = new ResponseData();
            responseData.setErr_code(1);
            responseData.setErr_msg("获取请求参数异常");
            return responseData;
        }
        return accountService.getUser(data);
    }

    /**
     * 扫码识别设备登录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/scanDevice")
    public ResponseData scanDevice(HttpServletRequest request) {
        String data = RequestUtil.buildReq(request);
        if (null == data) {
            ResponseData responseData = new ResponseData();
            responseData.setErr_code(1);
            responseData.setErr_msg("获取请求参数异常");
            return responseData;
        }
        return accountService.scanDevice(data);
    }

}