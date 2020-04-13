package com.sc.common.number;

import java.util.HashMap;
import java.util.Map;

public class NumberUnitFormat {

    public static Map<Integer, String> CONV = new HashMap<>();
    static { //thread
        CONV.put(1, "十");
        CONV.put(2, "百");
        CONV.put(3, "K");
        CONV.put(4, "W");
    }

    public static String convert(Integer fromNum, Integer topNum, Long numVal) {
        if(numVal == null || numVal <= 0) return "0";
        Integer p = topNum<0?4:topNum;
        String retVal = numVal + "";
        for(; p>=fromNum; p--) {
            Integer bian = pow10(p);
            if(bian <= numVal) {
                Long zs = numVal / bian;
                retVal = zs + "";
                if(p >= 1) {
                    Long remainder = remainder(numVal % bian, p - 1);
                    if(remainder != -1) {
                        retVal += "." + remainder;
                    }
                    retVal += CONV.get(p);
                }
                break;
            }
        }
        return retVal;
    }
    private static Long remainder(Long numRemain, Integer p) {
        if(numRemain >= pow10(p)) {
            return numRemain / pow10(p);
        } else {
            return -1L;
        }
    }
    private static Integer pow10(int num) {
        return Double.valueOf(Math.pow(10, num)).intValue();
    }

    /**
     *
     * @param o
     * @throws NumberFormatException
     * @return
     */
    public static Long convert2Long(Object o) {
        if(o == null) throw new NumberFormatException("不能为null");
        if(o instanceof Number) return ((Number) o).longValue();
        return Long.parseLong(String.valueOf(o));
    }
    /**
     *
     * @param o
     * @throws NumberFormatException
     * @return
     */
    public static Long convert2Long(Object o, Long nullDefault) {
        if(o == null) return nullDefault;
        if(o instanceof Number) return ((Number) o).longValue();
        return Long.parseLong(String.valueOf(o));
    }

    public static boolean convert2Bool(Object o) {
        if(o == null) return false;
        if (o.equals(Boolean.FALSE) || o instanceof String && ((String)o).equalsIgnoreCase("false")) {
            return false;
        }
        if (o.equals(Boolean.TRUE) || o instanceof String && ((String)o).equalsIgnoreCase("true")) {
            return true;
        }
        throw new RuntimeException("转化为bool失败");
    }

    //es 用  Long 转 String
    public static String esLong2Str(Long val) {
        if(val==null) return null;
        return val+"";
    }
    //es 用 String 转 Long
    public static Long esStr2Long(String val) {
        if(val==null) return null;
        return convert2Long(val);
    }

}
