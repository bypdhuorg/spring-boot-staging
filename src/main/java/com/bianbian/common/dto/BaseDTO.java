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

    public BaseDTO(boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("BaseDTO(success=%s, code=%s, message=%s, data=%s)", success, code, message, data);
    }

}
