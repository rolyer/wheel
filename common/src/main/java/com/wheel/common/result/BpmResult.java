package com.wheel.common.result;

import lombok.Data;

@Data
public class BpmResult extends BaseResVo {

    private Integer errorCode=0;
    private String errorMsg="OK";
    private Object data;


}
