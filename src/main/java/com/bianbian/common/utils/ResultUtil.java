package com.bianbian.common.utils;


import com.bianbian.common.dto.BaseDTO;

/**
 * @author Exrick
 */
public class ResultUtil<T> {

    private BaseDTO<T> result;

    public ResultUtil(){
        result=new BaseDTO<>();
        result.setSuccess(true);
        result.setMessage("success");
        result.setCode(200);
    }

    public BaseDTO<T> setData(T t){
        this.result.setData(t);
        this.result.setCode(200);
        return this.result;
    }

    public BaseDTO<T> setSuccessMsg(String msg){
        this.result.setSuccess(true);
        this.result.setMessage(msg);
        this.result.setCode(200);
        this.result.setData(null);
        return this.result;
    }

    public BaseDTO<T> setData(T t, String msg){
        this.result.setData(t);
        this.result.setCode(200);
        this.result.setMessage(msg);
        return this.result;
    }

    public BaseDTO<T> setErrorMsg(String msg){
        this.result.setSuccess(false);
        this.result.setMessage(msg);
        this.result.setCode(500);
        return this.result;
    }

    public BaseDTO<T> setErrorMsg(Integer code, String msg){
        this.result.setSuccess(false);
        this.result.setMessage(msg);
        this.result.setCode(code);
        return this.result;
    }
}
