package com.github.binarywang.demo.wechat.bean;

public class ParamError{
    private String paramName;
    private String errorMsg;

    public ParamError(String paramName,String errorMsg){
        this.paramName = paramName;
        this.errorMsg = errorMsg;
    }

    public ParamError(){
    }

    public String getParamName(){
        return paramName;
    }

    public void setParamName(String paramName){
        this.paramName = paramName;
    }

    public String getErrorMsg(){
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg){
        this.errorMsg = errorMsg;
    }
}
