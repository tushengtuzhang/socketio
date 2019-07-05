package com.zts.socketio.controller;

import com.zts.socketio.ali.entity.AddressResponseBody;
import com.zts.socketio.service.AliAddressService;
import com.zts.socketio.service.SbcAddressService;
import com.zts.socketio.tgenie.response.AddressResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhangtusheng
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AliAddressService aliAddressService;

    @Resource
    private SbcAddressService sbcAddressService;

    @GetMapping("/aliGet")
    @ResponseBody
    public AddressResponseBody aliGet(String address) throws Exception {
        return aliAddressService.get(address);
    }

    @PostMapping("/sbcGet")
    @ResponseBody
    public AddressResponse sbcGet(@RequestBody String query) throws Exception{
        return sbcAddressService.get(query);
    }
}
