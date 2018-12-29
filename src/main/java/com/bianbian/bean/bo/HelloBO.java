package com.bianbian.bean.bo;

import com.bianbian.bean.db.Hello;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bianbian
 * @date 2018/12/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HelloBO extends Hello {
//    some properties

//    some methods

    @Override
    public String toString() {
        return String.format("HelloBO(Hello=%s)", super.toString());
    }
}
