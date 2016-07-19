package com.ihesen.think.utils;

import android.util.Log;

import java.util.List;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 日志工具类
 */
public class LogUtil {
    public static boolean logFlag = true;  //Log开关

    public static HttpLoggingInterceptor.Level HttpLogLevel = logFlag ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;

    public static String tag = "TAG";
    private final static int logLevel = Log.VERBOSE;
    private String mClassName;

    private static LogUtil klog;

    /**
     * 写文件的锁对象
     */
    private static final Object mLogLock = new Object();

    private static final String HESEN = "hesen";

    private LogUtil(String name) {
        mClassName = name;
    }

    public static LogUtil hLog() {
        if (klog == null) {
            tag = HESEN;
            klog = new LogUtil(HESEN);
        }
        return klog;
    }

    /**
     * Get The Current Function Name
     *
     * @return
     */
    private String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(this.getClass().getName())) {
                continue;
            }
            return mClassName + "[ " + Thread.currentThread().getName() + ": " + st.getFileName() + ":" + st.getLineNumber() + " " + st.getMethodName() + " ]";
        }
        return null;
    }

    /**
     * The Log Level:i
     *
     * @param str
     */
    public void i(Object str) {
        if (logFlag) {
            if (logLevel <= Log.INFO) {
                String name = getFunctionName();
                if (name != null) {
                    Log.i(tag, name + " - " + str);
                } else {
                    Log.i(tag, str.toString());
                }
            }
        }

    }

    /**
     * The Log Level:d
     *
     * @param str
     */
    public void d(Object str) {
        if (logFlag) {
            if (logLevel <= Log.DEBUG) {
                String name = getFunctionName();
                if (name != null) {
                    Log.d(tag, name + " - " + str);
                } else {
                    Log.d(tag, str.toString());
                }
            }
        }
    }

    /**
     * The Log Level:V
     *
     * @param str
     */
    public void v(Object str) {
        if (logFlag) {
            if (logLevel <= Log.VERBOSE) {
                String name = getFunctionName();
                if (name != null) {
                    Log.v(tag, name + " - " + str);
                } else {
                    Log.v(tag, str.toString());
                }
            }
        }
    }

    /**
     * The Log Level:w
     *
     * @param str
     */
    public void w(Object str) {
        if (logFlag) {
            if (logLevel <= Log.WARN) {
                String name = getFunctionName();
                if (name != null) {
                    Log.w(tag, name + " - " + str);
                } else {
                    Log.w(tag, str.toString());
                }
            }
        }
    }

    /**
     * The Log Level:e
     *
     * @param str
     */
    public void e(Object str) {
        if (logFlag) {
            if (logLevel <= Log.ERROR) {
                String name = getFunctionName();
                if (name != null) {
                    Log.e(tag, name + " - " + str);
                } else {
                    Log.e(tag, str.toString());
                }
            }
        }
    }

    /**
     * The Log Level:e
     *
     * @param ex
     */
    public void e(Exception ex) {
        if (logFlag) {
            if (logLevel <= Log.ERROR) {
                Log.e(tag, "error", ex);
            }
        }
    }

    /**
     * The Log Level:e
     *
     * @param log
     * @param tr
     */
    public void e(String log, Throwable tr) {
        if (logFlag) {
            String line = getFunctionName();
            Log.e(tag, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + line + ":] " + log + "\n", tr);
        }
    }

    /**
     * 把Log存储到文件中
     *
     * @param log  需要存储的日志
     * @param path 存储路径
     */
    public static void log2File(String log, String path) {
        log2File(log, path, true);
    }

    public static void log2File(String log, String path, boolean append) {
        synchronized (mLogLock) {
            FileUtils.writeFile(log + "\r\n", path, append);
        }
    }

    public <T> void printList(List<T> list) {
        if (list == null || list.size() < 1) {
            return;
        }
        int size = list.size();
        i("---begin---");
        for (int i = 0; i < size; i++) {
            i(i + ":" + list.get(i).toString());
        }
        i("---end---");
    }

    public <T> void printArray(T[] array) {
        if (array == null || array.length < 1) {
            return;
        }
        int length = array.length;
        i("---begin---");
        for (int i = 0; i < length; i++) {
            i(i + ":" + array[i].toString());
        }
        i("---end---");
    }
}