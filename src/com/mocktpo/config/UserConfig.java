package com.mocktpo.config;

import com.alibaba.fastjson.annotation.JSONField;

public class UserConfig {

    @JSONField(name = "user_id")
    private String userId;
    private String mobile;
    private String secret;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "{" + "userId:" + this.getUserId() + ",mobile:" + this.getMobile() + ",secret:" + this.getSecret() + "}";
    }
}
