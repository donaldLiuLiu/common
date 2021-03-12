package com.sc.common.exception.handler;


import com.sc.common.constant.CommonConstants;
import com.sc.common.enums.JsonResultEnum;
import com.sc.common.exception.BizException;
import com.sc.common.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@Slf4j
@ControllerAdvice
public class FlExceptionHandler {

    /**
     * Biz异常
     * @param ex
     * @return
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public JsonResult handlerBizException(BizException ex) {
        return JsonResult.buildFailedResult(ex.getExceptionCodeWith(null), ex.getMessage());
    }


    /**
     * 参数校验异常
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public JsonResult handlerBindException(BindException ex) {
        return JsonResult.buildFailedResult(JsonResultEnum.FAIL.getCode(),
                                            ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public JsonResult handlerValidateException(MethodArgumentNotValidException  ex) {
        return JsonResult.buildFailedResult(JsonResultEnum.FAIL.getCode(),
                                            ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public JsonResult handlerArgTypeException(MethodArgumentTypeMismatchException e) {
        return JsonResult.buildFailedResult(JsonResultEnum.FAIL.getCode(), CommonConstants.ARG_TYPE_ERROR);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult exceptionHandler(Exception ex) {
        log.error("exception-log:" + ex.getMessage(), ex);
        return JsonResult.buildFailedResult(JsonResultEnum.FAIL.getCode(), CommonConstants.SYSTEM_ERROR);
    }

}
