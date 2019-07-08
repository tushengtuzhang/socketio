package com.zts.socketio.service;

import com.alibaba.nls.client.AccessToken;
import com.alibaba.nls.client.protocol.InputFormatEnum;
import com.alibaba.nls.client.protocol.NlsClient;
import com.alibaba.nls.client.protocol.SampleRateEnum;
import com.alibaba.nls.client.protocol.asr.SpeechTranscriber;
import com.zts.socketio.ali.AliSpeechTranscriberListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhangtusheng
 */
@Service
public class AliAudioToTextService {

    private static String appKey = "n7WeQj4ACKglzInX";

    private static String accessKey="kqZpof5RsV52y28g";
    private static String accessKeySecret="JT6vtVNLqMWbq91O7xSN4ObVDo4ExQ";

    private String url="ws://nls-gateway.cn-shanghai-internal.aliyuncs.com/ws/v1";

    private static NlsClient client;

    @PostConstruct
    public void init() throws IOException {
        AccessToken accessToken = new AccessToken(accessKey,accessKeySecret);
        accessToken.apply();
        client = new NlsClient(accessToken.getToken());
    }

    public void audioToText(InputStream inputStream){

        process(inputStream);

    }

    public void process(InputStream inputStream) {
        SpeechTranscriber transcriber = null;
        try {

            //创建实例,建立连接
            transcriber = new SpeechTranscriber(client, new AliSpeechTranscriberListener());
            transcriber.setAppKey(appKey);
            //输入音频编码方式
            transcriber.setFormat(InputFormatEnum.PCM);
            //输入音频采样率
            transcriber.setSampleRate(SampleRateEnum.SAMPLE_RATE_16K);
            //是否返回中间识别结果
            transcriber.setEnableIntermediateResult(false);
            //是否生成并返回标点符号
            transcriber.setEnablePunctuation(true);
            //是否将返回结果规整化,比如将一百返回为100
            transcriber.setEnableITN(false);
            //此方法将以上参数设置序列化为json发送给服务端,并等待服务端确认
            transcriber.start();
            //语音数据来自声音文件用此方法,控制发送速率;若语音来自实时录音,不需控制发送速率直接调用 transcriber.sent(ins)即可
            transcriber.send(inputStream, 3200, 100);
            //transcriber.send(ins);
            //通知服务端语音数据发送完毕,等待服务端处理完成

            //transcriber.stop();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            if (null != transcriber) {
                transcriber.close();
            }
        }
    }


}
