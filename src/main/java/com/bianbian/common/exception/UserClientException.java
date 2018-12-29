package com.bianbian.common.exception;

/**
 * @author bianbian
 * @date 2018/12/6
 */
public class UserClientException extends RuntimeException {

    public UserClientException() {
        super();
    }

    public UserClientException(String msg) {
        super(msg);
    }

    public UserClientException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
