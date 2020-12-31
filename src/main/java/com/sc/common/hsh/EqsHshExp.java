package com.sc.common.hsh;

import com.sc.common.utils.ObjectUtils;

public class EqsHshExp {
    private int code;
    private boolean isPrimary;
    private Integer wing;
    private String name;
    private Bean2Cool cool;
    private int[] codes;
    private Bean2Cool[] cools;

    public EqsHshExp(int code, boolean isPrimary, Integer wing, String name, Bean2Cool cool, int[] codes, Bean2Cool[] cools) {
        this.code = code;
        this.isPrimary = isPrimary;
        this.wing = wing;
        this.name = name;
        this.cool = cool;
        this.codes = codes;
        this.cools = cools;
    }

    /**
     * equals重写的模板样例
     *  1. x.equals(x)
     *  2. o is null; return false
     *  3. x.equals(y) ==> y.equals(x)
     *  4. x.equals(y); y.equals(x) ==> x.equals(z)
     * Object.equals(Object o) { return this==o;} //比较对象的引用地址
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        //1.直接比较引用值; for 1
        if(this == o) return true;
        //2.判空; for 2
        if(o == null) return false;
        //3.比较Class 或者 instanceof
        if(this.getClass() != o.getClass()) return false; //1.比较Class: for 3
        //if(!(o instanceof EqsHshExp)) return false; //2.instanceof: 违背3,允许o是子类型
        EqsHshExp that = (EqsHshExp) o;
        //4.比较字段值
        // 1).对于primitive类型(byte,short,int,long,char,boolean,float,double,void)，通过 == 比较
        // 2).declared class(如primitive包装类型, String, 自定义class), 数组(primitive数组, declared class数组)
        //   调用ObjectUtils.objEquals方法
        boolean flag = this.code==that.code;        //int,primitive
        flag &= this.isPrimary==that.isPrimary;     //boolean,primitive
        flag &= ObjectUtils.objEquals(this.wing, that.wing);    //Integer
        flag &= ObjectUtils.objEquals(this.name, that.name);    //String
        flag &= ObjectUtils.objEquals(this.cool, that.cool);    //自定义class
        flag &= ObjectUtils.objEquals(this.codes, that.codes);  //int[],primitive数组
        flag &= ObjectUtils.objEquals(this.cools, that.cools);  //Bean2Cool[],declared class数组
        //5.super的equals;如果基类是Object不需要调用super.equals
        //如果当前类时子类,需要调用基类的equals,则需要调用
        //flag &= super.equals(o);
        return flag;
    }


    /**
     * note: 如果equals，则hashCode一定相等
     * 计算方法: 取equals中用到的若干字段计算hashcode值并将这些值按照一定规则组合起来
     * Object中hashCode默认实现满足: Object.equals返回相等，则Object.hashCode也返回相同的int值
     * @return
     */
    @Override
    public int hashCode() {
        //1).对于primitive类型(byte,short,int,long,char,boolean,float,double,void)，使用其包装器的hashCode方法
        //2).declared class(如primitive包装类型, String, 自定义class), 数组(primitive数组, declared class数组)
        //   调用ObjectUtils.objHashCode方法
        int hashCode = Integer.hashCode(this.code);
        hashCode = 29*hashCode + ObjectUtils.objHashCode(name);
        hashCode = 29*hashCode + ObjectUtils.objHashCode(cool);
        hashCode = 29*hashCode + ObjectUtils.objHashCode(codes);
        hashCode = 29*hashCode + ObjectUtils.objHashCode(cools);
        //如果equals中使用了super,则使用super
        //hashCode = 29*hashCode + super.hashCode();
        return hashCode;
    }


    public static void main(String argv[]) {
        /** EqsHshExp.equals(EqsHshExp)
         * */
        EqsHshExp exp1 = new EqsHshExp(1, true,
                1, "name", new Bean2Cool(1), new int[]{1}, new Bean2Cool[]{new Bean2Cool(2)});

        EqsHshExp exp2 = new EqsHshExp(1, true,
                1, "name", new Bean2Cool(1), new int[]{1}, new Bean2Cool[]{new Bean2Cool(2)});
        System.out.println("exp1 == exp2: " + exp1.equals(exp2));

        /**EqsHshExp.equals(EqsHshExpSub)
         * */
        EqsHshExpSub sub1 = new EqsHshExpSub(2, 1, true,
                1, "name", new Bean2Cool(1), new int[]{1}, new Bean2Cool[]{new Bean2Cool(2)});
        System.out.println("exp1 == sub1: " + exp1.equals(sub1));

        System.out.println("exp1 hashCode: " + exp1.hashCode());

    }




}
