package com.bianbian.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author bianbian
 * @date 2018/12/6
 */
@Data
public class BaseDTO<T> implements Serializable {

    public static final long serialVersionUID = 221L;

    /**
     * 成功标志
     */
    @JsonProperty(value = "errcode")
    private boolean success;

    @JsonProperty(value = "code")
    private Integer code;

    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "data")
    private T data;

    public BaseDTO() {
    }

    public BaseDTO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("BaseDTO(code=%s, message=%s, data=%s)", code, message, data);
    }

//    public void setCode(Integer code) {
//        this.code = code;
//    }
//
//    public Integer getCode() {
//        return this.code;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public String getMessage() {
//        return this.message;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
//
//    public T getData() {
//        return this.data;
//    }
}
