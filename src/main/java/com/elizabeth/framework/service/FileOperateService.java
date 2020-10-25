package com.elizabeth.framework.service;

import javax.servlet.http.HttpServletResponse;

public interface FileOperateService {

    String createDirectoryName(String name);

    void downloadFile(String filePath, String fileName, HttpServletResponse response);
}
