package com.auts.lajitong.websocket;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket服务
 */
@Component
@ServerEndpoint(value = "/recoverysocket/{deviceId}")
public class WebsocketServer {

    private static final Logger LOGGER = LogManager.getLogger(WebsocketServer.class);

    private static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    /**
     * 连接事件 加入注解
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam(value = "deviceId") String deviceId, Session session) {
        LOGGER.info("有新的websocket链接建立" + deviceId);
        sessionMap.put(deviceId, session);

        Map<String, String> obj = new HashMap<>();
        obj.put("data_type", "heart");
        obj.put("data", "SUCCESS");
        session.getAsyncRemote().sendText(JSON.toJSONString(obj));
    }

    @OnClose
    public void onClose(@PathParam(value = "deviceId") String deviceId, Session session) {
        LOGGER.info("websocket链接关闭" + deviceId);
        sessionMap.remove(deviceId);
    }

    /**
     * 接收客户端消息
     * @param deviceId
     * @param message
     */
    @OnMessage
    public void onMessage(@PathParam(value = "deviceId") String deviceId, String message) {
        LOGGER.info("接收到websocket消息" + deviceId + "，" + message);
        Session session = sessionMap.get(deviceId);

        Map<String, String> obj = new HashMap<>();
        obj.put("data_type", "heart");
        obj.put("data", "SUCCESS");
        session.getAsyncRemote().sendText(JSON.toJSONString(obj));
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        LOGGER.error("websocket异常", throwable);
        try {
            session.close();
        } catch (IOException e) {
            LOGGER.error("websocket关闭异常", e);
        }
    }

    /**
     * 发送自定义消息
     */
    public static boolean sendMsg(String message, @PathParam("deviceId") String deviceId) {
        LOGGER.info("推送消息到" + deviceId + "，推送内容:" + message);
        Session session = sessionMap.get(deviceId);
        if(null == session || !session.isOpen()){
            LOGGER.error("推送消息失败，链接不存在或者已断开");
            return false;
        }
        // getAsyncRemote()和getBasicRemote()异步与同步
        RemoteEndpoint.Async async = session.getAsyncRemote();
        //发送消息
        async.sendText(message);
        return true;
    }

    /**
     * 广播消息
     */
    public static boolean sendMsg(String message) {
        LOGGER.info("推送内容:" + message);
        if(sessionMap.size() > 0){
            for(Map.Entry<String, Session> map : sessionMap.entrySet()){
                RemoteEndpoint.Async async = map.getValue().getAsyncRemote();
                async.sendText(message);
            }
            return true;
        }else{
            LOGGER.error("无websocket链接，广播消息失败:" + message);
            return false;
        }
    }

}
