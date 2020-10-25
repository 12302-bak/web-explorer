package com.elizabeth.framework.service.impl;

import com.elizabeth.framework.service.FileOperateService;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class FileOperateServiceImpl implements FileOperateService {

    @Override
    public String createDirectoryName(String name) {
        try{
            File temp ;
            if("".equals(name) || name == null) return "directory name invalid";
            if(!  (temp = new File(name)).exists()) temp.mkdirs();
        }catch (Exception e){
            return e.getMessage();
        }
        return "directory create successfully";
    }

    @Override
    public void downloadFile(String filePath, String fileName ,HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        FileChannel open = null;
        try {
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName).getBytes(), "iso-8859-1"));

            open = FileChannel.open(Paths.get(filePath,fileName), StandardOpenOption.READ);
            response.setContentLengthLong(open.size());
            MappedByteBuffer mappedByteBuffer = open.map(FileChannel.MapMode.READ_ONLY, 0, open.size());
            byte[] bytes = new byte[mappedByteBuffer.limit()];
            mappedByteBuffer.get(bytes,0,bytes.length);
            outputStream = response.getOutputStream();
            outputStream.write(bytes,0,bytes.length);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(open != null){
                    open.close();
                }
                if(outputStream != null){
                    outputStream.close();
                }
            }catch (Exception e){
                //Ignore...
            }
        }
    }

}
