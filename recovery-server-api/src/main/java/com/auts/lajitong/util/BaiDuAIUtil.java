package com.auts.lajitong.util;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;

import java.util.HashMap;

public class BaiDuAIUtil {
    private static String APP_ID = "16320184";
    private static String API_KEY = "3CylMryHLt9Q3erAhF4mVVlH";
    private static String SECRET_KEY = "PyISO1W33nAUrk9PU7fFT5xArC1wbhHW";

    /**
     * 删除用户组
     */
    public static void deleteGroup() {
        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();

        String groupId = "01";
        // 删除用户组
        JSONObject res = client.groupDelete(groupId, options);
        System.out.println(res.toString(2));

    }

    public static void main(String[] args){
        deleteGroup();
    }
}
