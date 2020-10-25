package com.elizabeth.framework.controller;

import com.elizabeth.framework.model.message.ResponseMessage;
import com.elizabeth.framework.service.FileOperateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Controller
public class UploadController {

    private static String prefix;

    @Value("${explorer.upload.root}")
    public void setPrefix(String prefixValue){
        prefix = prefixValue;
    }

    @Resource
    private FileOperateService fileOperateService;

    @PostMapping("/multiUpload")
    @ResponseBody
    public ResponseMessage multiUpload(HttpServletRequest request ,String currPath) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files[]");
        String filePath = prefix + currPath;
        files.stream()
                .filter(file -> !file.isEmpty())
                .forEach(multipartFile -> {
                    try{
                        multipartFile.transferTo(new File(filePath + multipartFile.getOriginalFilename()));
                    }catch (Exception e ){ e.printStackTrace(); }
                }
        );

        return new ResponseMessage();
    }


    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseMessage createDir(String currPath ,String dirName){
        return new ResponseMessage(fileOperateService.createDirectoryName(prefix + currPath + dirName));
    }

    @GetMapping(value = "/downloadFile")
    public void downloadFile(String filePath ,String fileName , HttpServletResponse response){
        fileOperateService.downloadFile(prefix + filePath, fileName ,response);
    }
}
