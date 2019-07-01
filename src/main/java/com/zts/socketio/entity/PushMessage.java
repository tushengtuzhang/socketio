package com.zts.socketio.entity;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PushMessage {
    private String loginUserNum;

    private String content;

}
