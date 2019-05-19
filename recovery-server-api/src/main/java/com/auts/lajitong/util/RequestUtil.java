package com.auts.lajitong.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 封装请求工具类
 *
 * @author zqh
 */
public class RequestUtil {

    private static final Logger LOGGER = LogManager.getLogger(RequestUtil.class);

    public static String buildReq(HttpServletRequest request) {
        String reqUri = request.getRequestURI();
        BufferedReader br = null;
        try {
            br = request.getReader();
            String str, wholeStr = "";
            while((str = br.readLine()) != null){
                wholeStr += str;
            }
            return wholeStr;
        } catch (IOException e) {
            LOGGER.error("请求参数获取异常：" + reqUri, e);
        }
        return null;
    }
}
