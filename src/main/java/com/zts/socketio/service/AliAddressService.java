package com.zts.socketio.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zts.socketio.ali.entity.AddressResponseBody;
import com.zts.socketio.utils.HttpUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangtusheng
 */
@Service
public class AliAddressService {
    private String url="https://assistant.ai.alibaba.com/addr/api";
    private Gson gson= new Gson();

    public AddressResponseBody get(String address) throws Exception {

        Map<String,Object> params=new HashMap<>();
        params.put("default_prov","广西");
        params.put("default_city","");
        params.put("sid","geo_parser_custom_inf");
        params.put("output_type","1,2,3,4,5,6,8");
        params.put("token","75a2fa28b07d72b43a241786805c4a62");
        params.put("src","6000000485264447");

        params.put("addr",address);

        String json = HttpUtils.get(url, params);

        return gson.fromJson(json, AddressResponseBody.class);
    }
}
