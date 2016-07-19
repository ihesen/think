package com.ihesen.think.module.splash.vus;

import android.app.Activity;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihesen.think.utils.LogUtil;
import com.ihesen.think.utils.net.ApiUtils;
import com.ihesen.think.utils.net.callback.JsonCallback;
import com.ihesen.think.R;
import com.ihesen.think.module.mvp.vus.Vu;
import com.ihesen.think.utils.net.callback.DialogCallback;
import com.ihesen.think.utils.net.model.PostDetailRequestBean;
import com.ihesen.think.utils.net.response.CommonBen;
import com.ihesen.think.utils.net.response.RequestInfo;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import ricky.oknet.OkHttpUtils;
import ricky.oknet.cache.CacheManager;
import ricky.oknet.callback.FileCallback;
import ricky.oknet.lifecycle.INetViewLifecycle;
import ricky.oknet.modeinterface.NetRequest;
import ricky.oknet.utils.Cons;

/**
 * author: ihesen on 2016/4/19 15:24
 * email: hesen@ichsy.com
 */
public class RegisterVu implements Vu, INetViewLifecycle{

    private View rootView;

    @Override
    public View getView() {
        return rootView;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.register_vu, viewGroup, false);
        ButterKnife.bind(this, rootView);
    }

    @OnClick(R.id.textview)
    public void onClick() {
//        upload();
//        downLoad();
        getPostDetail();
    }

    private void getPostDetail(){
        PostDetailRequestBean bean = new PostDetailRequestBean();
        bean.postID = "AAP160712110001";
        NetRequest<CommonBen> postDetial = ApiUtils.INSTANCE.getApi().getPostDetial(bean);
        postDetial.bind(this).execute(new DialogCallback<CommonBen>((Activity) getView().getContext()) {
            @Override
            public void onResponse(boolean isFromCache, CommonBen commonBen, Request request, @Nullable Response response) {
                LogUtil.hLog().d("isFromCache:" + isFromCache);
            }

            @Override
            public void onSimpleError(Cons.Error error, String message) {

            }
        });
    }

    private void upload(){
        ApiUtils.INSTANCE.getApi().upload(new File(Environment.getExternalStorageDirectory() + File.separator + "AmberLoading.jpg")).execute(new JsonCallback<RequestInfo>() {

            @Override
            public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                super.upProgress(currentSize, totalSize, progress, networkSpeed);
                LogUtil.hLog().d("currentSize: " + currentSize + "  totalSize : " + totalSize + " progress:" + progress + "  networkSpeed:" + networkSpeed);
            }

            @Override
            public void onResponse(boolean isFromCache, RequestInfo requestInfo, Request request, @Nullable Response response) {
                LogUtil.hLog().d("requestInfo:" + requestInfo);
            }

            @Override
            public void onSimpleError(Cons.Error error, String message) {
                LogUtil.hLog().d("error:" + error + "  message:" + message);
            }

        });
    }

    public void downLoad(){
        String url = "http://mobile.d.appchina.com/McDonald/r/3839542/com.qq.reader?channel=aplus.direct&uid=c0132c67-3efb-4b5c-96ec-c06a7ccc93ec&page=appplus.detail";
        CacheManager.INSTANCE.clear();
        OkHttpUtils.get(url).execute(new FileCallback("haha.apk") {

            @Override
            public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                LogUtil.hLog().d("currentSize: " + currentSize + "  totalSize : " + totalSize + " progress:" + progress + "  networkSpeed:" + networkSpeed);
            }

            @Override
            public void onResponse(boolean isFromCache, File file, Request request, @Nullable Response response) {
                LogUtil.hLog().d("isFromCache:" + isFromCache + "  file:" + file);
            }

            @Override
            public void onSimpleError(Cons.Error error, String message) {
                LogUtil.hLog().d("message:" + message);
            }

            @Override
            public void onAfter(boolean isFromCache, @Nullable File file, Call call, @Nullable Response response, @Nullable Exception e) {
                super.onAfter(isFromCache, file, call, response, e);
                LogUtil.hLog().d("onAfter isFromCache:" + isFromCache + "  file:" + file);
            }
        });

    }
}
