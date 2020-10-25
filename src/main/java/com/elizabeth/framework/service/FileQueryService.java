package com.elizabeth.framework.service;

import com.elizabeth.framework.model.FileVO;

import java.util.List;
import java.util.Map;

public interface FileQueryService {

    List<FileVO> getFiles(String prefix);

    List<FileVO> getFile(String path);

    List<Map<String, String>> search(String prefix, String path);
}
