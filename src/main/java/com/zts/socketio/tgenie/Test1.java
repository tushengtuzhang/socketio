package com.zts.socketio.tgenie;

import okhttp3.*;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Test1 {

    public static void main(String[] args) {
        String json = "{\n" +
                "\"asr\": {\n" +
                "\"res\": \"aicallcenter\",\n" +
                "\"enablePunctuation\": true,\n" +
                "\"language\": \"zh-CN\"\n" +
                "},\n" +
                "\"dialog\": {\n" +
                "\"productId\": \"914007162\"\n" +
                "},\n" +
                "\"audio\": {\n" +
                "\"audioType\": \"wav\",\n" +
                "\"channel\": 1,\n" +
                "\"sampleBytes\": 2,\n" +
                "\"sampleRate\": 8000\n" +
                "}\n" +
                "}";

        String publicKey="ea07ab10de434a6c96765b923f572096";
        String secretKey="1E3F50FB8D5536F2F321F2D7D7E5F4F0";

        //以上的json最外层参数名分别是asr，dialog，audio。用他们做ASCII码升序排列
        //算出来的值是asraudiodialog
        Map<String, String> map = new TreeMap<String, String>() {{
            put("asr", "");
            put("dialog", "");
            put("audio", "");
        }};
        String collect = map.keySet().stream().collect(Collectors.joining());
        //md5加密  //secret key 1E3F50FB8D5536F2F321F2D7D7E5F4F0
        String sign = DigestUtils.md5DigestAsHex(secretKey.concat(collect).getBytes());

        MediaType mediaType = MediaType.parse("audio/wav");
        File file=new File("D:/DSAdpcmPlayer/201904290833551930103.wav");
        System.out.println(file.length());
        RequestBody fileBody=RequestBody.create(file,mediaType);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "audio", fileBody)
                .addFormDataPart("param", json)
                .build();

        Request request = new Request.Builder()
                .addHeader("Authorization", publicKey)
                .addHeader("sign", sign)
                .post(requestBody)
                .url("https://api.tgenie.cn/api/v1/ba/asr").build();

        try {
            long startTime=System.currentTimeMillis();
            Response response =new OkHttpClient().newCall(request).execute();
            ResponseBody responseBody=response.body();
            String string = responseBody.string();
            System.out.println(string);
            long endTime=System.currentTimeMillis();
            System.out.println(endTime-startTime);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
