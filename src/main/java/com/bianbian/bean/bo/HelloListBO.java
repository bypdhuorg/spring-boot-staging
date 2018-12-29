package com.bianbian.bean.bo;

import com.bianbian.common.bean.BasePageSort;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bianbian
 * @date 2018/12/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HelloListBO extends BasePageSort {
    @Override
    public String toString() {
        return String.format("HelloListBO(BasePageSort=%s)", super.toString());
    }
}
