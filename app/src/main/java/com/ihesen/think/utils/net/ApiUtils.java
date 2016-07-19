package com.ihesen.think.utils.net;

import com.ihesen.think.utils.net.model.BaseRequest;

import ricky.oknet.modeinterface.NetUtil;
import ricky.oknet.utils.ApiHelper;

/**
 * @author YaoWeihui on 2016/5/25.
 */
public enum ApiUtils {
    INSTANCE;

    public HttpApi getApi() {
        return ApiHelper.get(HttpApi.class, new NetUtil.ICustomerJsonBean<BaseRequest>() {
            @Override
            public BaseRequest onInterceptRequest(BaseRequest baseRequest) {
                baseRequest.zoo.key = "123456";
//                baseRequest.zoo.token = "sfadfafd";
                return baseRequest;
            }
        });
    }

}
