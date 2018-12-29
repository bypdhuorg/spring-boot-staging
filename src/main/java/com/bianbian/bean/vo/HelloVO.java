package com.bianbian.bean.vo;

import com.bianbian.bean.db.Hello;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bianbian
 * @date 2018/12/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HelloVO extends Hello {
//    some properties

//    some methods

    @Override
    public String toString() {
        return String.format("HelloVO(Hello=%s)", super.toString());
    }
}
