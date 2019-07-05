package com.zts.socketio.controller;

import com.zts.socketio.service.AliAudioToTextService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangtusheng
 */
@RestController
@RequestMapping("/audioToText")
public class AudioToTextController {

    @Resource
    private AliAudioToTextService aliAudioToTextService;


}
