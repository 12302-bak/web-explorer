package com.elizabeth.framework.model;

import java.util.List;

public class FileVO implements Comparable<FileVO>{

    private String name;
    private String time;
    private String size;
    private Boolean isDir;
    private List<FileVO> fileVOs;
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getDir() {
        return isDir;
    }

    public void setDir(Boolean dir) {
        isDir = dir;
    }

    public String getRemark() {
        return remark;
    }

    public List<FileVO> getFileVOs() {
        return fileVOs;
    }

    public void setFileVOs(List<FileVO> fileVOs) {
        this.fileVOs = fileVOs;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public int compareTo(FileVO o) {
        if(!this.isDir && o.isDir)
            return 1;
        if(this.isDir && !o.isDir)
            return -1;
        return 0;
    }
}
