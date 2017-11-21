package com.wheel.common.result;


public class Result<T> extends BaseResult {

    private T data;


    public Result() {
        this(null);
    }

    public Result(T data) {
        if (data != null) {
            this.data = data;
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
