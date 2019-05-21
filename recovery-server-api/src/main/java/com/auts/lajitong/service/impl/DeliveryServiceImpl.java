package com.auts.lajitong.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import com.auts.lajitong.model.common.ResponseData;
import com.auts.lajitong.model.dao.DeliveryOrderModel;
import com.auts.lajitong.model.dao.KnowDtlModel;
import com.auts.lajitong.model.dao.KnowObjModel;
import com.auts.lajitong.model.dao.ObjTypeModel;
import com.auts.lajitong.model.response.DeliveryOrder;
import com.auts.lajitong.model.response.ObjType;
import com.auts.lajitong.util.DateUtils;
import com.baidu.aip.imageclassify.AipImageClassify;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auts.lajitong.mapper.DeliveryMapper;
import com.auts.lajitong.service.DeliveryService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户投递相关
 * @author zqh
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private static final Logger LOGGER = LogManager.getLogger(DeliveryServiceImpl.class);

    @Autowired
    DeliveryMapper deliveryMapper;

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
    @Value("${delivery.image.path}")
    private String imageDir;

    /**
     * 百度AI，单实例使用 避免重复获取access_token
     */
    AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

    /**
     * 回收物品识别
     *
     * @param data
     * @return
     */
    @Transactional
    @Override
    public ResponseData know(String data) {
        ResponseData responseData = new ResponseData();
        LOGGER.info("回收物品识别" + data);
        JSONObject jsonObject = new JSONObject(data);
        String image = jsonObject.getString("image");
        String device_id = jsonObject.getString("device_id");
        int uid = jsonObject.getInt("uid");

        String savePath = saveImageFile(image);
        if(null == savePath){
            responseData.setErr_code(1);
            responseData.setErr_msg("识别图像保存异常");
            return responseData;
        }
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        HashMap<String, String> options = new HashMap<>();
        //百科词条数 1
        options.put("baike_num", "1");
        JSONObject res = client.advancedGeneral(savePath, options);
        LOGGER.info("请求百度通用物体识别，接收到返回信息：" + res.toString());
//        System.out.println(res.toString(2));
        int error_code = res.getInt("error_code");
        if(0 != error_code){
            responseData.setErr_code(1);
            responseData.setErr_msg("图像识别失败");
            LOGGER.error("请求百度通用物体识别，失败");
        }else{
            JSONObject result = res.getJSONObject("result");
            JSONArray jsonArray = result.getJSONArray("result");
            //默认获取第一条
            JSONObject info = jsonArray.getJSONObject(0);
            String name = info.getString("keyword");
            //去智能识别物品信息表查询该物品所属分类
            KnowObjModel model = deliveryMapper.queryKnowObjByName(name);
            if(null == model){
                LOGGER.info("未识别出该物品所属分类");
                responseData.setErr_code(1);
                responseData.setErr_msg("未识别出该物品所属分类，请人工选择投递");
                //识别出新的物品
                KnowObjModel knowObj = new KnowObjModel();
                knowObj.setKnowName(name);
                knowObj.setObjType(0);
                deliveryMapper.saveKnowObj(knowObj);
                LOGGER.info("生成新的物品识别信息");
            }else if(0 == model.getObjType()){
                responseData.setErr_code(1);
                responseData.setErr_msg("未识别出该物品所属分类，请人工选择投递");
                LOGGER.info("已有该物品的识别信息，未标识其所属分类");
            }else{
                int objType = model.getObjType();
                ObjTypeModel typeModel = deliveryMapper.queryObjTypeByOid(objType);
                responseData.setErr_code(0);
                responseData.setErr_msg("回收物品识别成功");
                ObjType obj = new ObjType();
                BeanUtils.copyProperties(typeModel, obj);
                responseData.setData(obj);
                LOGGER.info("回收物品识别成功");
            }

            //保存此次用户投递物品识别记录
            KnowDtlModel kdm = new KnowDtlModel();
            kdm.setImagePath(savePath);
            kdm.setDeviceId(device_id);
            kdm.setUid(uid);
            kdm.setKnowName(name);
            kdm.setKnowTime(DateUtils.getNowTime());
            deliveryMapper.saveKnowDtl(kdm);
            LOGGER.info("保存用户投递物品识别记录成功");
        }
        return responseData;
    }

    /**
     * 获取用户投递记录
     *
     * @param data
     * @return
     */
    @Override
    public ResponseData orderList(String data) {
        ResponseData responseData = new ResponseData();
        LOGGER.info("获取用户投递记录" + data);
        JSONObject jsonObject = new JSONObject(data);
        int uid = jsonObject.getInt("uid");
        List<DeliveryOrderModel> orderList = deliveryMapper.queryDeliveryListByUser(uid);
        List<ObjTypeModel> typeList = deliveryMapper.queryObjTypes();
        Map<Integer, String> typeMap = new HashMap<>();
        for(ObjTypeModel type : typeList) {
            typeMap.put(type.getOid(), type.getObjectName());
        }
        List<DeliveryOrder> list = new ArrayList<>();
        for(DeliveryOrderModel model : orderList) {
            DeliveryOrder order = new DeliveryOrder();
            BeanUtils.copyProperties(model, order);
            //物品分类名称
            order.setS_order_type(typeMap.get(order.getOrderType()));
            list.add(order);
        }
        responseData.setErr_code(0);
        responseData.setErr_msg("获取用户投递记录成功");
        responseData.setData(list);
        return responseData;
    }

    private String saveImageFile(String image) {
        File dir = new File(imageDir);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String uuid = UUID.randomUUID().toString().replace("-","");
        String savePath = imageDir + "/" + uuid + ".jpg";
        LOGGER.info("识别图片保存目录" + savePath);
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.getDecoder().decode(image);
            File file=new File(savePath);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            LOGGER.error("识别图片保存服务器异常", e);
            return null;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    LOGGER.error("识别图片保存服务器输出流关闭", e);
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    LOGGER.error("识别图片保存服务器输出流关闭", e);
                }
            }
        }
        return savePath;
    }

}
