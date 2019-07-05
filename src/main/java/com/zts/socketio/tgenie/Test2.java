package com.zts.socketio.tgenie;

import net.sf.json.JSONObject;
import okhttp3.*;
import okio.ByteString;
import org.springframework.util.DigestUtils;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Test2 {
    static int receiveTimeCount =0;

    public static void main(String[] args) {

        String publicKey="ea07ab10de434a6c96765b923f572096";
        String secretKey="1E3F50FB8D5536F2F321F2D7D7E5F4F0";

        String str ="{\n" +
                "\"asr\": {\n" +
                "\"res\": \"comm\"\n" +
                "},\n" +
                "\"audio\": {\n" +
                "\"audioType\": \"wav\",\n" +
                "\"sampleRate\": 16000\n" +
                "},\n" +
                "\"dialog\":{\n" +
                "\"productId\":914007162\n" +
                "}\n" +
                "}";

        Map<String,String> map = new TreeMap<String,String>(){{
            put("asr","");
            put("dialog","");
            put("audio","");
        }};

        String collect = map.keySet().stream().collect(Collectors.joining());

        String sign = DigestUtils.md5DigestAsHex(secretKey.concat(collect).getBytes());


        OkHttpClient client = new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).build();

        //构造request对象
        Request request = new Request.Builder().url("ws://api.tgenie.cn/runtime/v2/recognize").build();

        //new 一个websocket调用对象并建立连接
        client.newWebSocket(request, new WebSocketListener() {

            public void onOpen(final WebSocket webSocket, Response response) {
                //保存引用，用于后续操作
                //webSocketClient = webSocket;
                //打印一些内容
                System.out.println("client onOpen");

                //注意下面都是write线程回写给客户端
                //建立连接成功后，发生command 1给服务器端

                System.out.println(System.currentTimeMillis());

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("route","ASR");
                jsonObject.put("authorization",publicKey);
                jsonObject.put("sign",sign);
                jsonObject.put("json",str);

                webSocket.send(jsonObject.toString());
                try {
                    webSocket.send( ByteString.of(readFile()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                webSocket.send( ByteString.of("".getBytes()));
            }

            @Override
            public void onMessage(WebSocket webSocket, String message) {
                System.out.println("client onMessage " + receiveTimeCount + ", " + message);

                System.out.println(System.currentTimeMillis());

                receiveTimeCount++;
            }

            public void onClosing(WebSocket webSocket, int code, String reason) {
                System.out.println("client onClose");
                System.out.println("code:" + code + " reason:" + reason);

                System.exit(0);

            }

            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                //发生错误时会回调到这
                System.out.println("client onFailure");
                System.out.println("throwable info is: " + t);

                this.onClosed(webSocket, 1, t.getMessage());
            }
        });
    }

    private static byte[]  readFile() throws Exception {
        File f = new File("D:\\DSAdpcmPlayer\\nls-sample-16k.wav");
        if(!f.exists()){
            throw new FileNotFoundException("D:\\DSAdpcmPlayer\\201904290833551930103.wav");
        }
        try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length())){
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while(-1 != (len = in.read(buffer,0,buf_size))){
                bos.write(buffer,0,len);
            }
            return bos.toByteArray();
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
