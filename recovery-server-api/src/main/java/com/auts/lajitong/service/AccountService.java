package com.auts.lajitong.service;

import com.auts.lajitong.model.common.ResponseData;

/**
 * 账户相关接口
 *
 * @author ranshao2017
 */
public interface AccountService {

	/**
	 * 发送短信验证码
	 * @param data
	 * @return
	 */
	ResponseData sendSms(String data);

	/**
	 * 手机号码登录
	 * @param reqStr
	 * @return
	 */
	ResponseData phoneLogin(String reqStr);

    /**
     * 手机号码和验证码登录
     * @param data
     * @return
     */
    ResponseData phoneCodeLogin(String data);

    /**
     * 人脸识别登录
     * @param data
     * @return
     */
    ResponseData faceLogin(String data);

    /**
     * 人脸识别注册
     * @param data
     * @return
     */
    ResponseData faceRegister(String data);

    /**
     * IC卡登录
     * @param data
     * @return
     */
    ResponseData icLogin(String data);

    /**
     * IC卡注册
     * @param data
     * @return
     */
    ResponseData icRegister(String data);

    /**
     * 获取用户信息
     * @param data
     * @return
     */
    ResponseData getUser(String data);

    /**
     * 扫码识别设备登录
     * @param data
     * @return
     */
    ResponseData scanDevice(String data);

}
