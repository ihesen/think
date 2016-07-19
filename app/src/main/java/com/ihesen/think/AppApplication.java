package com.ihesen.think;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import ricky.oknet.OkHttpUtils;
import ricky.oknet.cookie.store.PersistentCookieStore;
import ricky.oknet.model.HttpParams;

/**
 * author: ihesen on 2016/4/22 11:58
 * email: hesen@ichsy.com
 */
public class AppApplication extends Application {

    /**
     * 全局Context，原理是因为Application类是应用最先运行的，所以在我们的代码调用时，该值已经被赋值过了
     */
    private static Context mInstance;
    /**
     * 主线程ID
     */
    private static int mMainThreadId = -1;
    /**
     * 主线程ID
     */
    private static Thread mMainThread;
    /**
     * 主线程Handler
     */
    private static Handler mMainThreadHandler;
    /**
     * 主线程Looper
     */
    private static Looper mMainLooper;

    @Override
    public void onCreate() {
        super.onCreate();
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        mInstance = this;
        initNet();
    }

    private void initNet(){
        OkHttpUtils.init(this);

       /* HttpHeaders headers = new HttpHeaders();
        headers.put("commonHeaderKey1", "commonHeaderValue1");    //所有的 header 都 不支持 中文
        headers.put("commonHeaderKey2", "commonHeaderValue2");*/
        HttpParams params = new HttpParams();
        params.put("commonParamsKey1", "commonParamsValue1");     //所有的 params 都 支持 中文
        params.put("commonParamsKey2", "这里支持中文参数");

        OkHttpUtils.getInstance()//
                .baseUrl("http://beta-artcloud.ntw.srnpr.com")
                .debug(true, true, "OKNet")                                              //是否打开调试
                .setInnerDebug(false)
                .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)                     //全局的连接超时时间
                .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)                        //全局的读取超时时间
                .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)                       //全局的写入超时时间
                /*.addCommonHeaders(headers)   */                                        //设置全局公共头
//                .setCookieStore(new MemoryCookieStore())                               //cookie使用内存缓存（app退出后，cookie消失）
                .setCookieStore(new PersistentCookieStore())                             //cookie持久化存储，如果cookie不过期，则一直有效
                .addCommonParams(params);
    }

    public static Context getApplication() {
        return mInstance;
    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取主线程的looper
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }
}
