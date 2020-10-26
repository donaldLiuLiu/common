package com.sc.common.utils;

public abstract class StringUtils {

    /**
     * 是否带有进制
     * @param str
     * @return
     */
    public static boolean isHexNumber(String str) {
        int index = (str.startsWith("-") ? 1 : 0);
        return (str.startsWith("0x", index) || str.startsWith("0X", index) || str.startsWith("#", index));
    }

    /**
     * 去除所有whitespace
     * @param str
     * @return
     */
    public static String trimAllWhitespace(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        int len = str.length();
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (!Character.isWhitespace(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
