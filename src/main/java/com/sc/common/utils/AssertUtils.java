package com.sc.common.utils;

import com.sc.common.exception.BizException;
import java.util.function.Supplier;

public abstract class AssertUtils {

    /**
     * 是否为true, if expression不为true throw exception
     * @param expression
     * @param message
     * @param exceptionCode
     */
    public static void isTrue(boolean expression,
                              Supplier<String> message,
                              Supplier<String> exceptionCode) {
        if (!expression) {
            throwsExp(message, exceptionCode);
        }
    }

    /**
     * 如果obj==null,抛异常
     * @param obj
     * @param message
     * @param exceptionCode
     */
    public static void ifNull(Object obj,
                              Supplier<String> message,
                              Supplier<String> exceptionCode) {
        if(obj == null) {
            throwsExp(message, exceptionCode);
        }
    }

    /**
     * 如果obj==null,抛异常
     * @param obj
     * @param message
     * @param exceptionCode
     */
    public static void notNull(Object obj,
                              Supplier<String> message,
                              Supplier<String> exceptionCode) {
        ifNull(obj, message, exceptionCode);
    }

    /**
     * 如果expression为true, 抛异常
     * @param expression
     * @param message
     * @param exceptionCode
     */
    public static void ifTrue(boolean expression,
                              Supplier<String> message,
                              Supplier<String> exceptionCode) {
        isTrue(!expression, message, exceptionCode);
    }

    private static Supplier<String> nullSafeGet(Supplier<String> supplier) {
        return supplier != null ? supplier : () -> "";
    }

    private static void throwsExp(Supplier<String> message,
                                  Supplier<String> exceptionCode) {
        throw new BizException(nullSafeGet(message), nullSafeGet(exceptionCode));
    }


}
