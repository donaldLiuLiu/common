package com.sc.common.vo;

public class BasicJsonResult<T> extends JsonResult {
    protected BasicJsonResult() { }
    private T data;


    public T getData() {
        return data;
    }

    public BasicJsonResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}
