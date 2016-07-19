package com.ihesen.think.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * author: ihesen on 2016/4/22 14:39
 * email: hesen@ichsy.com
 */
public class IOUtils {
    /**
     * 关闭流
     */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                LogUtil.hLog().e(e);
            }
        }
        return true;
    }
}
