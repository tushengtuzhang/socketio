package com.zts.socketio.ali.entity;

import lombok.Data;

/**
 * @author zhangtusheng
 */
@Data
public class Result {
    private String formattedText;
    private String location;
    private String poiPredict;
    private String rtDetail;
    private String segmentAddr;
    private String structExtra;
    private String structText;
    private String wgs84Location;
    private String zipCode;
}
