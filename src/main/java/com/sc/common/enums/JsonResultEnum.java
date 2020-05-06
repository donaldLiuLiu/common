package com.sc.common.enums;

public enum JsonResultEnum {
    SUCCESS("1"),
    FAIL("-1"),
    PERMISSION_DENIED("403");

    private String code;

    JsonResultEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
