package com.zts.socketio.ali;

import com.alibaba.nls.client.protocol.asr.SpeechTranscriberListener;
import com.alibaba.nls.client.protocol.asr.SpeechTranscriberResponse;
import com.zts.socketio.service.SocketIOService;
import org.springframework.stereotype.Service;

/**
 * 语音转换监听
 * @author zhangtusheng
 */
@Service
public class AliSpeechTranscriberListener extends SpeechTranscriberListener {
    private SocketIOService socketIOService;

    @Override
    public void onTranscriberStart(SpeechTranscriberResponse speechTranscriberResponse) {
        System.out.println(speechTranscriberResponse);
    }

    @Override
    public void onSentenceBegin(SpeechTranscriberResponse speechTranscriberResponse) {
        System.out.println(speechTranscriberResponse);
    }

    /**识别出一句话.服务端会智能断句,当识别到一句话结束时会返回此消息*/
    @Override
    public void onSentenceEnd(SpeechTranscriberResponse speechTranscriberResponse) {
        System.out.println(speechTranscriberResponse);
        //TODO 返回数据

    }

    /**识别出中间结果.服务端识别出一个字或词时会返回此消息.仅当setEnableIntermediateResult(true)时,才会有此类消息返回*/
    @Override
    public void onTranscriptionResultChange(SpeechTranscriberResponse speechTranscriberResponse) {
        System.out.println(speechTranscriberResponse);
    }

    /**识别完毕*/
    @Override
    public void onTranscriptionComplete(SpeechTranscriberResponse speechTranscriberResponse) {
        System.out.println(speechTranscriberResponse);
    }

    @Override
    public void onFail(SpeechTranscriberResponse speechTranscriberResponse) {
        System.out.println(speechTranscriberResponse);
    }
}
