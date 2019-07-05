package com.zts.socketio.tgenie.response;

import lombok.Data;

/**
 * @author zhangtusheng
 */
@Data
public class AddressResponse {

    private String[] address;
    private String level;
    private String type;

}
