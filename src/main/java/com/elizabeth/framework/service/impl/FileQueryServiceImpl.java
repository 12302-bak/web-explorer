package com.elizabeth.framework.service.impl;

import com.elizabeth.framework.model.FileVO;
import com.elizabeth.framework.service.FileQueryService;
import com.elizabeth.framework.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class FileQueryServiceImpl implements FileQueryService {

    @Override
    public List<FileVO> getFiles(String prefix) {
        List<FileVO> result = new ArrayList<>();
        File files = new File(prefix);

        for (File file : files.listFiles()) {
            if(file.isDirectory()){
                FileVO fileVO = new FileVO();
                fileVO.setDir(true);
                fileVO.setName(file.getName());
                fileVO.setTime(FileUtil.getModifiedTime(file.lastModified()));
                /*前端页面不用展示，所以不用获取数据*/
                //List<FileVO> temp = getFiles(file.getAbsolutePath());
                //fileVO.setFileVOs(temp);
                result.add(fileVO);
            }else{
                FileVO fileVO = new FileVO();
                fileVO.setDir(false);
                fileVO.setName(file.getName());
                fileVO.setSize(FileUtil.toHumanReadable(file.length()));
                fileVO.setTime(FileUtil.getModifiedTime(file.lastModified()));
                result.add(fileVO);
            }
        }
        Collections.sort(result);
        return result;
    }


    @Override
    public List<FileVO> getFile(String path) {
        List<FileVO> result = new ArrayList(1);
        File file = new File(path);
        FileVO fileVO = new FileVO();
        fileVO.setDir(false);
        fileVO.setName(file.getName());
        fileVO.setSize(FileUtil.toHumanReadable(file.length()));
        fileVO.setTime(FileUtil.getModifiedTime(file.lastModified()));
        result.add(fileVO);
        return result;
    }


    @Override
    public List<Map<String, String>> search(String prefix, String path) {
        List<Map<String,String>> result = new ArrayList<>();
        File files = new File(prefix + path);

        for (File file : files.listFiles()) {
            if(file.isDirectory()){
                List<Map<String,String>> temp = search(prefix ,path + file.getName() + "/");
                result.addAll(temp);
            }else{
                result.add(new HashMap(){{put("name",path + file.getName());}});
            }
        }
        return result;
    }
}
