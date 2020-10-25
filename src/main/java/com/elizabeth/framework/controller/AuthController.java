package com.elizabeth.framework.controller;

import com.elizabeth.framework.model.message.ResponseMessage;
import com.elizabeth.framework.shiro.token.SimpleAuthToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    @RequestMapping(value = "/") public String root(){
        Subject subject = SecurityUtils.getSubject();
        return subject.isAuthenticated() ? "redirect:/index" : "auth";
    }

    @RequestMapping(value = "/index") public String index(){ return "index"; }

    @RequestMapping(value = "/auth" ,method = { RequestMethod.POST } )
    @ResponseBody
    public Object auth(String authCode){
        Subject subject = SecurityUtils.getSubject();
        SimpleAuthToken simpleAuthToken = new SimpleAuthToken(authCode);
        try{
            subject.login(simpleAuthToken);
        }catch(UnknownAccountException uae){
            uae.printStackTrace();
            System.out.println(uae.getMessage());
            return new ResponseMessage(501,"UnknownAccountException",null);
        }catch(IncorrectCredentialsException ice){
            ice.printStackTrace();
            System.out.println(ice.getMessage());
            return new ResponseMessage(502,"IncorrectCredentialsException",null);
        }catch(LockedAccountException lae){
            System.out.println(lae.getMessage());
            return new ResponseMessage(503,"LockedAccountException",null);
        }catch(ExcessiveAttemptsException eae){
            System.out.println(eae.getMessage());
            return new ResponseMessage(504,"ExcessiveAttemptsException",null);
        }catch(AuthenticationException ae){
            System.out.println(ae.getMessage());
            return new ResponseMessage(505,"AuthenticationException",null);
        }
        return new ResponseMessage(200,"verified success",null);
    }

    @RequestMapping(value = "/offline",method = { RequestMethod.GET })
    public String offline(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }
}
