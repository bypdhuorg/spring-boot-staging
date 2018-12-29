package com.bianbian.bean.dto;

import com.bianbian.bean.common.HelloDetail;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author bianbian
 * @date 2018/12/29
 */
@EqualsAndHashCode(callSuper = false)
public class HelloListDTO extends BaseListDTO<HelloDetail> {

    public HelloListDTO(Long total, Integer page, Integer pageSize, List<HelloDetail> content) {
        super(total, page, pageSize, content);
    }

    @Override
    public String toString() {
        return String.format("HelloListDTO(BaseListDTO=%s)", super.toString());
    }
}
