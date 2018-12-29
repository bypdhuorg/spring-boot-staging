package com.bianbian.common.exception;

/**
 * @author bianbian
 * @date 2018/12/6
 */
public class MyServiceException extends RuntimeException {

    public MyServiceException() {
        super();
    }

    public MyServiceException(String msg) {
        super(msg);
    }

    public MyServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
