package com.zts.socketio.service;

import com.zts.socketio.entity.PushMessage;

/**
 * @author zhangtusheng
 */
public interface SocketIOService {

    /**推送的事件*/
    public static final String PUSH_EVENT = "push_event";

    /** 启动服务*/
    void start() throws Exception;

    /** 停止服务*/
    void stop();

    /** 推送信息*/
    void pushMessageToUser(PushMessage pushMessage);
}
