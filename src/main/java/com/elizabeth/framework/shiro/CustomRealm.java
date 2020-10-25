package com.elizabeth.framework.shiro;

import com.elizabeth.framework.shiro.token.SimpleAuthToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomRealm extends AuthorizingRealm {
    private static String authCode;

    @Value(value = "${explorer.auth.code}")
    public void setAuthCode(String authCode){ CustomRealm.authCode = authCode; }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String inputToken = SimpleAuthToken.class.cast(authenticationToken).getAuth();
        if(authCode.equals(inputToken))
            return new SimpleAuthenticationInfo(authCode + "_p",authCode + "_c",authCode + "_r");
        return null;
    }
}
