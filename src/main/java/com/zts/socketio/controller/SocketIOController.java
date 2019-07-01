package com.zts.socketio.controller;

import com.zts.socketio.entity.PushMessage;
import com.zts.socketio.service.SocketIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangtusheng
 */
@RestController
@RequestMapping("/socket")
public class SocketIOController {


    @Autowired
    private SocketIOService socketIOService;

    @GetMapping("/send")
    @ResponseBody
    public String send(){
        PushMessage pushMessage=new PushMessage("88","fuck!");
        socketIOService.pushMessageToUser(pushMessage);
        return "OK";
    }


    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello World!";
    }

    @RequestMapping("/hi")
    public String hi(){
        return "redirect:/index.html";
    }
}
