package com.elizabeth.framework.model.message;

public class ResponseMessage<T> {

    public ResponseMessage(){
        this.code = 200;
        this.desc = "success";
    }

    public ResponseMessage(T data){
        this.code = 200;
        this.desc = "success";
        this.data = data;
    }

    public ResponseMessage(String desc , T data){
        this.code = 200;
        this.desc = desc;
        this.data = data;
    }

    public ResponseMessage(Integer code,String desc,T data){
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public ResponseMessage(String desc,T source ,boolean back){
        this.code = 200;
        this.desc = desc;
        this.source = source;
    }

    private Integer code;
    private String desc;
    private T data;
    private T source;

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
