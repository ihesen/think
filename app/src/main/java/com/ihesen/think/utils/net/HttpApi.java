package com.ihesen.think.utils.net;

import com.ihesen.think.utils.net.model.PostDetailRequestBean;
import com.ihesen.think.utils.net.response.CityResponse;
import com.ihesen.think.utils.net.response.CommonBen;
import com.ihesen.think.utils.net.response.RequestInfo;

import java.io.File;

import ricky.oknet.cache.CacheMode;
import ricky.oknet.modeinterface.NetRequest;
import ricky.oknet.modeinterface.annotation.CACHE;
import ricky.oknet.modeinterface.annotation.GET;
import ricky.oknet.modeinterface.annotation.PARAMS;
import ricky.oknet.modeinterface.annotation.POST;
import ricky.oknet.modeinterface.annotation.POSTJSON;

public interface HttpApi {

    @GET("http://192.168.1.70/api/common/cityList")
    //?productLine=5&os=android
    NetRequest<CityResponse.DataBean> cityList(@PARAMS("productLine") int productLine, @PARAMS("os") String os);

    @CACHE(CacheMode.FIRST_CACHE_THEN_REQUEST)
    @GET("method")
    NetRequest<RequestInfo> method();

    @GET("download")
    NetRequest<File> downFile();

    @POST("/webupload/zooupload")
    NetRequest<RequestInfo> upload(@PARAMS("file") File file);

    @POSTJSON("/api/artContentController/getPostDetial")
    NetRequest<CommonBen> getPostDetial(PostDetailRequestBean entity);
}
