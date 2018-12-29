package com.bianbian.common.validator;


import com.bianbian.common.exception.MyServiceException;

/**
 * @author bianbian
 * @date 2018/12/6
 */
public class BaseValidator {
    public static void notNull(String s, String msg) {
        if (null == s) {
            throw new MyServiceException(msg + " is null.");
        }
    }

    public static void notEmpty(String s, String msg) {
        if ("".equals(s)) {
            throw new MyServiceException(msg + "is empty.");
        }
    }

    public static void notNullOrEmpty(String s, String msg) {
        notNull(s, msg);
        notEmpty(s, msg);
    }
}
