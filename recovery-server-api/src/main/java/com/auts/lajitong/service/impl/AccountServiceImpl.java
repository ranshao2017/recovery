package com.auts.lajitong.service.impl;

import com.alibaba.fastjson.JSON;
import com.auts.lajitong.api.YPSmsApi;
import com.auts.lajitong.mapper.AccountMapper;
import com.auts.lajitong.mapper.CaptchaMapper;
import com.auts.lajitong.model.common.ResponseData;
import com.auts.lajitong.model.dao.AccountModel;
import com.auts.lajitong.model.dao.CaptchaModel;
import com.auts.lajitong.consts.SexTypeEnum;
import com.auts.lajitong.consts.StatusTypeEnum;
import com.auts.lajitong.model.response.Account;
import com.auts.lajitong.service.AccountService;
import com.auts.lajitong.util.DateUtils;
import com.auts.lajitong.websocket.WebsocketServer;
import com.baidu.aip.face.AipFace;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 账户
 * @author zqh
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LogManager.getLogger(AccountServiceImpl.class);

	/**
	 * 百度人脸识别
	 */
    @Value("${baidu.APP_ID}")
    private String APP_ID;
    @Value("${baidu.API_KEY}")
    private String API_KEY;
    @Value("${baidu.SECRET_KEY}")
    private String SECRET_KEY;
    @Value("${baidu.imageType}")
    private String imageType;

    @Autowired
    CaptchaMapper captchaMapper;
    @Autowired
    AccountMapper accountMapper;

    /**
     * 发送短信验证码
     */
    @Override
    public ResponseData sendSms(String data) {
        ResponseData responseData = new ResponseData();
        JSONObject jsonObject = new JSONObject(data);
        try {
            //发送验证码短信
            String captchaCode = YPSmsApi.getRandCaptchaCode();
            String result = YPSmsApi.sendSms(YPSmsApi.API_KEY, String.format(YPSmsApi.CAPTCHA_TEXT, captchaCode), jsonObject.getString("mobile"));
            if(result.contains("发送成功")) {
                LOGGER.info("发送短信验证码成功：" + jsonObject.getString("mobile") + "-" + jsonObject.getString("device_id") + "-" + captchaCode);
                CaptchaModel cm = new CaptchaModel();
                cm.setPhoneNo(jsonObject.getString("mobile"));
                cm.setCaptchaCode(captchaCode);
                cm.setSendTime(DateUtils.getNowTime());
                if(captchaMapper.queryByPhoneNo(cm.getPhoneNo()) != null) {
                    captchaMapper.updateCaptcha(cm);
                } else {
                    captchaMapper.insert(cm);
                }
                responseData.setErr_code(0);
                responseData.setErr_msg("验证码发送成功");
            } else {
                responseData.setErr_code(1);
                responseData.setErr_msg("验证码发送失败");
            }
        } catch (Exception e) {
            LOGGER.error("验证码获取异常" + jsonObject.getString("mobile") + "-" + jsonObject.getString("device_id"), e);
            responseData.setErr_code(1);
            responseData.setErr_msg("验证码发送异常");
        }
        return responseData;
    }

    /**
     * 手机号码登录
     * @param data
     * @return
     */
    @Override
    public ResponseData phoneLogin(String data) {
        ResponseData responseData = new ResponseData();
        JSONObject jsonObject = new JSONObject(data);
        String mobile = jsonObject.getString("mobile");
        String device_id = jsonObject.getString("device_id");
        LOGGER.info("手机号码登录" + mobile + "-" + device_id);
        AccountModel accountModel = accountMapper.queryAccountByPhone(mobile);
        if(null == accountModel) {
            LOGGER.error("根据登录手机号未找到用户" + mobile);
            responseData.setErr_code(1);
            responseData.setErr_msg("未找到该手机号对应的用户");
        }else{
            responseData.setErr_code(0);
            responseData.setErr_msg("手机号码登录成功");
            Account account = new Account();
            BeanUtils.copyProperties(accountModel, account);
            responseData.setData(account);
            LOGGER.info("手机号码登录成功");
        }
        return responseData;
    }

    /**
     * 手机号码和验证码登录
     * @param data
     * @return
     */
    @Override
    public ResponseData phoneCodeLogin(String data) {
        ResponseData responseData = new ResponseData();
        JSONObject jsonObject = new JSONObject(data);
        String mobile = jsonObject.getString("mobile");
        String code = jsonObject.getString("code");
        String device_id = jsonObject.getString("device_id");
        //验证码模块
        boolean sms = this.verificationSms(mobile, code);
        if(!sms){
            responseData.setErr_code(1);
            responseData.setErr_msg("验证验证码失败");
            LOGGER.error("验证验证码失败" + mobile + "-" + code + "-" + device_id);
            return responseData;
        }
        AccountModel accountModel = accountMapper.queryAccountByPhone(mobile);
        if(null == accountModel) {
            //注册
            AccountModel ac = new AccountModel();
            ac.setAccountId(mobile);
            ac.setNickName("");
            ac.setSex(SexTypeEnum.unknow.getValue());
            ac.setStatus(StatusTypeEnum.normal.getValue());
            ac.setCurrentProfit(0);
            ac.setDeliverCount(0);
            ac.setTotalProfit(0);
            ac.setWxsOpenId("");
            ac.setFaceToken("");
            ac.setIcCard("");
            ac.setCreateTime(DateUtils.getNowTime());
            accountMapper.register(ac);
            AccountModel aM = accountMapper.queryAccountByPhone(mobile);
            Account account = new Account();
            BeanUtils.copyProperties(aM, account);
            responseData.setErr_code(0);
            responseData.setErr_msg("手机号码注册成功");
            responseData.setData(account);
            LOGGER.info("手机号码注册成功" + mobile + "-" + code + "-" + device_id);
        }else{
            //登录
            Account account = new Account();
            BeanUtils.copyProperties(accountModel, account);
            responseData.setErr_code(0);
            responseData.setErr_msg("手机号码登录成功");
            responseData.setData(account);
            LOGGER.info("手机号码登录成功" + mobile + "-" + code + "-" + device_id);
        }
        return responseData;
    }

    /**
     * 人脸识别登录
     *
     * @param data
     * @return
     */
    @Override
    public ResponseData faceLogin(String data) {
        ResponseData responseData = new ResponseData();
        JSONObject jsonObject = new JSONObject(data);
        //读取人像后BASE64编码
        String image = jsonObject.getString("image");
        String device_id = jsonObject.getString("device_id");
        LOGGER.info("人脸登录请求数据：" + data);

        HashMap<String, String> options = new HashMap<>();
        //返回对比度最高的那张脸
        options.put("max_user_num", "1");
        // 人脸搜索
        JSONObject res = getClient().search(image, imageType, "01", options);
        LOGGER.info("调用百度AI人像检索，获取到信息：" + res.toString());
        String error_code = res.getString("error_msg");
        if(!"SUCCESS".equals(error_code)){
            LOGGER.info("人像检索失败" + res.toString());
            responseData.setErr_code(1);
            responseData.setErr_msg("人像检索失败");
        }else{
            JSONObject result = res.getJSONObject("result");
            JSONArray jsonArray = result.getJSONArray("user_list");
            //默认获取第一条用户信息
            JSONObject userInfo = jsonArray.getJSONObject(0);
            //人像匹配度
            double score = userInfo.getDouble("score");
            if (score < 80) {
                responseData.setErr_code(1);
                responseData.setErr_msg("人像检索失败");
            }else{
                String faceToken = result.getString("face_token");
                AccountModel aM = accountMapper.queryAccountByFaceToken(faceToken);
                if(null == aM){
                    responseData.setErr_code(1);
                    responseData.setErr_msg("未找到人脸对应的用户信息");
                }else{
                    responseData.setErr_code(0);
                    responseData.setErr_msg("人脸识别登录成功");
                    Account account = new Account();
                    BeanUtils.copyProperties(aM, account);
                    responseData.setData(account);
                }
            }
        }
        return responseData;
    }

    /**
     * 人脸识别注册
     * @return
     */
    @Override
    public ResponseData faceRegister(String data) {
        ResponseData responseData = new ResponseData();
        JSONObject jsonObject = new JSONObject(data);
        LOGGER.info("人脸注册请求数据：" + data);
        String image = jsonObject.getString("image");
        String mobile = jsonObject.getString("mobile");
        String code = jsonObject.getString("code");
        //验证码模块
        boolean sms = this.verificationSms(mobile, code);
        if(!sms){
            responseData.setErr_code(1);
            responseData.setErr_msg("验证验证码失败");
            return responseData;
        }
        try {
            HashMap<String, String> options = new HashMap<>();
            options.put("mobile", mobile);
            //先删除已有的人像信息
            getClient().deleteUser("01", mobile, options);
            LOGGER.info("调用百度AI删除已有的人像信息成功");
            //再新增新的人像信息
            JSONObject res = getClient().addUser(image, imageType, "01", mobile, options);
            LOGGER.info("调用百度AI接口新增新的人像信息，返回数据：" + res.toString());
            String status = res.getString("error_msg");
            if (!"SUCCESS".equals(status)) {
                LOGGER.info("人像注册失败");
                responseData.setErr_code(1);
                responseData.setErr_msg("人像注册失败");
            }else{
                LOGGER.info("人像注册成功");
                res = res.getJSONObject("result");
                //人像信息的唯一标识
                String face_token = res.getString("face_token");

                //判断是否存在该用户
                AccountModel user = accountMapper.queryAccountByPhone(mobile);
                if(null == user){
                    LOGGER.info("不存在用户，注册");
                    AccountModel ac = new AccountModel();
                    ac.setAccountId(mobile);
                    ac.setNickName("");
                    ac.setSex(SexTypeEnum.unknow.getValue());
                    ac.setStatus(StatusTypeEnum.normal.getValue());
                    ac.setCurrentProfit(0);
                    ac.setDeliverCount(0);
                    ac.setTotalProfit(0);
                    ac.setWxsOpenId("");
                    ac.setFaceToken(face_token);
                    ac.setIcCard("");
                    ac.setCreateTime(DateUtils.getNowTime());
                    accountMapper.register(ac);
                    AccountModel aM = accountMapper.queryAccountByPhone(mobile);
                    Account account = new Account();
                    BeanUtils.copyProperties(aM, account);
                    responseData.setErr_code(0);
                    responseData.setErr_msg("人脸注册成功");
                    responseData.setData(account);
                    LOGGER.info("人脸注册成功");
                }else{
                    LOGGER.info("存在用户，完善facetoken");
                    user.setFaceToken(face_token);
                    accountMapper.updateAccountModel(user);
                    Account account = new Account();
                    BeanUtils.copyProperties(user, account);
                    responseData.setErr_code(0);
                    responseData.setErr_msg("人脸注册成功");
                    responseData.setData(account);
                    LOGGER.info("人脸信息更新成功");
                }
            }
        } catch (Exception e) {
            LOGGER.error("人像注册异常", e);
            responseData.setErr_code(1);
            responseData.setErr_msg("人像注册异常");
            return responseData;
        }
        return responseData;
    }

    /**
     * IC卡登录
     *
     * @param data
     * @return
     */
    @Override
    public ResponseData icLogin(String data) {
        ResponseData responseData = new ResponseData();
        JSONObject jsonObject = new JSONObject(data);
        String card = jsonObject.getString("card");
        LOGGER.info("IC卡登录" + data);
        AccountModel accountModel = accountMapper.queryAccountByIcCard(card);
        if(null == accountModel) {
            LOGGER.error("根据IC卡未找到用户");
            responseData.setErr_code(1);
            responseData.setErr_msg("未找到该IC卡对应的用户");
        }else{
            responseData.setErr_code(0);
            responseData.setErr_msg("IC卡登录成功");
            Account account = new Account();
            BeanUtils.copyProperties(accountModel, account);
            responseData.setData(account);
            LOGGER.info("IC卡登录成功");
        }
        return responseData;
    }

    /**
     * IC卡注册
     *
     * @param data
     * @return
     */
    @Override
    public ResponseData icRegister(String data) {
        ResponseData responseData = new ResponseData();
        LOGGER.info("IC卡注册" + data);
        JSONObject jsonObject = new JSONObject(data);
        String card = jsonObject.getString("card");
        String mobile = jsonObject.getString("mobile");
        String code = jsonObject.getString("code");
        //验证码模块
        boolean sms = this.verificationSms(mobile, code);
        if(!sms){
            responseData.setErr_code(1);
            responseData.setErr_msg("验证验证码失败");
            LOGGER.error("验证验证码失败" + mobile + "-" + code);
            return responseData;
        }
        AccountModel accountModel = accountMapper.queryAccountByPhone(mobile);
        if(null == accountModel){
            LOGGER.info("不存在用户，注册");
            AccountModel ac = new AccountModel();
            ac.setAccountId(mobile);
            ac.setNickName("");
            ac.setSex(SexTypeEnum.unknow.getValue());
            ac.setStatus(StatusTypeEnum.normal.getValue());
            ac.setCurrentProfit(0);
            ac.setDeliverCount(0);
            ac.setTotalProfit(0);
            ac.setWxsOpenId("");
            ac.setFaceToken("");
            ac.setIcCard(card);
            ac.setCreateTime(DateUtils.getNowTime());
            accountMapper.register(ac);
            AccountModel aM = accountMapper.queryAccountByPhone(mobile);
            Account account = new Account();
            BeanUtils.copyProperties(aM, account);
            responseData.setErr_code(0);
            responseData.setErr_msg("IC卡注册成功");
            responseData.setData(account);
            LOGGER.info("IC卡注册成功");
        }else{
            LOGGER.info("存在用户，icCard");
            accountModel.setIcCard(card);
            accountMapper.updateAccountModel(accountModel);
            Account account = new Account();
            BeanUtils.copyProperties(accountModel, account);
            responseData.setErr_code(0);
            responseData.setErr_msg("IC卡注册成功");
            responseData.setData(account);
            LOGGER.info("IC卡信息更新成功");
        }
        return responseData;
    }

    /**
     * 获取用户信息
     *
     * @param data
     * @return
     */
    @Override
    public ResponseData getUser(String data) {
        ResponseData responseData = new ResponseData();
        LOGGER.info("获取用户信息" + data);
        JSONObject jsonObject = new JSONObject(data);
        String uid = jsonObject.getString("uid");
        AccountModel accountModel = accountMapper.queryAccountByUid(uid);
        if(null == accountModel){
            LOGGER.info("不存在用户");
            responseData.setErr_code(1);
            responseData.setErr_msg("不存在用户");
        }else{
            Account account = new Account();
            BeanUtils.copyProperties(accountModel, account);
            responseData.setErr_code(0);
            responseData.setErr_msg("获取用户信息成功");
            responseData.setData(account);
            LOGGER.info("获取用户信息成功");
        }
        return responseData;
    }

    /**
     * 扫码识别设备登录
     *
     * @param data
     * @return
     */
    @Override
    public ResponseData scanDevice(String data) {
        ResponseData responseData = new ResponseData();
        LOGGER.info("扫码识别设备登录" + data);
        JSONObject jsonObject = new JSONObject(data);
        String uid = jsonObject.getString("uid");
        String deviceId = jsonObject.getString("device_id");
        AccountModel accountModel = accountMapper.queryAccountByUid(uid);
        if(null == accountModel){
            LOGGER.info("不存在用户");
            responseData.setErr_code(1);
            responseData.setErr_msg("不存在用户");
        }else{
            Account account = new Account();
            BeanUtils.copyProperties(accountModel, account);
            Map<String, Object> map = new HashMap<>();
            map.put("data_type", "user");
            map.put("data", account);
            //调用websocket链接推送消息
            boolean sendResult = WebsocketServer.sendMsg(JSON.toJSONString(map), deviceId);
            if(sendResult){
                //成功
                responseData.setErr_code(0);
                responseData.setErr_msg("向终端设备推送用户信息成功");
            }else{
                responseData.setErr_code(1);
                responseData.setErr_msg("向终端设备推送用户信息失败");
            }
        }
        return responseData;
    }

    /**
     * 验证码验证
     * @param mobile
     * @param code
     */
    private boolean verificationSms(String mobile, String code) {
    	try {
            CaptchaModel cm = captchaMapper.queryByPhoneNo(mobile);

            if(null == cm){
                LOGGER.error("验证码验证失败：未找到手机号" + mobile + "的发送短信验证码记录");
                return false;
            }
            if(!code.equals(cm.getCaptchaCode())){
                LOGGER.error("验证码验证失败：验证码不匹配" + mobile + "，数据库验证码" + cm.getCaptchaCode() + "，请求验证码" + code);
                return false;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date sendDate = sdf.parse(cm.getSendTime());
            Calendar sendCal = Calendar.getInstance();
            sendCal.setTime(sendDate);
            sendCal.add(Calendar.MINUTE, 10);
            Calendar nowCal = Calendar.getInstance();
            nowCal.compareTo(sendCal);
            if(nowCal.compareTo(sendCal) > 0){
                LOGGER.error("验证码验证失败：验证码已经超过有效期" + mobile + "-" + code);
                return false;
            }

            return true;
		} catch (Exception e) {
            LOGGER.error("验证验证码异常" + mobile + "-" + code, e);
            return false;
		}

    }

    /**
     * 百度AI，单实例使用 避免重复获取access_token
     */
    AipFace client = null;

    private AipFace getClient() {
        if(null == client){
            client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);
        }
        return client;
    }

}