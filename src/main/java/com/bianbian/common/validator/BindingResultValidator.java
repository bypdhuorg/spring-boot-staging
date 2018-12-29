package com.bianbian.common.validator;

import com.bianbian.common.exception.UserClientException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;


/**
 * @author bianbian
 * @date 2018/11/9
 */
public class BindingResultValidator {
    public static void valid(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            List<ObjectError> allErrors = bindingResult.getAllErrors();
            String errorStr = "";
            for (ObjectError err : allErrors) {
                errorStr += err.getDefaultMessage() + "; ";
            }
            throw new UserClientException(errorStr);
        }
    }
}
