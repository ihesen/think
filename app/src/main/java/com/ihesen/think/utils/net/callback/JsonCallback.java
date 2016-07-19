package com.ihesen.think.utils.net.callback;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import ricky.oknet.exception.ExceptionParser;
import ricky.oknet.utils.Cons;
import ricky.oknet.utils.GsonUtils;

/**
 * 默认将返回的数据解析成需要的Bean,可以是 BaseBean，String，List，Map
 */
public abstract class JsonCallback<T> extends EncryptCallback<T> {

    private Class<T> clazz;
    private Type type;

    @SuppressWarnings("all")
    public JsonCallback() {
        this.clazz = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        //此方法添加自定义异常
        addExceptionParser(new ExceptionParser() {
            @Override
            protected boolean handler(@NonNull Throwable e, @NonNull IHandler handler) {

                String s = !TextUtils.isEmpty(e.getMessage()) ? e.getMessage() : e.getClass().getSimpleName();

                if (JSONException.class.isAssignableFrom(e.getClass())) {
                    handler.onHandler(Cons.Error.Internal, s);
                    return true;
                }
                return false;
            }
        });
    }


    public JsonCallback(Type type) {
        this.type = type;
    }


    //该方法是子线程处理，不能做ui相关的工作
    @Override
    public T parseNetworkResponse(Response response) throws Exception {
        String responseData = response.body().string();
        if (TextUtils.isEmpty(responseData)) return null;

        /**
         * 一般来说，服务器返回的响应码都包含 code，msg，data 三部分，在此根据自己的业务需要完成相应的逻辑判断
         * 以下只是一个示例，具体业务具体实现
         */
        JSONObject jsonObject = new JSONObject(responseData);
        final String msg = jsonObject.optString("message", "");
        final int code = jsonObject.optInt("status", 0);
        String data = jsonObject.toString();

        switch (code) {
            case 1:
                /**
                 * code = 0 代表成功，默认实现了Gson解析成相应的实体Bean返回，可以自己替换成fastjson等
                 * 对于返回参数，先支持 String，然后优先支持class类型的字节码，最后支持type类型的参数
                 */
                if (clazz == String.class) return (T) data;
                if (clazz != null) return GsonUtils.INSTANCE.gson.fromJson(data, clazz);
                if (type != null) return GsonUtils.INSTANCE.gson.fromJson(data, type);
            case 104:
                //比如：用户授权信息无效，在此实现相应的逻辑，弹出对话或者跳转到其他页面等
                throw new IllegalStateException("用户授权信息无效");
            case 105:
                //比如：用户收取信息已过期，在此实现相应的逻辑，弹出对话或者跳转到其他页面等
                throw new IllegalStateException("用户收取信息已过期");
            case 106:
                //比如：用户账户被禁用，在此实现相应的逻辑，弹出对话或者跳转到其他页面等
                throw new IllegalStateException("用户账户被禁用");
            case 300:
                //比如：其他乱七八糟的等，在此实现相应的逻辑，弹出对话或者跳转到其他页面等
                throw new IllegalStateException("其他乱七八糟的等");
            default:
                throw new IllegalStateException("错误代码：" + code + "，错误信息：" + msg);
        }
        //如果要更新UI，需要使用handler，可以如下方式实现，也可以自己写handler
        /*OkHttpUtils.getInstance().getDelivery().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(OkHttpUtils.getContext(), "错误代码：" + code + "，错误信息：" + msg, Toast.LENGTH_SHORT).show();
            }
        });
        return null;*/
    }
}
