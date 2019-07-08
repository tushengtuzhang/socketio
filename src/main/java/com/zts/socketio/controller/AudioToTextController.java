package com.zts.socketio.controller;

import com.zts.socketio.service.AliAudioToTextService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author zhangtusheng
 */
@RestController
@RequestMapping("/audioToText")
public class AudioToTextController {

    @Resource
    private AliAudioToTextService aliAudioToTextService;

    @PostMapping("/a")
    public String audioToText(MultipartFile file) throws IOException {
        aliAudioToTextService.audioToText(file.getInputStream());
        return "ok";
    }


}
