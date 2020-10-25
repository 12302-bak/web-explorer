package com.elizabeth.framework.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class SimpleAuthToken extends UsernamePasswordToken {
    private String auth;

    public SimpleAuthToken(String auth){
        this.auth = auth;
    }

    public String getAuth() { return auth; }

    @Override
    public Object getPrincipal() {
        return auth;
    }

    @Override
    public Object getCredentials() {
        return auth + "_c";
    }
}
