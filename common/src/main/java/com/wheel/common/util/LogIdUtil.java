package com.wheel.common.util;

import lombok.extern.log4j.Log4j2;

/**
 * 类LogIdUtil.java的实现描述：LOG id
 *
 */
@Log4j2
public class LogIdUtil {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static void setLogId(String logId) {
        threadLocal.set(logId);
    }

    public static String getlogId() {
        if (threadLocal.get() == null) {
            return null;
        }
        return threadLocal.get();
    }


}
