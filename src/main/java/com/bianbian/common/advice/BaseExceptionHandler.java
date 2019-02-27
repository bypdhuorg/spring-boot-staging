package com.bianbian.common.advice;


import com.bianbian.common.dto.BaseDTO;
import com.bianbian.common.enums.ResponseStatusEnum;
import com.bianbian.common.exception.MyServiceException;
import com.bianbian.common.exception.ObjectNotFoundException;
import com.bianbian.common.exception.UserClientException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * @author bianbian
 * @date 2018/12/6
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    public BaseDTO exception(Exception e, WebRequest req) {
        log.error(e.getMessage(), e);
        return new BaseDTO<>(ResponseStatusEnum.FAILURE.getSuccess(), HttpStatus.NOT_IMPLEMENTED.value(), "服务异常，请检查日志", null);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object httpMessageNotReadableException(Exception e, WebRequest req) {
        return getExceptionResult(e, "无法识别客户端参数", HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object httpRequestMethodNotSupportedException(Exception e, WebRequest req) {
        return getExceptionResult(e, "无法识别客户端Restful", HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object httpMediaTypeNotSupportedException(Exception e, WebRequest req) {
        return getExceptionResult(e, "无法识别客户端Content-Type", HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(value = {MyServiceException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Object serviceException(Exception e, WebRequest req) {
        return getExceptionResult(e, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(value = {UserClientException.class, MissingServletRequestParameterException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object userClientException(Exception e, WebRequest req) {
        return getExceptionResult(e, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(value = {ObjectNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Object objectNotFoundException(Exception e, WebRequest req) {
        String msg = "找不到对应的内容";
        if (!StringUtils.isEmpty(e.getMessage())) {
            msg = e.getMessage();
        }
        return getExceptionResult(e, msg, HttpStatus.NOT_FOUND.value());
    }

    private BaseDTO getExceptionResult(Exception e, String msg, Integer status) {
        log.error(e.getMessage());
        return new BaseDTO<>(ResponseStatusEnum.FAILURE.getSuccess(), status, msg, null);
    }
}
