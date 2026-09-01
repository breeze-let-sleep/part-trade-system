package com.hyltest.utils;

/**
 * 当前线程的操作用户ID的工具类
 */
public class CurrentHolder {

    /*
    右边 new ThreadLocal<>() 是真实的ThreadLocal 对象；
    左边 CURRENT_LOCAL 变量就是外部强引用；
    只要这个类不卸载，CURRENT_LOCAL 就一直存在，强引用永远不断。
     */
    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }

    public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }

    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}