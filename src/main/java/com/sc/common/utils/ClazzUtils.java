package com.sc.common.utils;

public abstract class ClazzUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex) {
            // do nothing
        }
        if (cl == null) {
            cl = ClazzUtils.class.getClassLoader();
            if (cl == null) {
                try {
                    cl = ClassLoader.getSystemClassLoader();
                }
                catch (Throwable ex) {
                }
            }
        }
        return cl;
    }
}
