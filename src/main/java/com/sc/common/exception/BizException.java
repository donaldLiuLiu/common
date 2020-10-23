package com.sc.common.exception;

import java.util.Optional;
import java.util.function.Supplier;

public class BizException extends RuntimeException {

    private Supplier<String> messageSupplier;
    private Supplier<String> exceptionCodeSupplier;

    public BizException() {
    }

    public BizException(Supplier<String> messageSupplier) {
        super(Optional.ofNullable(messageSupplier).orElse(() -> "").get());
        this.messageSupplier = Optional.ofNullable(messageSupplier).orElse(() -> "");
    }

    public BizException(Supplier<String> messageSupplier, Supplier<String> exceptionCodeSupplier) {
        super(Optional.ofNullable(messageSupplier).orElse(() -> "").get());
        this.messageSupplier = Optional.ofNullable(messageSupplier).orElse(() -> "");
        this.exceptionCodeSupplier = Optional.ofNullable(exceptionCodeSupplier).orElse(() -> "");
    }

    public BizException(Throwable e) {
        super(e);
    }

    public BizException(Throwable e, Supplier<String> messageSupplier) {
        super(Optional.ofNullable(messageSupplier).orElse(() -> "").get(), e);
        this.messageSupplier = Optional.ofNullable(messageSupplier).orElse(() -> "");
    }

    public BizException(Throwable e, Supplier<String> messageSupplier, Supplier<String> exceptionCodeSupplier) {
        super(Optional.ofNullable(messageSupplier).orElse(() -> "").get(), e);
        this.messageSupplier = Optional.ofNullable(messageSupplier).orElse(() -> "");
        this.exceptionCodeSupplier = Optional.ofNullable(exceptionCodeSupplier).orElse(() -> "");
    }


    public String getExceptionCode() {
        return this.exceptionCodeSupplier.get();
    }


    public static void main(String argv[]) {
        Supplier<String> s = null;
        BizException e = new BizException(s, () -> "403");
        System.out.println(e.getMessage());
        System.out.println(e.getExceptionCode());
    }

}
