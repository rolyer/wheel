package com.wheel.common.util;


import com.wheel.common.result.ReturnCodeEnum;

public class SysException extends RuntimeException {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = -5147581356988161519L;
    /**
     * 错误码
     */
    private ReturnCodeEnum returnCodeEnum;
    /**
     * 错误信息
     */
    private String message;

    public SysException(ReturnCodeEnum returnCodeEnum, String message) {
        super(message);
        this.returnCodeEnum = returnCodeEnum;
        this.message = message;
    }

    public SysException(ReturnCodeEnum returnCodeEnum) {
        super(returnCodeEnum.getDesc());
        this.returnCodeEnum = returnCodeEnum;
    }

    public SysException(String msg) {
        super(msg);
    }

    public ReturnCodeEnum getReturnCodeEnum() {
        return returnCodeEnum;
    }

    public void setReturnCodeEnum(ReturnCodeEnum returnCodeEnum) {
        this.returnCodeEnum = returnCodeEnum;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
