package com.elizabeth.framework.controller;


import com.elizabeth.framework.model.message.ResponseMessage;
import com.elizabeth.framework.service.FileQueryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    private static String prefix;

    @Resource
    private FileQueryService fileQueryService;

    @Value("${explorer.upload.root}")
    public void setPrefix(String prefixValue){
        prefix = prefixValue;
    }

    @RequestMapping(value = "json")
    @ResponseBody
    public ResponseMessage test(String path , boolean isDir){
        String prefix = TestController.prefix + path;
        if(isDir){
            return new ResponseMessage(path + "/",fileQueryService.getFiles(prefix));
        }else{
            return new ResponseMessage(path ,fileQueryService.getFile(prefix));
        }
    }

    @RequestMapping(value = "search")
    @ResponseBody
    public Object search(String path){
        return fileQueryService.search(prefix,path);
    }


}
