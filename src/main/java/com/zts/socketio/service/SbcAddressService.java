package com.zts.socketio.service;

import com.google.gson.Gson;
import com.zts.socketio.tgenie.response.AddressResponse;
import com.zts.socketio.utils.HttpUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangtusheng
 */
@Service
public class SbcAddressService {
    private String url="http://nlu-alpha.talkinggenie.com/ccdmnlu/alarm/event_extraction";
    private Gson gson= new Gson();

    public AddressResponse get(String address) throws Exception {

        Map<String,String> headers=new HashMap<>();
        headers.put("Content-Type","application/json");
        String json = HttpUtils.post(url,address,headers);

        return gson.fromJson(json,AddressResponse.class);
    }

}
