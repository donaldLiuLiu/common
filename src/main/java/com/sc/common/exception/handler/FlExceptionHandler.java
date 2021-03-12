package com.sc.common.exception.handler;


import com.sc.common.constant.CommonConstants;
import com.sc.common.enums.JsonResultEnum;
import com.sc.common.exception.BizException;
import com.sc.common.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@ControllerAdvice
public class FlExceptionHandler {

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public JsonResult handlerBizException(BizException ex) {
        return JsonResult.buildFailedResult(ex.getExceptionCodeWith(null), ex.getMessage());
    }


    @ExceptionHandler(BindException.class)
    @ResponseBody
    public JsonResult handlerBindException(BindException ex) {
        return JsonResult.buildFailedResult(JsonResultEnum.FAIL.getCode(),
                                            ex.getBindingResult().getFieldError().getDefaultMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult exceptionHandler(Exception ex) {
        log.error("exception-log:" + ex.getMessage(), ex);
        return JsonResult.buildFailedResult(JsonResultEnum.FAIL.getCode(), CommonConstants.SYSTEM_ERROR);
    }

}
