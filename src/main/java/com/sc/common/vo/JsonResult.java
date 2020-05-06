package com.sc.common.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sc.common.enums.JsonResultEnum;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class JsonResult {

    private String message;

    private String code;

    private boolean success;

    protected JsonResult() {}

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public boolean getSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }


    public static <T> JsonResult buildSuccessResult(T data) {
        BasicJsonResult<T> result = new BasicJsonResult<>();
        result.setSuccess(true);
        result.setCode(JsonResultEnum.SUCCESS.getCode());
        result.setMessage("SUCCESS");
        result.setData(data);
        return result;
    }

    public static <T> JsonResult buildSuccessResult(IPage<T> page) {
        BasicJsonResult<PageJsonResultVo> result = new BasicJsonResult<>();
        result.setSuccess(true);
        result.setCode(JsonResultEnum.SUCCESS.getCode());
        result.setMessage("SUCCESS");
        PageJsonResultVo<T> pageJsonResultVo = new PageJsonResultVo<>();
        pageJsonResultVo.setPage(page.getCurrent())
                        .setPageSize(page.getSize())
                        .setPages(page.getPages())
                        .setTotal(page.getTotal())
                        .setItems(page.getRecords());
        result.setData(pageJsonResultVo);
        return result;
    }

    protected static JsonResult buildResult(String code, String message, boolean success) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(code);
        jsonResult.setMessage(message);
        jsonResult.setSuccess(success);
        return jsonResult;
    }

    public static JsonResult buildSuccessResult(String message) {
        return buildResult(JsonResultEnum.SUCCESS.getCode(), message, true);
    }

    public static JsonResult buildFailedResult(String message) {
        return buildResult(JsonResultEnum.FAIL.getCode(), message, false);
    }






}
