package com.wheel.common.util;


import com.wheel.common.result.ReturnCodeEnum;

public class BusinessException extends RuntimeException {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = -5147581356988161519L;
    /**
     * 错误码
     */
    private ReturnCodeEnum returnCodeEnum;

    private Exception exception;
    /**
     * 错误信息
     */
    private String message;


    public BusinessException(ReturnCodeEnum returnCodeEnum, Exception e) {
        super();
        this.returnCodeEnum = returnCodeEnum;
        this.exception = e;
    }

    public BusinessException(ReturnCodeEnum returnCodeEnum, String message, Exception e) {
        super(message);
        this.returnCodeEnum = returnCodeEnum;
        this.message = message;
        this.exception = e;
    }

    public BusinessException(ReturnCodeEnum returnCodeEnum, String message, String logId, Exception e) {
        super(message);
        this.returnCodeEnum = returnCodeEnum;
        this.message = message + ",logId:" + logId;
        this.exception = e;
    }

    public BusinessException(ReturnCodeEnum returnCodeEnum, String message) {
        super(message);
        this.returnCodeEnum = returnCodeEnum;
        this.message = message;
    }

    public BusinessException(ReturnCodeEnum returnCodeEnum, String message, String logId) {
        super(message);
        this.returnCodeEnum = returnCodeEnum;
        this.message = message + ",logId:" + logId;
    }


    public BusinessException(ReturnCodeEnum returnCodeEnum) {
        super(returnCodeEnum.getDesc());
        this.returnCodeEnum = returnCodeEnum;
        // this.message = ReturnCodeEnum.getDesc();
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

    public Exception getException() {
        return exception;
    }
}
