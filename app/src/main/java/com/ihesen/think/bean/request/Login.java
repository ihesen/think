package com.ihesen.think.bean.request;

/**
 * author: ihesen on 2016/4/28 10:34
 * email: hesen@ichsy.com
 */
public class Login {

    /**
     * loginName : 13512345678
     * loginPass : 123456
     * zoo : {"key":"tesetkey","token":" "}
     */

    private String loginName;
    private String loginPass;
    /**
     * key : tesetkey
     * token :
     */

    private ZooBean zoo;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPass() {
        return loginPass;
    }

    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }

    public ZooBean getZoo() {
        return zoo;
    }

    public void setZoo(ZooBean zoo) {
        this.zoo = zoo;
    }

    public static class ZooBean {
        private String key;
        private String token;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
